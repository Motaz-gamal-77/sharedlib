
def call() {
    pipeline{
        agent {
            label 'any'
        }
        environment {
            DOCKER_USER = credentials('dockerhub-user')
            DOCKER_PASS = credentials('dockerhub-password')
        }
        stages{
            stage("Build java app"){
                steps{
                    sh "mvn clean package install"
                }
            }
            stage("build java app image"){
                steps{
                    sh "docker build -t java3:v${BUILD_NUMBER} ."
                }
            }
            stage("Login to Docker Hub"){
                steps{
                    sh "docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}"
                }
            }
            stage("push java app image"){
                steps{
                    sh "docker push ${DOCKER_USER}/java3:v${BUILD_NUMBER}"
                }
            }
        }
    }
}
