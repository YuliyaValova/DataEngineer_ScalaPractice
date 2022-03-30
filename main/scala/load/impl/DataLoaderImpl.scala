package load.impl

import load.DataLoader

import java.sql.{Connection, PreparedStatement}
import java.text.MessageFormat
import scala.::

case class DataLoaderImpl() extends DataLoader{

  override def addTableRecord(product_id: Int, product_group: Byte, year: Int, monthly_purchases: List[Int]): Boolean = ???

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
}
