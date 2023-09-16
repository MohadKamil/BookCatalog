pipeline {
  agent any
  stages {
    stage('Build') {
      agent any
      steps {
        sh '''echo "running gradle build"
chmod +x ./gradlew 
./gradlew build'''
      }
    }

  }
}