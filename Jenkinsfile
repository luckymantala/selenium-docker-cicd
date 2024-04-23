pipeline{

    agent any

    stages{

        stage('Build Jar') {
            steps{
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Build Image') {
            steps{
                bat "docker build -t=luckymantala/selenium ."
            }
        }

        stage('Push Image') {
            environment {
                DOCKER_HUB = credentials('dockerhub_creds')
            }
            steps{
                bat 'echo | set /p="%DOCKER_HUB_PSW%" | docker login -u %DOCKER_HUB_USR% --password-stdin'
                bat "docker push luckymantala/selenium:latest"
                // bat "docker tag luckymantala/selenium:latest luckymantala/selenium:${env.BUILD_NUMBER}"
                // bat "docker push luckymantala/selenium:${env.BUILD_NUMBER}"
            }
        }
        
    }

    post{
        always{
            bat "docker logout"
        }
    }
}