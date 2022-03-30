package connection
import java.sql.DriverManager
import java.sql.{Connection, PreparedStatement}
import java.util.{Objects, Properties}

case object DB2Connector{
  //todo: Validation and checking for misbehavior

  private val DATABASE_CONFIG_PATH = "db.properties"
  private val DRIVER = "database.driver"
  private val URL = "database.url"
  private val DATABASE_NAME = "database.name"
  private val USERNAME = "database.username"
  private val PASSWORD = "database.password"
  private var properties: Properties = null

  private def getProperty(key: String) = properties.getProperty(key)

  private def loadProperties(): Unit = {
    val is = classOf[DB2Connector.type].getClassLoader.getResourceAsStream(DATABASE_CONFIG_PATH)
    properties = new Properties()
    properties.load(is)
    is.close()
  }

  def prepareUrlForConnection(): String ={
    val url = getProperty(URL)
    val dbName = getProperty(DATABASE_NAME)
    val user = getProperty(USERNAME)
    val password = getProperty(PASSWORD)
    url + dbName + ":user=" + user + ";password=" + password + ";sslConnection=true;"
  }

  def getDriverName():String={
    properties.getProperty(DRIVER)
  }

  def getConnectionToDatabase(): Connection ={
    loadProperties()
    val driver = getDriverName()
    val url = prepareUrlForConnection()
    Class.forName(driver)
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

