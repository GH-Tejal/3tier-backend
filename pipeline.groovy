pipeline {
    agent any
    stages {
        stage('Code-Pull'){
            steps{
                git branch: 'dev', url: 'https://github.com/GH-Tejal/3tier-backend.git'
           }
        }

        stage('Build'){
            steps{
              sh 'mvn clean package'  
           }
        }

        stage('Deploy'){
            steps{
              sh '''
              docker build . -t tejalvaidya/3-tier:latest
              docker push tejalvaidya/3-tier:latest
              docker rmi tejalvaidya/3-tier:latest
              kubectl apply -f ./deploy
           }
        }
    }
}