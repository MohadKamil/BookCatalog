pipeline {
  agent any
  tools {
    gradle '8.3'
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
