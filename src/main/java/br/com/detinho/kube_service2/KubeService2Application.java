package br.com.detinho.kube_service2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Iterator;

@SpringBootApplication
@RestController
public class KubeService2Application {

	public static void main(String[] args) {
		SpringApplication.run(KubeService2Application.class, args);
	}

	@GetMapping("/")
	public String index() throws SocketException {
		return hello();
	}

	@GetMapping("hello")
	public String hello() throws SocketException {
		String ips = "";
		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
		for (Iterator<NetworkInterface> it = networkInterfaces.asIterator(); it.hasNext(); ) {
			NetworkInterface ni = it.next();
			for (Iterator<InetAddress> it1 = ni.getInetAddresses().asIterator(); it1.hasNext(); ) {
				InetAddress address = it1.next();
				ips += address.getHostAddress() + " ";
			}
		}
		final String message = "Hello Kube Service 2 IPs: " + ips.trim();
		System.out.println(message);
		return message;
	}

	@GetMapping("/randomError")
	public String randomError() {
		if (Math.random() > 0.9) {
			throw new RuntimeException("Error");
		}
		return "Ok";
	}

	@GetMapping("fibo/{n}")
	public String fiboRequest(@PathVariable("n") int n) {
		return String.valueOf(fibo(n));
	}

	@GetMapping("killBoy")
	public String killBoy() {
		System.exit(-1);
		return "Never";
	}

	long fibo(int n) {
		if (n < 2) {
			return n;
		} else {
			return fibo(n - 1) + fibo(n - 2);
		}
	}

}
