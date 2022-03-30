package connection

import java.sql.{Connection, DriverManager, PreparedStatement}
import java.util.{Objects, Properties}

case object DB2ConnectorFromEnv{
  //todo: Validation and checking for misbehavior
  private val DRIVER = System.getenv("driver")
  private val URL = System.getenv("url")
  private val DATABASE_NAME = System.getenv("name")
  private val USERNAME = System.getenv("username")
  private val PASSWORD = System.getenv("password")

  def prepareUrlForConnection(): String ={
    URL + DATABASE_NAME + ":user=" + USERNAME + ";password=" + PASSWORD + ";sslConnection=true;"
  }

  def getConnectionToDatabase(): Connection ={
    val url = prepareUrlForConnection()
    Class.forName(DRIVER)
    DriverManager.getConnection(url)
  }

  def retrieveConnection(connection: Connection): Unit ={
    if(Objects.nonNull(connection))
      connection.close()
  }

    def main(args: Array[String]): Unit = {
        val connection = getConnectionToDatabase()
        val preparedStatement: PreparedStatement = connection.prepareStatement("Select * from Employees;")
        val resultSet = preparedStatement.executeQuery
        while (resultSet.next) {
          val name = resultSet.getString(1)
          val age = resultSet.getLong(2)
          println("Name: " + name + ", Age: " + age)
        }
        retrieveConnection(connection)
    }
}

