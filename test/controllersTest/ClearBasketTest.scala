package controllersTest

import models.{MongoDatabaseConnector, OrderLine}
import org.scalatest.{FlatSpec, Matchers, Tag}

/**
  * Created by Administrator on 09/08/2016.
  */
class ClearBasketTest extends FlatSpec with Matchers with MongoDatabaseConnector{

  val orderLine = OrderLine

  val basketTest = orderLine.basket

  val testProduct = findProductByID("701")
  val testProduct2 = findProductByID("702")
  val testProduct3 = findProductByID("703")

  orderLine.addToBasket(OrderLine(testProduct.itemID, 1, testProduct.price))
  orderLine.addToBasket(OrderLine(testProduct2.itemID, 1, testProduct2.price))
  orderLine.addToBasket(OrderLine(testProduct3.itemID, 1, testProduct3.price))

  println(basketTest.size)


  //Clearing Basket
  basketTest.clear()
  it should "Clear all items from basket" taggedAs ClearingBasket in(

    basketTest.isEmpty shouldEqual true

    )

  println(basketTest.size)
}

object ClearingBasket extends Tag("test.modelsTest.ClearingBasket")
