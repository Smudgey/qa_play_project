package models

import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.util.{Failure, Success}


/**
  * Created by Marko on 02/08/2016.
  */
case class Order_New(orderID: Int, accountID: String, date: String, time: String, orderStatus: String, paymentMethod: String, orderLines: Array[OrderLine_New], totalPrice: Double, rating: Int) {}

object Order_New extends MongoDatabaseConnector{

  implicit object OrderReader extends BSONDocumentReader[Order_New] {
    def read(doc: BSONDocument): Order_New =
      Order_New(
        doc.getAs[Int]("orderID").get,
        doc.getAs[String]("accountID").get,
        doc.getAs[String]("date").get,
        doc.getAs[String]("time").get,
        doc.getAs[String]("orderStatus").get,
        doc.getAs[String]("paymentMethod").get,
        doc.getAs[Array[OrderLine_New]]("orderLines").get,
        doc.getAs[Double]("totalPrice").get,
        doc.getAs[Int]("rating").get
      )
  }

  implicit object OrderWriter extends BSONDocumentWriter[Order_New] {
    def write(on: Order_New): BSONDocument = {
      BSONDocument(
        "orderID" -> on.orderID,
        "accountID" -> on.accountID,
        "date" -> on.date,
        "time" -> on.time,
        "orderStatus" -> on.orderStatus,
        "paymentMethod" -> on.paymentMethod,
        "orderLines" -> on.orderLines,
        "totalPrice" -> on.totalPrice,
        "rating" -> on.rating
      )
    }
  }

  def create(orderCollection: BSONCollection, order: Order_New)(implicit ec: ExecutionContext, writer: BSONDocumentWriter[Order_New]): Future[Unit] = {
    val writeResult = orderCollection.insert(order)
    writeResult.map(_ => {
      /*presumably this is just using the writer implicitly to know how to output every key we loop through
      * as a valid entry in a BSONDocument as an Account_New object.... I'll try to improve this description later*/
    })
  }

  def setStarRating(orderID: String, rating: Int): Unit = {
    connectToDatabase(CollectionNames.ORDER_COLLECTION, DatabaseNames.ORDERS_DATABASE).onComplete {
      case Success(result) =>
        result.update(BSONDocument("orderID" -> orderID), BSONDocument("$set" -> BSONDocument("rating" -> rating)))
      case Failure(fail) =>
        throw fail
    }
  }
}