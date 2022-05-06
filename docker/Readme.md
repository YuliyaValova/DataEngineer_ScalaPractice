# To create and run a docker image of the project you need:
1) Go to the directory where the project folder is located.
2) Place in it this Dockerfile.
3) Run this command to build an image:
	```sh
	docker build -t <IMAGE_NAME> .
	```
4) Run this command to run the image and start a job:
	```sh
	docker run <IMAGE_NAME> java -cp "./*" load.LoadStarter <PARAMS>
	```
	
# Example for docker run (for DB2 and COS)
- To build image:
	```sh 
	docker build -t scala .
	```
- To run the app:
	```sh
	docker run scala java -cp "./*" load.LoadStarter db2 jdbc:db2://.../bludb username password TEST
	```
