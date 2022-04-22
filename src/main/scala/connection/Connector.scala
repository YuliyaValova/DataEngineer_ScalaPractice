package connection

import java.sql.{Connection, DriverManager}
import java.util.Objects

case object Connector {

  /**
   * This class is used to work with connections to DB2 on Cloud. Properties for connection is loaded from
   * app environment
   */


  /**
   * Get connection to DB2 on Cloud
   * @return Connection - working connection
   */
  def getConnectionToDatabase(dbType: String, url:String, username:String, password:String): Connection = {
    try {
      var connectionUrl:String = null
      var driverName = ""
      dbType match {
        case "db2" => {
          driverName = "com.ibm.db2.jcc.DB2Driver"
          connectionUrl =  url + ":user=" + username + ";password=" + password + ";sslConnection=true;"
        }
        case "mysql" => {
          driverName = "com.mysql.cj.jdbc.Driver"
          connectionUrl = url + "?user=" + username + "&password=" + password + "&useSSL=false"
        }
        case _ => {
          println("Invalid database type.")
          System.exit(1)
        }
      }
      Class.forName(driverName)
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

