pipeline {
    agent any
      
    stages 
    {
       stage("Git Checkout")
       {
            steps
            {
                git branch: 'main', changelog: false, poll: false, url: 'https://github.com/ktsreddy007/Java_Pro.git'
            }
        }
        
        stage("Compile")
        {
            steps
            {
                bat "mvn clean compile"
            }
        }
        
         stage("UnitTest")
         {
            steps
            {
                bat "mvn test"
            }
        }
         stage("Build & Package")
         {
            steps
            {
                bat "mvn clean package"
            }
        }
        stage('Static Code Analysis') 
        {
            steps 
            {
                withSonarQubeEnv('Sonarqube') {
                    withCredentials([string(credentialsId: 'Jenkins_Sonar', variable: 'SONAR_AUTH_TOKEN')]) 
                    {
                        bat 'cd C:\\Users\\ktsreddy\\.jenkins\\workspace\\CI_Maven_Sonar'
                        dir('C:\\Users\\ktsreddy\\.jenkins\\workspace\\CI_Maven_Sonar') 
                        {
                            bat 'dir /s /b pom.xml | findstr /i "pom.xml"'
                            bat "\"${env.M2_HOME}\"\\bin\\mvn sonar:sonar -Dsonar.projectName=%CI_Maven_Sonar% -Dsonar.java.binaries=.\\ -Dsonar.login=%SONAR_AUTH_TOKEN% -Dsonar.host.url=http://192.168.56.1:9000"
                        }
                    }
                }
            }
        }
                /* environment {
                // Define the necessary Artifactory details
                ARTIFACTORY_URL = ''
                ARTIFACTORY_REPO = 'your-artifactory-repo'
                ARTIFACTORY_USERNAME = credentials('')
                ARTIFACTORY_PASSWORD = credentials('')
            }*/
        stage ('Artifact Publish')
        {
            steps
            {
                 rtServer (
                id:"Artifactory",
                url: 'http://192.168.56.1:8082/artifactory',
                username:'ktsreddy',
                password:'Admin_123',
                timeout:300)
            
                rtUpload (
                serverId:"Artifactory",
                spec: '''{
                    "files":[
                    {
                        "pattern":"*.jar",
                        "target":"javapro-libs-snapshot-local"
                    }
                    ]
                }''',)
            } 
        }
         /*stage('Mail_Notification') {
             displayName 'Mail Notification'
             steps {
                  echo 'Sending mail notifications...'
                  }
            }*/
    }

     post {
        success {
            emailext (
                to: 'ktsreddy007@gmail.com', // Email recipients
                subject: "Jenkins Build Success - ${currentBuild.fullDisplayName}", // Email subject
                body: "Congratulations! Your build ${currentBuild.fullDisplayName} was successful." // Email body
            )
        }
        failure {
            emailext (
                to: 'ktsreddy007@gmail.com', // Email recipients
                subject: "Jenkins Build Failed - ${currentBuild.fullDisplayName}", // Email subject
                body: "Unfortunately, your build ${currentBuild.fullDisplayName} failed." // Email body
            )
        }
    }
}
