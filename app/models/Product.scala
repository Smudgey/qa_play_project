package models

import reactivemongo.bson.{BSONDocument, BSONDocumentReader}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

/**
  * Created by Administrator on 03/08/2016.
  */
case class Product(itemID: String, product: String, images: Array[String], category: Array[String], description: String, var stock: Int, price: Double) {

  def hasXAvailable(x: Int): Boolean = {
    this.stock >= x
  }

  // URL: String
  def decrementStock(quantity: Int): Unit = {
    //Add stock validation here?
    this.stock -= quantity
  }

  def incrementStock(quantity: Int): Unit = {
    //Add stock validation here?
    this.stock += quantity
  }
}


object Product extends Formatter with MongoDatabaseConnector{

  implicit object productReader extends BSONDocumentReader[Product] {
    def read(doc: BSONDocument): Product =
      Product(
        doc.getAs[String]("itemID").get,
        doc.getAs[String]("Product").get,
        doc.getAs[Array[String]]("images").get,
        doc.getAs[Array[String]]("Category").get,
        doc.getAs[String]("Description").get,
        doc.getAs[Int]("Stock").get,
        priceFormat(doc.getAs[Double]("price").get)
      )
  }

  def updateDatabaseStock(pid: String, stock: Int): Unit = {
    connectToDatabase(CollectionNames.PRODUCT_COLLECTION, DatabaseNames.ORDERS_DATABASE).onComplete {
      case Success(result) =>
        val query = BSONDocument("itemID" -> pid)
        val modifier = BSONDocument("$set" -> BSONDocument("Stock" -> stock))
        result.update(query, modifier)

      case Failure(fail) =>
        println("stock update failed")
    }
  }

}
