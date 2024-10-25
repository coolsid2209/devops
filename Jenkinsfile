pipeline {
    agent any
    
    environment {
        NODE_VERSION = '14'
        // Define the EC2 instance details
        EC2_USER = 'ec2-user'
        EC2_IP = '13.235.70.47' // Replace with your EC2 instance IP
        PEM_FILE = '/Users/siddharthkumar/Downloads/devops_jenkins_aws.pem' // Update with your key file
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from GitHub
                git 'https://github.com/coolsid2209/devops.git'
            }
        }

        stage('Build') {
            steps {
                // Run Maven commands to build the project
                sh './mvnw clean install' // Maven clean and install
                sh './mvnw test' // Run tests
            }
        }

        stage('Package') {
            steps {
                // Package the Spring Boot application
                sh './mvnw package'
            }
        }

        stage('Deploy to AWS EC2') {
            steps {
                // Copy the JAR file to the EC2 instance
                sshagent(['devops-ec2-user]) { // You need to configure SSH key in Jenkins
                    sh '''
                        scp -o StrictHostKeyChecking=no target/devops-0.0.1-SNAPSHOT.jar ${EC2_USER}@${13.235.70.47}:/home/${EC2_USER}/
                        ssh -o StrictHostKeyChecking=no ${EC2_USER}@${13.235.70.47} 'java -jar /home/${EC2_USER}/devops-0.0.1-SNAPSHOT.jar &'
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment succeeded!'
        }
        failure {
            echo 'Deployment failed.'
        }
    }
}
