To start a job inside the pod(deployment) you need:
1) Download a scala.yaml file.
2) Replace <YOUR_IMAGE_NAME> with your image name.                  
3) Start your k8s, e.g. if you use Minikube:
	```sh
	minikube start
	```
4) Write this to create a deployment on .yaml file base:
	```sh
	kubectl apply -f ./scala.yaml 
	```
5) Write this and find your new pod name:
	```sh
	kubectl get pods 
	```
	Be sure, that your pod is running (it becomes unactive after "sleep 1000" ends)
6) Write this to run spark job inside the pod:
	```sh
	kubectl exec <POD_NAME> -- java -cp "./*" load.LoadStarter <PARAMS>
	```
	
- POD_NAME - Name of the created pod, that you look at step 5.
- PARAMS - Parameters for connection to source, table name, etc. (Look here[https://github.com/YuliyaValova/DataEngineer_ScalaPractice/blob/dev/README.md])
	