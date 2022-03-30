package entity

class TableRow(var product_id:Int, var product_group:Byte, var year:Int, var monthly_purchases:List[Int]) {

  def setProductId(id:Int): Unit ={
    this.product_id = id
  }

  def setProductGroup(group:Byte): Unit ={
    this.product_group = group
  }

  def setYear(year:Int): Unit ={
    this.year = year
  }

  def setPurchases(purchases:List[Int]): Unit ={
    this.monthly_purchases = purchases
  }

}
