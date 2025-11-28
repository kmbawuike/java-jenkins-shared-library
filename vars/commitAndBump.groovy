#!/user/bin/env groovy

def call(String credentialsId, String gitUrl, String branch) {
  sshagent(['git-ssh']) {
      sh 'git config user.email "jenkins@example.com"'
      sh 'git config user.name "jenkins"'

      // Ensure remote is using SSH
      sh "git remote set-url origin ${gitUrl}"
      sh 'git add .'
      sh 'git commit -m "ci: version bump"'

      // Create branch if it doesn't exist
      sh "git push origin HEAD:${branch}"
  }
}