package load

import java.sql.Connection

trait DataLoader {

  def createTable(connection: Connection, tableName:String):Unit

  def addTableRecord(product_id:Int, product_group:Byte, year:Int, monthly_purchases:List[Int]):Boolean

}
