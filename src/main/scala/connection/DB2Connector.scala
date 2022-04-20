package connection

import java.sql.{Connection, DriverManager}
import java.util.Objects

case object DB2Connector {

  /**
   * This class is used to work with connections to DB2 on Cloud. Properties for connection is loaded from
   * app environment
   */


  /**
   * Get connection to DB2 on Cloud
   * @return Connection - working connection
   */
  def getConnectionToDatabase(url:String, username:String, password:String): Connection = {
    try {
      val connectionUrl =  url + ":user=" + username + ";password=" + password + ";sslConnection=true;"
      Class.forName("com.ibm.db2.jcc.DB2Driver")
      DriverManager.getConnection(connectionUrl)
    } catch {
      case e: Exception => {
        println("ERROR: get connection to DB - failed!")
        e.printStackTrace()
        System.exit(1)
        null
      }
    }
  }

  /**
   * Closes connection
   * @param connection to be closed
   */
  def retrieveConnection(connection: Connection): Unit = {
    if (Objects.nonNull(connection))
      connection.close()
  }

}

