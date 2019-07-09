node {
    checkout scm

    env.DOCKER_API_VERSION="1.23"

    sh "git rev-parse --short HEAD > commit-id"

    tag = readFile('commit-id').replace("\n", "").replace("\r", "")
    appName = "kube_service2"
    registryHost = "127.0.0.1:30400/"
    imageName = "${registryHost}${appName}:${tag}"
    env.BUILDIMG=imageName
    env.BUILD_TAG=tag

    stage "Build"

        withMaven(
            maven: 'maven-3',
            jdk: 'jdk-11') {
                // Run the maven build
                sh "mvn clean package"
            }

        sh "docker build -t ${imageName} ."

    stage "Push"

        sh "docker push ${imageName}"

    stage "Deploy"

        kubernetesDeploy configs: "kube/minikube/deployment.yaml", kubeconfigId: "kube_service2"
}