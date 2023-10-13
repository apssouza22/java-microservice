node {

   stage('Build images') {
      sh "./package-projects.sh"
   }
   stage('Deploy ECS') {
      sh "./aws-deploy.sh"
   }
}
