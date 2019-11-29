pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Build project...'
        sh 'mvn package -DskipTests'
        echo 'Build done!'
      }
    }

    stage('Test') {
      steps {
        echo 'Tests started...'
        script {
          try {
            sh 'mvn test -B'
          } catch(err) {
            step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
            if (currentBuild.result == 'UNSTABLE')
            currentBuild.result = 'FAILURE'
            throw err
          }

          echo 'Tests done!'
        }

      }
    }

    stage('Deploy') {
      steps {
        echo 'Deploy...'
        sh 'bash jenkins_vc.sh'
      }
    }
  }

  post {
    always {
      echo 'Finished!'
      step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
      deleteDir()
    }
  }
}
