def call() {
    pipeline{
        agent{
            label "any"
        }

        environment {
            DOCKER_USER = credentials('dockerhub-user')
            DOCKER_PASS = credentials('dockerhub-password')
        }

        stages{

            stage("build Docker image"){
                steps{
                    sh "docker build -t ${DOCKER_USER}/python3:${BUILD_NUMBER} ."
                }
            } 
            stage("Login to Docker Hub"){
                steps{
                    sh "docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}"
                }
            }
            stage("Push Docker image"){
                steps{
                    sh "docker push ${DOCKER_USER}/python3:${BUILD_NUMBER}"
                }
            }
        }
    }
}