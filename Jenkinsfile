pipeline {
    agent any

    parameters {
        choice(
            name: 'BRANCH',
            choices: ['main', 'dev', 'qa'],
            description: 'Rama a construir'
        )
    }

    stages {
        stage('Checkout Code') {
            steps {
                script {
                    git branch: params.BRANCH,
                         url: 'https://github.com/BernardoDuran14/backend-udemi_pro.git'

                    dir('frontend') {
                        git branch: params.BRANCH,
                             url: 'https://github.com/BernardoDuran14/frontend-udemi_pro.git'
                    }
                }
            }
        }

        stage('Build Backend') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    bat 'npm ci --legacy-peer-deps'
                    bat 'npm run build -- --configuration=production --output-hashing=none'
                }
            }
        }
    }

    post {
        always {
            echo "Build completado para rama ${params.BRANCH}"
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            dir('frontend/dist') {
                archiveArtifacts artifacts: '**/*', fingerprint: true
            }
        }
    }
}