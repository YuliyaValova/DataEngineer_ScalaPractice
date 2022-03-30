package load
import connection.DB2Connector
import load.impl.DataLoaderImpl

case object LoadStarter{

  def main(args: Array[String]): Unit = {

    val connection = DB2Connector.getConnectionToDatabase()
    val dataLoader:DataLoader = new DataLoaderImpl
    dataLoader.createTable(connection,"Sales")
    DB2Connector.retrieveConnection(connection)

  }
}
