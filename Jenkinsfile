pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'github-creds', url: 'https://github.com/NitaMarius10/kawasaki-automation-tests.git'
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean test -Dsurefire.suiteXmlFiles=testng.xml'
            }
        }

        stage('Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/allure-results/**/*.*', allowEmptyArchive: true
        }
    }
}
