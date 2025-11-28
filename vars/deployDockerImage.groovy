#!/user/bin/env groovy

def call (String sshCredential, String ec2Url, String port, String dockerImageUrl){
  ssh-agent([sshCredential]){
    echo 'Deploying to AWS EC2'
    sh "ssh -o StrictHostKeyChecking=no ${ec2Url}"

    sh "docker pull ${dockerImageUrl}"
    sh "docker run -d -p ${port} ${dockerImageUrl}"
  }
}