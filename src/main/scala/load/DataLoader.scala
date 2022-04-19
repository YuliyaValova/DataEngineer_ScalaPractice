package load

import entity.TableRow

import java.sql.Connection


trait DataLoader {

  /**
   * Creates a table with specified name in DB2 on Cloud
   * @param connection - connection to database
   * @param tableName - name of the created table
   */
  def createTable(connection: Connection, tableName:String):Unit

  /**
   * Deletes a table from DB2
   * @param connection - connection to database
   * @param tableName - name of the table to be deleted
   */
  def dropTable(connection: Connection, tableName:String):Unit

  /**
   * Adds a record to the table in DB2
   * @param tableName - name of the table
   * @param connection - connection to database
   * @param row - row to be added
   * @return Boolean - True if success, otherwise False
   */
  def addTableRecord(tableName:String, connection: Connection, row:TableRow):Boolean

}
