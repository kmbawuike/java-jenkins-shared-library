#!/user/bin/env groovy

def call() {
    echo 'Incrementing version'
    sh "npm version minor —no-git-tag-version"

    // read the updated version from the package.json file
    def packageJson = readJSON file: 'package.json'
    def version = packageJson.version

    // set the new version as part of IMAGE_NAME
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"

}