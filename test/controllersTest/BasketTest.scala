package controllersTest

import models.{MongoDatabaseConnector, OrderLine}
import org.scalatest.{FlatSpec, Matchers, Tag}

/**
  * Created by Administrator on 08/08/2016.
  */
class BasketTest extends FlatSpec with Matchers with MongoDatabaseConnector{

  val orderLine = OrderLine

  val testProduct = findProductByID("701")
  val testProduct2 = findProductByID("702")
  val testProduct3 = findProductByID("703")
  val testProduct4 = findProductByID("704")

  it should "Add Item to basket" taggedAs AddToBasketSuccess in{

    val basketTest = orderLine.basket

    orderLine.addToBasket(OrderLine(testProduct.itemID, 1, testProduct.price))
    orderLine.addToBasket(OrderLine(testProduct2.itemID, 1, testProduct2.price))
    orderLine.addToBasket(OrderLine(testProduct3.itemID, 1, testProduct3.price))

    val product = basketTest.find(_.prodId == "701")

    product.isEmpty shouldEqual true

  }


  it should "Does not Add to Basket" taggedAs AddToBasketFail in{
    val basketTest = orderLine.basket

    orderLine.addToBasket(OrderLine(testProduct.itemID, 1, testProduct.price))
    orderLine.addToBasket(OrderLine(testProduct2.itemID, 1, testProduct2.price))
    orderLine.addToBasket(OrderLine(testProduct3.itemID, 1, testProduct3.price))

    val product = basketTest.find(_.prodId == "711")

    product.isEmpty shouldEqual true
  }



  it should "Remove Item 702 from basket" taggedAs RemoveFromBasketSuccess in{
    val basketTest = orderLine.basket

    orderLine.addToBasket(OrderLine(testProduct.itemID, 1, testProduct.price))
    orderLine.addToBasket(OrderLine(testProduct2.itemID, 1, testProduct2.price))
    orderLine.addToBasket(OrderLine(testProduct3.itemID, 1, testProduct3.price))
    orderLine.removeItem("702")

    val product = basketTest.find(_.prodId == "702")

    product.isEmpty shouldEqual true
  }



  it should "Does not remove item from basket" taggedAs RemoveFromBasketFail in{
    val basketTest = orderLine.basket

    orderLine.addToBasket(OrderLine(testProduct.itemID, 1, testProduct.price))
    orderLine.addToBasket(OrderLine(testProduct2.itemID, 1, testProduct2.price))
    orderLine.addToBasket(OrderLine(testProduct3.itemID, 1, testProduct3.price))
    val product = basketTest.find(_.prodId == "701")

    product.isEmpty shouldEqual true
  }



  it should "Get Total Cost" taggedAs TotalCostSuccess in{
    val basketTest = orderLine.basket

    orderLine.addToBasket(OrderLine(testProduct.itemID, 1, testProduct.price))
    orderLine.addToBasket(OrderLine(testProduct2.itemID, 1, testProduct2.price))
    orderLine.addToBasket(OrderLine(testProduct3.itemID, 1, testProduct3.price))
    orderLine.totalPrice(basketTest) == (testProduct.price + testProduct2.price + testProduct3.price) shouldEqual true
  }




}
object AddToBasketSuccess extends Tag("test.modelsTest.AddToBasketSuccess")
object AddToBasketFail extends Tag("test.modelsTest.AddToBasketFail")
object RemoveFromBasketSuccess extends Tag("test.modelsTest.RemoveFromBasketSuccess")
object RemoveFromBasketFail extends Tag("test.modelsTest.RemoveFromBasketFail")
object TotalCostSuccess extends Tag("test.modelsTest.TotalCostSuccess")
