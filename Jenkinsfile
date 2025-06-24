pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        MAVEN_OPTS = '-Dmaven.test.failure.ignore=false'
    }

    stages {
        stage('Build and Test') {
            steps {
                echo 'Running Maven tests...'
                bat 'mvn clean test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                echo 'Generating Allure results...'
                bat 'mvn allure:report'
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/**/*.xml', fingerprint: true
        }
        failure {
            echo 'Build failed. Please check test logs.'
        }
    }
}
