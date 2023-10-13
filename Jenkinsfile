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

        stage('admin-server') {
            parallel {
                stage('admin-server') {
                    steps {
                        dir('admin-server') {
                            bsh 'mvn clean package'
                            // You can add more Maven-related build and test commands as needed
                        }
                    }
                }

                stage('api-gateway') {
                    steps {
                        dir('api-gateway') {
                            sh 'mvn clean package'
                            // You can add more Maven-related build and test commands as needed
                        }
                    }
                }
            }
        }
    }
}
