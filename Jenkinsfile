pipeline {
    agent any
    stages {
      stage('Build and Test') {
            steps {
                // Your common build and test steps here
            }
        }
 
        stage('Deploy Microservice A') {
            when {
                changeset '**/admin-server/**'
            }
            steps {
               sh "mvn clean package"
            }
        }
 
        stage('Deploy Microservice B') {
            when {
                changeset '**/microserviceB/**'
            }
            steps {
                // Deployment steps for Microservice B
            }
        }
 
        // Add more stages for other microservices as needed
 
    }
 
    post {
        success {
            echo 'Build and tests passed. Ready for deployment.'
        }
 
        failure {
            echo 'Build or tests failed. Deployment skipped.'
        }
    }
}
