pipeline {
    agent any
tools {
      maven 'local_maven'
   }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/apssouza22/java-microservice.git'
            }
        }

        stage('Build and Test') {
            parallel {
                stage('api-gateway') {
                    steps {
                        dir('api-gateway') {
                            sh 'mvn clean package'
                            // You can add more Maven-related build and test commands as needed
                        }
                    }
                }

                stage('eureka-server') {
                    steps {
                        dir('eureka-server') {
                            sh 'mvn clean package'
                            // You can add more Maven-related build and test commands as needed
                        }
                    }
                }
            }
        }
    }
}
