package models

import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.util.{Failure, Success}


/**
  * Created by Marko on 02/08/2016.
  */
case class Order(orderID: Int, accountID: String, date: String, time: String, orderStatus: String, paymentMethod: String, orderLines: Array[OrderLine], totalPrice: Double, rating: Int) {}

object Order extends MongoDatabaseConnector{

  implicit object OrderReader extends BSONDocumentReader[Order] {
    def read(doc: BSONDocument): Order =
      Order(
        doc.getAs[Int]("orderID").get,
        doc.getAs[String]("accountID").get,
        doc.getAs[String]("date").get,
        doc.getAs[String]("time").get,
        doc.getAs[String]("orderStatus").get,
        doc.getAs[String]("paymentMethod").get,
        doc.getAs[Array[OrderLine]]("orderLines").get,
        doc.getAs[Double]("totalPrice").get,
        doc.getAs[Int]("rating").get
      )
  }

  implicit object OrderWriter extends BSONDocumentWriter[Order] {
    def write(on: Order): BSONDocument = {
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

  def create(orderCollection: BSONCollection, order: Order)(implicit ec: ExecutionContext, writer: BSONDocumentWriter[Order]): Future[Unit] = {
    val writeResult = orderCollection.insert(order)
    writeResult.map(_ => {
      /*presumably this is just using the writer implicitly to know how to output every key we loop through
      * as a valid entry in a BSONDocument as an Account object.... I'll try to improve this description later*/
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