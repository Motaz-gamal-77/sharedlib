package org.iti;

def login(USERNAME, PASSWORD){
    sh "docker login -u ${USERNAME} -p ${PASSWORD}"
}

def build(IMAGE_NAME, IMAGE_TAG){
    sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
}

def push(IMAGE_NAME,USERNAME, IMAGE_TAG){
    sh "docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${USERNAME}/${IMAGE_NAME}:${IMAGE_TAG}"
    sh "docker push ${USERNAME}/${IMAGE_NAME}:${IMAGE_TAG}"
}