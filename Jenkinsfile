pipeline {
  agent any
  tools {
    gradle
    jdk '17'
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
