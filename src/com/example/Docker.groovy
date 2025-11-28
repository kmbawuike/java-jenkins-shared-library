#!/user/bin/env groovy
package com.example

class Docker implements Serializable {

    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName) {
        script.echo "building the docker image..."
        script.sh "docker build -t $imageName ."
        }

    def dockerLogin() {
        script.withCredentials([script.usernamePassword(credentialsId: 'jenkins-docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh "echo '${script.PASS}' | docker login -u '${script.USER}' --password-stdin"
        }
    }

    def dockerPush(String imageName) {
        script.sh "docker push $imageName"
    }

     def dockerPull(String imageName) {
        script.sh "docker pull $imageName"
    }

      def dockerRun(String imageName, String port) {
        script.sh "docker run -d -p $port $imageName"
    }
}