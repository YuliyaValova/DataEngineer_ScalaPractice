package generator

import entity.TableRow
import load.DataLoader

import java.sql.Connection
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

case class RecordsGenerator() {

  private val loader = new DataLoader
  private var idCounter = 0

  /**
   * Generates the number of years for which product information is available
   * @return Int [0;4]
   */
  def generateCount(): Int = {
    val random = new Random()
    random.nextInt((4 - 1) + 1) + 1 // [1;4] as there are only 4 years available (2015-2018)
  }

  /**
   * Generates id for new product(autoincrement)
   * @return Int - productId
   */
  def generateId(): Int = {
    idCounter += 1
    idCounter
  }

  /**
   * Generates group number for a product
   * @return Int [0;9]
   */
  def generateGroup(): Int = {
    val random = new Random()
    random.nextInt(10) //[0;9]
  }

  /**
   * Generates array of years for which product information is available
   * @param count - Number of generated years
   * @return Array[Int] - array of years
   */
  def generateYears(count: Int): Array[Int] = {
    val years = new Array[Int](4)
    val allowedValues = Array(2015, 2016, 2017, 2018)
    for (currentPosition <- 0 until count) {
      years(currentPosition) = allowedValues(currentPosition)
    }
    years
  }

  /**
   * Generates monthly purchases
   * @return Array[Int] - array of Int-s in range [0;100000]
   */
  def generatePurchases(): Array[Int] = {
    val random = new Random()
    val purchases: Array[Int] = new Array[Int](12)
    for (currentPosition <- 1 to 12) {
      purchases(currentPosition - 1) = random.nextInt(100000) //[0;100000]
    }
    purchases
  }

  /**
   * Creates table in DB2 with specified number of rows
   * @param tableName  - the name of the table to be stored in the database
   * @param connection - connection to database
   * @param rowNumber  - number of generated records for the table
   */
  def generateTable(connection: Connection, tableName:String, rowsNumber:Int): Unit = {
    var query = ""
    var batchSize = 0
    var currentRowNumber = 0

    try {
      loader.createTable(connection, tableName)
    } catch {
      case e: Exception => {
        println("ERROR: table cannot be created.")
        e.printStackTrace()
        System.exit(1)
      }
    }

    while (currentRowNumber < rowsNumber) {
      if(batchSize >= 1000){
        loader.loadRecords(tableName,connection, query)
        batchSize = 0
        query = ""
      }
      val recordsCount = generateCount()
      val productId = generateId()
      val productGroup = generateGroup()
      val years = generateYears(recordsCount)
      val productRows: ArrayBuffer[TableRow] = ArrayBuffer[TableRow]()
      var currentYearPosition = 0
      for (rowIndex <- 0 until recordsCount) {
        productRows += new TableRow(productId, productGroup, years(currentYearPosition), generatePurchases())
        currentYearPosition += 1
      }
      try {
        productRows.foreach(row => query = query + "(" + row.toString + "),")
      } catch {
        case e: Exception => {
          println("ERROR: Error adding entry. Failed row number: " + currentRowNumber)
          e.printStackTrace()
          System.exit(1)
        }
      }
      currentRowNumber += recordsCount
      batchSize += recordsCount
    }
    loader.loadRecords(tableName, connection, query)
  }
}
