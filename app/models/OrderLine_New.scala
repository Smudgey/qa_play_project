/**
  * Created by Administrator on 02/08/2016.
  */
package models

import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter}

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marko on 02/08/2016.
  *
  * The orderline class takes in a product, an order quantitry and a porousware quantity (not used)  A product is passed rather than a product ID so that the objects variables can be called
  * within this class.
  */
case class OrderLine_New(prodId: String, var quantity: Int, price: Double) {}

object OrderLine_New extends Formatter with MongoDatabaseConnector {

  var basket = new ArrayBuffer[OrderLine_New]
  var size = getSize

  implicit object OrderLineReader extends BSONDocumentReader[OrderLine_New] {
    def read(doc: BSONDocument): OrderLine_New =
      OrderLine_New(
        doc.getAs[String]("productID").get,
        doc.getAs[Int]("quantity").get,
        priceFormat(doc.getAs[Double]("price").get)
      )
  }

  implicit object OrderLineWriter extends BSONDocumentWriter[OrderLine_New] {
    def write(oln: OrderLine_New): BSONDocument = {
      BSONDocument(
        "productID" -> oln.prodId,
        "quantity" -> oln.quantity,
        "price" -> oln.price
      )
    }
  }

  def returnProduct(itemID: String): Product_New = {
    findProductByID(itemID)
  }

  def addToBasket(oli: OrderLine_New): Unit = {

    val p = findProductByID(oli.prodId)

    //Do product stock validation here
    if (oli.quantity > p.stock) {

      //TODO send some response that request cant be fulfilled

    } else {
      p.decrementStock(oli.quantity)
      size += oli.quantity

      def addOrIncrease(bsk: ArrayBuffer[OrderLine_New], oli2: OrderLine_New): Unit = {
        if (bsk.isEmpty) {
          basket += oli2
        } else if (bsk.head.prodId == oli2.prodId) {

          bsk.head.quantity += oli2.quantity

        } else {
          addOrIncrease(bsk.tail, oli2)
        }
      }
      addOrIncrease(basket, oli)
    }
  }

  def getSize: Int = {
    def accumulate(bsk: ArrayBuffer[OrderLine_New], total: Int): Int = {
      if (bsk.isEmpty)
        total
      else
        accumulate(bsk.tail, total + bsk.head.quantity)
    }
    accumulate(basket, 0)
  }

  def findOrderLine(pid: String) = basket.find(_.prodId == pid)

  def removeItem(pid: String): Unit = {

    println("Remove Item: "+ pid)
    size -= findOrderLine(pid).get.quantity
    basket.remove(basket.indexOf(findOrderLine(pid).get))
  }

  def totalPrice(bsk: ArrayBuffer[OrderLine_New]): Double = {
    def addToTot(bsk: ArrayBuffer[OrderLine_New], total: Double): Double = {
      if (bsk.isEmpty)
        priceFormat(total)
      else
        addToTot(bsk.tail, total + bsk.head.price * bsk.head.quantity)
    }
    addToTot(bsk, 0)
  }

  def clear : Unit = {
    for(ol <- basket) {
    val product = Product_New.findProductByID(ol.prodId)
    product.stock += ol.quantity
  }
    basket.clear()
    size = basket.size
  }


}
