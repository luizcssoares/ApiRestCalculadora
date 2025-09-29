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

					   withKubeConfig(caCertificate: '-----BEGIN CERTIFICATE-----
MIIC/jCCAeagAwIBAgIBADANBgkqhkiG9w0BAQsFADAVMRMwEQYDVQQDEwprdWJl
cm5ldGVzMB4XDTI1MDkyODA2NTQzOFoXDTM1MDkyNjA2NTQzOFowFTETMBEGA1UE
AxMKa3ViZXJuZXRlczCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALMx
O6lBO9oYTniIDoJ13YX2iCnDRlyyWTr2sxDXtfuHK4j3S0YxuBk1M0k/Q1SF7ueG
eZJ9RWeLIHLTowIzElqbLUQ+lxCe6D++MMqmGv0GKAk5MpxBDWUa2NGVC01j98+u
tFeijXvsx+EPxlwEblSMrqOlOWvW4K0hT2RTjHP9cB+emKnEv5iQ4gXikYWRPc1r
hfW76GwG9qxhWu9I7fCJqnoi/IY0T3kOaVFVmLk3YjJIMknlrSNqHI8PBqc5pCqQ
tpLV1RfNG8w/1XipNr3HNV9N7y3vdSwbOC3nzMiYWGfQt0Aqj7bb7oDrJq0K6hGx
Ebemmk/5oLn0X8pvD3cCAwEAAaNZMFcwDgYDVR0PAQH/BAQDAgKkMA8GA1UdEwEB
/wQFMAMBAf8wHQYDVR0OBBYEFMhUkaEOhOeqIzu3ncGVki6C/EIrMBUGA1UdEQQO
MAyCCmt1YmVybmV0ZXMwDQYJKoZIhvcNAQELBQADggEBAGZvyw/oWhMVZjtzRVRP
s52Uyz6/kFoalV89SkW17mjrOF0fZFWELeB390EzcTutT3ek6zwahoN6lc4JBBfr
p6X4L7bwStJ3txGi0gc3oq95gYlX+YzAjfD9BkYdB9M/xfomJLqQqw7+rHBpU0N/
S5FYXaq0U/Dkdx4RT8ah+cKkOGlXsLKM2v5DY7YMHs6vGKm5j1Vu4/0rrGdWcYFf
LrJkjAhCFCWOsLFvchlfeA+ddjFUceFLXoeDd5Cl45bDxcaPPVdy01z1qELVEaS9
A4lrMYGkUpslkP26UuavohT4Am2Bx9QN8+zgxJCFExbfENSps2VxT4BjqJ70wMjI
Jfk=
-----END CERTIFICATE-----',
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
