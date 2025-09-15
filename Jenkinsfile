pipeline {
    agent any
	environment {
		registry = 'luizcssoares/apirestcalculadora'
		dockerhub_credentials = 'dockerhub_luizcssoares'
		KUBE_SA_TOKEN = 'eyJhbGciOiJSUzI1NiIsImtpZCI6ImlEYkozMnM3R3IwTlo3ekh1Y2xqT0dNVE1QNGZycUNwYVpOaEdQQ0tnSnMifQ.eyJhdWQiOlsiaHR0cHM6Ly9rdWJlcm5ldGVzLmRlZmF1bHQuc3ZjLmNsdXN0ZXIubG9jYWwiXSwiZXhwIjoxNzUxNDY5MzY2LCJpYXQiOjE3MTk5MzMzNjYsImlzcyI6Imh0dHBzOi8va3ViZXJuZXRlcy5kZWZhdWx0LnN2Yy5jbHVzdGVyLmxvY2FsIiwianRpIjoiZjQ0MTQ1OWItNTg1Yy00NzVjLWI1N2QtY2EzZjZlNDQyM2U3Iiwia3ViZXJuZXRlcy5pbyI6eyJuYW1lc3BhY2UiOiJqZW5raW5zIiwic2VydmljZWFjY291bnQiOnsibmFtZSI6ImplbmtpbnMiLCJ1aWQiOiIyYWFlM2NjOC0wMzY0LTQyZjctODljNy0yODM3YmJjYmQ5YzUifX0sIm5iZiI6MTcxOTkzMzM2Niwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50OmplbmtpbnM6amVua2lucyJ9.AqJgPLhdtPnrjPCUcNRWLK1Hjr9FxXvfQgP7hcRU1urYhL7-C09sTuexVBb4rrmqI1ds7SL2fXXw9Rt8tx0XUkenDGX6Mh0KCXrf13YWdwIqvuV2mFgdSJHAmrO8fgfefEyQCVJgEWS57qnc71wOPxOUJ0xljAqMYaAFgfwfEKRgICBQ998Vums8JlDmd_xkpfz_36G9DKat7WX45gjK9Dklwm-s4r5NYPPKBRnhZC8ENbS4vkYMxhIM_cwvP5hjlHsyhppTU6_hhHmzdE7p7etKMvaaij6wwipe6rhnISzCRW3J9uV93xNvUGnofDOot1CV8dBtgnRUHL0cTjtYBA'
		docker_image = ''
		IMAGE_TAG = "latest"
        NAMESPACE = "default"
		//KUBECONFIG = credentials('minikube-kubeconfig')
	}
	stages { 
		stage('GIT pull') {			
			steps{
			   git branch: 'main', url: "https://github.com/luizcssoares/ApiRestCalculadora.git"
			}
		}
		stage('Build Maven') {
			steps {
			   sh 'mvn -B -DskipTests clean package'
			}
		}
		stage('SonarQube') {
			steps {
			   echo 'Executin SonarQube.'
			}
		}
		stage('Docker Build'){
			steps{
			   script {
			         docker_image = docker.build  registry
			   }
			}
		}
		stage('Deploy Docker Hub') {
			steps{
			   script {				 
				     // echo 'Deploy Docker Hub concluido com sucesso !'
				     docker.withRegistry( '', dockerhub_credentials ) {
				     docker_image.push("latest")					
				  }				  				
			   }
			}
		}
		stage('Check Cluster') {
            steps {
                sh 'kubectl cluster-info'
                sh 'kubectl get nodes'
            }
        }

        //stage('Deploy with Helm') {
        //    steps {
                //sh 'helm upgrade --install exemplo ./chart --namespace default'
		//		withEnv(["KUBECONFIG=/var/lib/jenkins/.kube/config"]) {						  
		//				 dir ('chart') {
		//					sh 'helm upgrade apirestcalculadora .'
                            //sh 'helm install apirestcalculadora .'
		//				 }
        //               }                       
        //    }
        //}
        stage('Deploy App on k8s') {
            steps {
				script {				  						
                
					   // withKubeConfig([credentialsId: 'secrets', serverUrl: 'https://127.0.0.1:32771']) {
					   //	 dir ('chart') {
					   //	 	sh 'ls'
					   //	     sh 'helm install apirestcalculadora .'
							     //sh 'helm upgrade --install apirestcalculadora chart --namespace default --set image.repository=apirestcalculadora --set image.tag=latest'
					   //	 }
					   //    echo 'Chibata Preta'
					   //  }
                       withEnv(["KUBECONFIG=/var/lib/jenkins/.kube/config"]) {						  
						 dir ('chart') {
							sh 'helm upgrade apirestcalculadora .'
                            //sh 'helm install apirestcalculadora .'
						 }
                       }                       

                       // withKubeConfig([credentialsId: 'minikube-secret',                         
						//		serverUrl: 'https://127.0.0.1:32776', 
						//		namespace: 'default',
						//		clusterName: 'minikube']) {	
						//	dir ('chart') {
						//		sh 'ls'
								//sh 'kubectl get pods'
						 //       sh 'helm install apirestcalculadora . --kubeconfig ~/.kube/config'
								//sh 'helm upgrade --install apirestcalculadora chart --namespace default --set image.repository=apirestcalculadora --set image.tag=latest'
						//	}
						//}
				}
            }
        }
	}
}
