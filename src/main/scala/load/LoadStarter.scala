package load
import connection.{DB2Connector, DB2ConnectorFromEnv}
import entity.TableRow
import load.impl.DataLoaderImpl

import java.sql.PreparedStatement

case object LoadStarter{

  def main(args: Array[String]): Unit = {

    val connection = DB2ConnectorFromEnv.getConnectionToDatabase()
    val dataLoader:DataLoader = new DataLoaderImpl
    dataLoader.createTable(connection,"Test")
    //dataLoader.dropTable(connection,"Test")
    val tableRow:TableRow = new TableRow(1,8,2015, List(1,2,3,4,5,6,7,8,9,10,11,12))
    dataLoader.addTableRecord("Test", connection, tableRow)
    val preparedStatement: PreparedStatement = connection.prepareStatement("Select * from Test;")
    val resultSet = preparedStatement.executeQuery()
    while (resultSet.next) {
      val name = resultSet.getString(1)
      val age = resultSet.getLong(2)
      println("Name: " + name + ", Age: " + age)
    }
    DB2Connector.retrieveConnection(connection)

  }
}
