pipeline {
  agent any
  tools {
    gradle
    jdk
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
