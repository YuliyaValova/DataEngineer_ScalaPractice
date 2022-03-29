# DataEngineer_ScalaPractice
<b>In order to create a connection to the DB2 on Cloud you must:</b>
1) Clone the current version of the repository
2) Add a driver (for connection to the database) to External Libraries of the project (the db2jcc4.jar file is already included in the project structure)
   You can do it e.g. Ctrl+Shift+Alt+S -> Modules -> Dependencies -> click "+" -> JARs or Directories -> Choose "db2jcc4.jar" -> "Ok"
3) Update resourses.db.properties file with your database info: driver, db name, url for connection and credentials.
Now you can use methods from "connection.GetConnectiontoDb2" class to get or retrieve connections to your db.
