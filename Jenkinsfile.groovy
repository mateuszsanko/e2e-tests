#!groovy

node('master') {
    stage('Checkout') {
        checkout scm
    }

    stage('Run tests') {
        withMaven(maven: 'Maven 3.5.3') {
            dir('e2e-tests') {
                sh 'mvn clean install'
            }
        }
    }
}