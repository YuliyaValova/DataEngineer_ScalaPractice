# DataEngineer_ScalaPractice
<b>To create a table with 20k sample records you need:</b>
1) Clone the current version of the repository
2) Add a driver (for connection to the database) to External Libraries of the project (the db2jcc4.jar file is already included in the project structure)
   You can do it e.g. Ctrl+Shift+Alt+S -> Modules -> Dependencies -> click "+" -> JARs or Directories -> Choose "db2jcc4.jar" -> "Ok"
3) Add your database info: driver, db name, url for connection and credentials using one of two ways:
   <br>
   a) Update "resourses.db.properties" file.
   <br>
   ![image](https://user-images.githubusercontent.com/73712980/160850210-d12b9dc2-8967-46af-8139-c55b2406904c.png)
   <br>
   b) Start you application with configured environment variables. It may look like e.g.:
   <i>url=jdbc:db2://.../;driver=com.ibm.db2.jcc.DB2Driver;name=...;username=...;password=...</i>
   <br>
   ![image](https://user-images.githubusercontent.com/73712980/160850444-e3527d57-8725-4b73-8f8c-cb85b65d8d2e.png)
   <br>
4) To start running application, run main method of "load.LoadStarter" class. 
   <br>
   If you use 3-a) method to add your database info, you need to change this
   <br>
   <i>val connection = DB2ConnectorFromEnv.getConnectionToDatabase()</i>
   <br>
   to this
   <br>
   <i>val connection = DB2Connector.getConnectionToDatabase()</i>
   <br>
   If you use 3-b) method you don't have to do anything.
   <br>
   You can change a name of generated table by updating first parameter here:
   <br>
   <i> generator.generateTable("Sales_test", connection, 20000)</i>
   <br>
   Populating the data table can take a long time.
 

