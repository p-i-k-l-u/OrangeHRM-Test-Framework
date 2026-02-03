pipeline {
    agent any

    tools {
        maven 'Maven3'      // Name configured in Jenkins Global Tool Config
        jdk 'JDK17'         // Or JDK11/17 based on your setup
    }

    environment {
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=true"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'git@github.com:p-i-k-l-u/OrangeHRM-Test-Framework.git'
            }
        }

        stage('Clean & Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run TestNG Tests') {
            steps {
                sh 'mvn test -DsuiteXmlFile=testng.xml'
            }
        }

        stage('Archive Test Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Archive Screenshots (if any)') {
            steps {
                archiveArtifacts artifacts: 'src/test/resources/screenshots/**/*.png', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline Finished'
        }
        success {
            echo 'All Tests Passed ✅'
        }
        failure {
            echo 'Some Tests Failed ❌ Check Reports'
        }
    }
}
