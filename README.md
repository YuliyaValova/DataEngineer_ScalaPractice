# DataEngineer_ScalaPractice
<b>In order to create a connection to the DB2 on Cloud you must:</b>
1) Clone the current version of the repository
2) Add a driver (for connection to the database) to External Libraries of the project (the db2jcc4.jar file is already included in the project structure)
   You can do it e.g. Ctrl+Shift+Alt+S -> Modules -> Dependencies -> click "+" -> JARs or Directories -> Choose "db2jcc4.jar" -> "Ok"
3) To add your database info: driver, db name, url for connection and credentials there are two ways:
   a) Update "resourses.db.properties" file and use methods from "connection.DB2Connector" class to get or retrieve connections to your db.
   b) Start you application with configured environment variables. It may look like e.g.:
   <i>url=jdbc:db2://.../;driver=com.ibm.db2.jcc.DB2Driver;name=...;username=...;password=...</i>
   After that you can use methods from "connection.DB2ConnectorFromEnv" class to get or retrieve connections to your db.
