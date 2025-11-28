#!/user/bin/env groovy

def call() {
  echo 'Incrementing version'
  def version
  def msg = sh(script: "git log -1 --pretty=%B", returnStdout: true).trim() // print out commit headline
  if (msg.contains('BREAKING CHANGE')) {
      version = sh(script: 'npm version major', returnStdout: true).trim().replace('v', '')
  } else if (msg.startsWith('feat:')) {
      version = sh(script: 'npm version minor —no-git-tag-version', returnStdout: true).trim().replace('v', '')
  } else {
      version = sh(script: 'npm version patch', returnStdout: true).trim().replace('v', '')
  }
  env.IMAGE_NAME = "${version}-${env.BUILD_NUMBER}"
}