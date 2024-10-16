pipeline {
    agent any  // Use any available agent

    environment {
        // Optional: Define any environment variables
        JAVA_HOME = '/usr/lib/jvm/java-11-openjdk' // Adjust based on your Java installation
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the repository
                git 'https://github.com/coolsid2209/devops.git'
            }
        }

        stage('Build') {
            steps {
                // Use Maven to build the project
                sh 'mvn clean package' // Clean and package the application
            }
        }

        stage('Test') {
            steps {
                // Run tests using Maven
                sh 'mvn test' // Run the test command
            }
        }

        stage('Deploy') {
            steps {
                // Example of deploying the application
                echo 'Deploying to production...'
                // Add your deployment command here (e.g., using SSH, Docker, etc.)
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded!'
            // Optional: Notify team (e.g., via email, Slack, etc.)
        }
        failure {
            echo 'Pipeline failed.'
            // Optional: Send notifications on failure
        }
        always {
            echo 'Cleaning up...'
            // Optional: Always execute cleanup steps, such as archiving artifacts
        }
    }
}
