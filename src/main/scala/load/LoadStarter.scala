package load
import connection.{DB2Connector, DB2ConnectorFromEnv}
import generator.RecordsGenerator
import generator.impl.RecordsGeneratorImpl
import load.impl.DataLoaderImpl

case object LoadStarter{

  def main(args: Array[String]): Unit = {

    val connection = DB2ConnectorFromEnv.getConnectionToDatabase()
    val generator:RecordsGenerator = new RecordsGeneratorImpl
    val loader = new DataLoaderImpl
    //loader.dropTable(connection,"Sales_test")
    generator.generateTable("Sales_test", connection, 20000)
    /*
    val preparedStatement: PreparedStatement = connection.prepareStatement("Select * from Test;")
    val resultSet = preparedStatement.executeQuery()
    while (resultSet.next) {
      val name = resultSet.getString(1)
      val age = resultSet.getLong(2)
      println("Name: " + name + ", Age: " + age)
    }*/
    DB2Connector.retrieveConnection(connection)
  }
}
