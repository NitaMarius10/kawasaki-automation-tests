pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        MAVEN_OPTS = '-Dmaven.test.failure.ignore=false'
    }

    stages {
        stage('Test - CreateAccountTest') {
            steps {
                echo 'Running CreateAccountTest...'
                bat 'mvn -Dtest=tests.CreateAccountTest test'
            }
        }

        stage('Test - LoginTest') {
            steps {
                echo 'Running LoginTest...'
                bat 'mvn -Dtest=tests.LoginTest test'
            }
        }

        stage('Test - DeleteAccountTest') {
            steps {
                echo 'Running DeleteAccountTest...'
                bat 'mvn -Dtest=tests.DeleteAccountTest test'
            }
        }

        stage('Test - InvalidLoginTest') {
            steps {
                echo 'Running InvalidLoginTest...'
                bat 'mvn -Dtest=tests.InvalidLoginTest test'
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
