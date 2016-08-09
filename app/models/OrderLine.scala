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
case class OrderLine(prodId: String, var quantity: Int, price: Double) {}

object OrderLine extends Formatter with MongoDatabaseConnector {

  var basket = new ArrayBuffer[OrderLine]
  var size = getSize

  implicit object OrderLineReader extends BSONDocumentReader[OrderLine] {
    def read(doc: BSONDocument): OrderLine =
      OrderLine(
        doc.getAs[String]("productID").get,
        doc.getAs[Int]("quantity").get,
        priceFormat(doc.getAs[Double]("price").get)
      )
  }

  implicit object OrderLineWriter extends BSONDocumentWriter[OrderLine] {
    def write(oln: OrderLine): BSONDocument = {
      BSONDocument(
        "productID" -> oln.prodId,
        "quantity" -> oln.quantity,
        "price" -> oln.price
      )
    }
  }

  def returnProduct(itemID: String): Product = {
    findProductByID(itemID)
  }

  def addToBasket(oli: OrderLine): Unit = {

    val p = findProductByID(oli.prodId)

    //Do product stock validation here
    if (oli.quantity > p.stock) {

      //TODO send some response that request cant be fulfilled

    } else {
      p.decrementStock(oli.quantity)
      size += oli.quantity

      def addOrIncrease(bsk: ArrayBuffer[OrderLine], oli2: OrderLine): Unit = {
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
    def accumulate(bsk: ArrayBuffer[OrderLine], total: Int): Int = {
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

  def totalPrice(bsk: ArrayBuffer[OrderLine]): Double = {
    def addToTot(bsk: ArrayBuffer[OrderLine], total: Double): Double = {
      if (bsk.isEmpty)
        priceFormat(total)
      else
        addToTot(bsk.tail, total + bsk.head.price * bsk.head.quantity)
    }
    addToTot(bsk, 0)
  }

  def clear : Unit = {
    for(ol <- basket) {
    val product = Product.findProductByID(ol.prodId)
    product.stock += ol.quantity
  }
    basket.clear()
    size = basket.size
  }


}
