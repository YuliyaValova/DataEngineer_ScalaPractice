package connection

import java.sql.{Connection, DriverManager}
import java.util.{Objects, Properties}

case object DB2Connector {

  /**
   * This class is used to work with connections to DB2 on Cloud. Properties for connection is loaded from
   * resourses/db.properties
   */
  private val DATABASE_CONFIG_PATH = "db.properties"
  private val DRIVER = "database.driver"
  private val URL = "database.url"
  private val DATABASE_NAME = "database.name"
  private val USERNAME = "database.username"
  private val PASSWORD = "database.password"
  private var properties: Properties = null

  /**
   * Get property by key
   *
   * @param key of the property
   * @return String - value of the property
   */
  private def getProperty(key: String) = properties.getProperty(key)

  /**
   * Load properties from resourses/db.properties
   */
  private def loadProperties(): Unit = {
    val is = classOf[DB2Connector.type].getClassLoader.getResourceAsStream(DATABASE_CONFIG_PATH)
    properties = new Properties()
    properties.load(is)
    is.close()
  }

  /**
   * Prepares url for connection with credentials
   *
   * @return String - prepared URL
   */
  def prepareUrlForConnection(): String = {
    val url = getProperty(URL)
    val dbName = getProperty(DATABASE_NAME)
    val user = getProperty(USERNAME)
    val password = getProperty(PASSWORD)
    url + dbName + ":user=" + user + ";password=" + password + ";sslConnection=true;"
  }

  /**
   * Get driver from property file
   *
   * @return String - Driver name
   */
  def getDriverName(): String = {
    properties.getProperty(DRIVER)
  }

  /**
   * Get connection to DB2 on Cloud
   *
   * @return Connection - working connection
   */
  def getConnectionToDatabase(): Connection = {
    try {
      loadProperties()
      val driver = getDriverName()
      val url = prepareUrlForConnection()
      Class.forName(driver)
      DriverManager.getConnection(url)
    } catch {
      case e:Exception => {
        println("ERROR: get connection to DB - failed!")
        System.exit(1)
        null
      }
    }
  }

  /**
   * Closes connection
   *
   * @param connection to be closed
   */
  def retrieveConnection(connection: Connection): Unit = {
    if (Objects.nonNull(connection))
      connection.close()
  }

}

