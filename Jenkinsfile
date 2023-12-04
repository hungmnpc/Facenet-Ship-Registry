pipeline {
	agent {
		docker {
			image 'maven'
			args '-v $HOME/.m2:/root/.m2'
		}
	}
	stages {
	  stage("build & SonarQube analysis") {            
		steps {
		
		  withSonarQubeEnv('sonarqube') {
			sh 'mvn clean package sonar:sonar'
		  }
		}
	  }
	  stage("Quality Gate") {
		steps {
		  timeout(time: 1, unit: 'HOURS') {
			waitForQualityGate abortPipeline: true
		  }
		}
	  }
	}
  }