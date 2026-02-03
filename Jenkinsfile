pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/p-i-k-l-u/OrangeHRM-Test-Framework.git'
            }
        }

        stage('Clean & Compile') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run TestNG Tests') {
            steps {
                bat 'mvn test -DsuiteXmlFile=testng.xml'
            }
        }

        stage('Archive Test Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Archive Screenshots') {
            steps {
                archiveArtifacts artifacts: 'src/test/resources/screenshots/**/*.png', allowEmptyArchive: true
            }
        }
    }

    post {
        always { echo 'Pipeline Finished' }
        success { echo 'All Tests Passed ✅' }
        failure { echo 'Some Tests Failed ❌ Check Reports' }
    }
}
