package generator

import java.sql.Connection


trait RecordsGenerator {

  /**
   * Generates the number of years for which product information is available
   * @return Int [0;4]
   */
  def generateCount():Int

  /**
   * Generates id for new product(autoincrement)
   * @return Int - productId
   */
  def generateId():Int

  /**
   * Generates group number for a product
   * @return Int [0;9]
   */
  def generateGroup():Int

  /**
   * Generates array of years for which product information is available
   * @param count - Number of generated years
   * @return Array[Int] - array of years
   */
  def generateYears(count:Int):Array[Int]

  /**
   * Generates monthly purchases
   * @return Array[Int] - array of Int-s in range [0;100000]
   */
  def generatePurchases():Array[Int]

  /**
   * Creates table in DB2 with specified number of rows
   * @param tableName - the name of the table to be stored in the database
   * @param connection - connection to database
   * @param rowNumber - number of generated records for the table
   */
  def generateTable(tableName:String, connection:Connection, rowNumber:Int):Unit

}
