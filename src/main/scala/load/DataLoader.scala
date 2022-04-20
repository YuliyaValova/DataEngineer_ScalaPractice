package load

import entity.TableRow

import java.sql.{Connection, ResultSet}
import java.text.MessageFormat

case class DataLoader() {

  def addTableRecord(tableName: String, connection: Connection, row: TableRow): Boolean = {
    val query = "insert into " + tableName + " values(" + row.toString + ");"
    val statement = connection.createStatement()
    statement.execute(query)
  }

  def createTable(connection: Connection, tableName: String): Unit = {
    val query =
      """
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

  def dropTable(connection: Connection, tableName: String): Unit = {
    val query =
      """
        |drop table {0};
                  """.stripMargin
    val preparedStatement = connection.createStatement()
    preparedStatement.execute(MessageFormat.format(query, tableName))
  }

  def selectAll(connection: Connection, tableName: String): Unit = {
    val query =
      """
        |select count(*) from {0};
                  """.stripMargin
    val preparedStatement = connection.createStatement()
    val result: ResultSet = preparedStatement.executeQuery(MessageFormat.format(query, tableName))
    while (result.next()){
      println(result.getLong(1))
    }
  }
}
