package load.impl

import entity.TableRow
import generator.RecordsGenerator
import generator.impl.RecordsGeneratorImpl
import load.DataLoader

import java.sql.Connection
import java.text.MessageFormat

case class DataLoaderImpl() extends DataLoader{

  override def addTableRecord(tableName:String, connection: Connection, row:TableRow): Boolean = {
    val query = "insert into " + tableName + " values(" + row.toString + ");"
    val statement = connection.createStatement()
    statement.execute(query)
  }

  override def createTable(connection: Connection, tableName: String): Unit = {
    val query = """
                      |create table {0} (
                      |product_id int not null,
                      |product_group smallint,
                      |year int not null,
                      |month_1 int,
                      |month_2 int,
                      |month_3 int,
                      |month_4 int,
                      |month_5 int,
                      |month_6 int,
                      |month_7 int,
                      |month_8 int,
                      |month_9 int,
                      |month_10 int,
                      |month_11 int,
                      |month_12 int,
                      |primary key (product_id, year)
                      |);
                    """.stripMargin
    val preparedStatement = connection.createStatement()
    preparedStatement.execute(MessageFormat.format(query, tableName))
  }

  override def dropTable(connection: Connection, tableName: String): Unit = {
    val query =   """
                  |drop table {0};
                  """.stripMargin
    val preparedStatement = connection.createStatement()
    preparedStatement.execute(MessageFormat.format(query, tableName))
  }
}
