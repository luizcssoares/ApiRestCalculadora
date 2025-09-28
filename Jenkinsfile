pipeline {
    agent any
	environment {
		registry = 'luizcssoares/apirestcalculadora'
		dockerhub_credentials = 'dockerhub-login'
		KUBE_SA_TOKEN = 'eyJhbGciOiJSUzI1NiIsImtpZCI6ImlEYkozMnM3R3IwTlo3ekh1Y2xqT0dNVE1QNGZycUNwYVpOaEdQQ0tnSnMifQ.eyJhdWQiOlsiaHR0cHM6Ly9rdWJlcm5ldGVzLmRlZmF1bHQuc3ZjLmNsdXN0ZXIubG9jYWwiXSwiZXhwIjoxNzUxNDY5MzY2LCJpYXQiOjE3MTk5MzMzNjYsImlzcyI6Imh0dHBzOi8va3ViZXJuZXRlcy5kZWZhdWx0LnN2Yy5jbHVzdGVyLmxvY2FsIiwianRpIjoiZjQ0MTQ1OWItNTg1Yy00NzVjLWI1N2QtY2EzZjZlNDQyM2U3Iiwia3ViZXJuZXRlcy5pbyI6eyJuYW1lc3BhY2UiOiJqZW5raW5zIiwic2VydmljZWFjY291bnQiOnsibmFtZSI6ImplbmtpbnMiLCJ1aWQiOiIyYWFlM2NjOC0wMzY0LTQyZjctODljNy0yODM3YmJjYmQ5YzUifX0sIm5iZiI6MTcxOTkzMzM2Niwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50OmplbmtpbnM6amVua2lucyJ9.AqJgPLhdtPnrjPCUcNRWLK1Hjr9FxXvfQgP7hcRU1urYhL7-C09sTuexVBb4rrmqI1ds7SL2fXXw9Rt8tx0XUkenDGX6Mh0KCXrf13YWdwIqvuV2mFgdSJHAmrO8fgfefEyQCVJgEWS57qnc71wOPxOUJ0xljAqMYaAFgfwfEKRgICBQ998Vums8JlDmd_xkpfz_36G9DKat7WX45gjK9Dklwm-s4r5NYPPKBRnhZC8ENbS4vkYMxhIM_cwvP5hjlHsyhppTU6_hhHmzdE7p7etKMvaaij6wwipe6rhnISzCRW3J9uV93xNvUGnofDOot1CV8dBtgnRUHL0cTjtYBA'
		docker_image = ''
		IMAGE_TAG = "latest"
        NAMESPACE = "default"
		KIND_CONTEXT = "kind-luiz"
		KUBECONFIG = credentials('kubeconfig-secret')
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

					   //dir ('chart') {
					   //   sh 'kubectl get nodes'
					   //}

					   withKubeConfig(caCertificate: 'LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUMvakNDQWVhZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRJMU1Ea3lPREEyTlRRek9Gb1hEVE0xTURreU5qQTJOVFF6T0Zvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBTE14Ck82bEJPOW9ZVG5pSURvSjEzWVgyaUNuRFJseXlXVHIyc3hEWHRmdUhLNGozUzBZeHVCazFNMGsvUTFTRjd1ZUcKZVpKOVJXZUxJSExUb3dJekVscWJMVVErbHhDZTZEKytNTXFtR3YwR0tBazVNcHhCRFdVYTJOR1ZDMDFqOTgrdQp0RmVpalh2c3grRVB4bHdFYmxTTXJxT2xPV3ZXNEswaFQyUlRqSFA5Y0IrZW1LbkV2NWlRNGdYaWtZV1JQYzFyCmhmVzc2R3dHOXF4aFd1OUk3ZkNKcW5vaS9JWTBUM2tPYVZGVm1MazNZakpJTWtubHJTTnFISThQQnFjNXBDcVEKdHBMVjFSZk5HOHcvMVhpcE5yM0hOVjlON3kzdmRTd2JPQzNuek1pWVdHZlF0MEFxajdiYjdvRHJKcTBLNmhHeApFYmVtbWsvNW9MbjBYOHB2RDNjQ0F3RUFBYU5aTUZjd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0hRWURWUjBPQkJZRUZNaFVrYUVPaE9lcUl6dTNuY0dWa2k2Qy9FSXJNQlVHQTFVZEVRUU8KTUF5Q0NtdDFZbVZ5Ym1WMFpYTXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBR1p2eXcvb1doTVZaanR6UlZSUApzNTJVeXo2L2tGb2FsVjg5U2tXMTdtanJPRjBmWkZXRUxlQjM5MEV6Y1R1dFQzZWs2endhaG9ONmxjNEpCQmZyCnA2WDRMN2J3U3RKM3R4R2kwZ2Mzb3E5NWdZbFgrWXpBamZEOUJrWWRCOU0veGZvbUpMcVFxdzcrckhCcFUwTi8KUzVGWVhhcTBVL0RrZHg0UlQ4YWgrY0trT0dsWHNMS00ydjVEWTdZTUhzNnZHS201ajFWdTQvMHJyR2RXY1lGZgpMckprakFoQ0ZDV09zTEZ2Y2hsZmVBK2RkakZVY2VGTFhvZURkNUNsNDViRHhjYVBQVmR5MDF6MXFFTFZFYVM5CkE0bHJNWUdrVXBzbGtQMjZVdWF2b2hUNEFtMkJ4OVFOOCt6Z3hKQ0ZFeGJmRU5TcHMyVnhUNEJqcUo3MHdNakkKSmZrPQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCg==',
					                   clusterName: 'kind-kind-cluster', 
									   contextName: 'kind-kind-cluster', 
									   credentialsId: 'kubeconfig-secret', 
									   namespace: 'default', 
									   restrictKubeConfigAccess: false, 
									   serverUrl: 'https://172.19.0.2:6443') {
    						dir ('chart') {
							   sh 'ls'
						       sh 'kubectl get pods'
						       //sh 'helm install apirestcalculadora . --kubeconfig ~/.kube/config'
						       //sh 'helm upgrade --install apirestcalculadora chart --namespace default --set image.repository=apirestcalculadora --set image.tag=latest'
							}
					   }
                                       
                       //withKubeConfig([credentialsId: 'kubeconfig-secret']) {	
					   //dir ('chart') {
						//		sh 'ls'
						//        sh 'kubectl get pods'
						        //sh 'helm install apirestcalculadora . --kubeconfig ~/.kube/config'
								//sh 'helm upgrade --install apirestcalculadora chart --namespace default --set image.repository=apirestcalculadora --set image.tag=latest'
						//	}
					   //}
				}
            }
        }
	}
}
