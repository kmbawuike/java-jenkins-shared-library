#!/user/bin/env groovy

def call() {
  echo 'Testing node application...'
  sh 'npm install'
  sh 'npm test'
}