package connection

import java.sql.{Connection, DriverManager}
import java.util.Objects

case object DB2ConnectorFromEnv {

  /**
   * This class is used to work with connections to DB2 on Cloud. Properties for connection is loaded from
   * app environment
   */
  private val DRIVER = System.getenv("driver")
  private val URL = System.getenv("url")
  private val DATABASE_NAME = System.getenv("name")
  private val USERNAME = System.getenv("username")
  private val PASSWORD = System.getenv("password")

  /**
   * Prepares url for connection with credentials
   *
   * @return String - prepared URL
   */
  def prepareUrlForConnection(): String = {
    URL + DATABASE_NAME + ":user=" + USERNAME + ";password=" + PASSWORD + ";sslConnection=true;"
  }


  /**
   * Get connection to DB2 on Cloud
   *
   * @return Connection - working connection
   */
  def getConnectionToDatabase(): Connection = {
    try {
      val url = prepareUrlForConnection()
      Class.forName(DRIVER)
      DriverManager.getConnection(url)
    } catch {
      case e: Exception => {
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

