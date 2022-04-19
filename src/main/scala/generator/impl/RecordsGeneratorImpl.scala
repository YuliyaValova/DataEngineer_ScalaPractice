package generator.impl

import entity.TableRow
import generator.RecordsGenerator
import load.DataLoader
import load.impl.DataLoaderImpl

import java.sql.Connection
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

case class RecordsGeneratorImpl() extends RecordsGenerator {

  private val loader: DataLoader = new DataLoaderImpl
  private var idCounter = 0


  override def generateCount(): Int = {
    val random = new Random()
    random.nextInt((4 - 1) + 1) + 1 // [1;4] as there are only 4 years available (2015-2018)
  }

  override def generateId(): Int = {
    idCounter += 1
    idCounter
  }

  override def generateGroup(): Int = {
    val random = new Random()
    random.nextInt(10) //[0;9]
  }

  //todo optimal code for sampling without repetitions
  override def generateYears(count: Int): Array[Int] = {
    val years = new Array[Int](4)
    val allowedValues = Array (2015,2016,2017,2018)
    for (currentPosition <- 0 until count) {
      years(currentPosition) = allowedValues(currentPosition)
    }
    years
  }

  override def generatePurchases(): Array[Int] = {
    val random = new Random()
    val purchases: Array[Int] = new Array[Int](12)
    for (currentPosition <- 1 to 12) {
      purchases(currentPosition - 1) = random.nextInt(100000) //[0;100000]
    }
    purchases
  }

  override def generateTable(tableName: String, connection: Connection, rowNumber: Int): Unit = {
    var currentRowNumber = 0
    try {
      loader.createTable(connection, tableName)
    } catch {
      case e:Exception => {
        println("ERROR: table cannot be created.")
        System.exit(1)
      }
    }
    while (currentRowNumber < rowNumber){
      val recordsCount = generateCount()
      val productId = generateId()
      val productGroup = generateGroup()
      val years = generateYears(recordsCount)
      val productRows: ArrayBuffer[TableRow] = ArrayBuffer[TableRow]()
      var currentYearPosition = 0
      for(rowIndex<-0 until  recordsCount){
        productRows += new TableRow(productId,productGroup,years(currentYearPosition),generatePurchases())
        currentYearPosition+=1
      }
      try {
        productRows.foreach(row => loader.addTableRecord(tableName, connection, row))
      } catch {
        case e:Exception => {
          println("ERROR: Error adding entry. Failed row number: " + currentRowNumber)
        }
      }
      currentRowNumber+=recordsCount
    }
  }
}

