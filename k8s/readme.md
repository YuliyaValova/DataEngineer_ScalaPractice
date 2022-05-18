# To start a job inside the pod you need:
1) Download scala.yaml and scala_secrets.yaml files.
2) Replace <YOUR_IMAGE_NAME> in scala.yaml with your image name.
3) Add credentials and other configs to scala_secrets.yaml (in format "key: value" (Parameter's descriptions - [here](https://github.com/YuliyaValova/DataEngineer_ScalaPractice/blob/master/README.md)))                  
4) Start your k8s, e.g. if you use Minikube:
	```sh
	minikube start
	```
5) Write this to create a secrets pod on secrets.yaml file base:
	```sh
	kubectl apply -f ./scala_secrets.yaml 
	```
	
6) Write this to create a executable pod on scala.yaml file base:
	```sh
	kubectl apply -f ./scala.yaml 
	```

	
