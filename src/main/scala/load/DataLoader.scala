package load

import entity.TableRow

import java.sql.Connection


trait DataLoader {

  def createTable(connection: Connection, tableName:String):Unit

  def dropTable(connection: Connection, tableName:String):Unit

  def addTableRecord(tableName:String, connection: Connection, row:TableRow):Boolean

}
