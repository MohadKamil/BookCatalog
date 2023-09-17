pipeline {
  agent any
  tools {
    gradle 'gradle 8.3'
    jdk 'jdk17'
  }
  stages {
    stage('Build') {
      agent any
      steps {
        sh '''echo "running gradle build"
chmod +x ./gradlew 
./gradlew compileJava'''
      }
    }

  }
}
