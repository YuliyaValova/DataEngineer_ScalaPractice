package generator

import java.sql.Connection


trait RecordsGenerator {

  //Generates the number of years for which product information is available
  def generateCount():Int

  //Generates id for new product(autoincrement)
  def generateId():Int

  //Generates group number for a product
  def generateGroup():Int

  //Generates array of years for which product information is available
  def generateYears(count:Int):Array[Int]

  //Generates monthly purchases
  def generatePurchases():Array[Int]

  //Generates table for specified row number
  def generateTable(tableName:String, connection:Connection, rowNumber:Int):Unit

}
