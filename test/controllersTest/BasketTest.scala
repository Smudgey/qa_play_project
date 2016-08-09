package controllersTest

import models.{MongoDatabaseConnector, OrderLine_New}
import org.scalatest.{FlatSpec, Matchers, Tag}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 08/08/2016.
  */
class BasketTest extends FlatSpec with Matchers with MongoDatabaseConnector{

  val orderLine = OrderLine_New

  val basketTest = orderLine.basket

  val testProduct = findProductByID("701")
  val testProduct2 = findProductByID("702")
  val testProduct3 = findProductByID("703")
  val testProduct4 = findProductByID("704")

//  val orderLineTest = OrderLine_New(testProduct.itemID, 1, testProduct.price)
//  val orderLineTest2 =  OrderLine_New(testProduct2.itemID, 1, testProduct2.price)
//  val orderLineTest3 = OrderLine_New(testProduct3.itemID, 1, testProduct3.price)

  orderLine.addToBasket(OrderLine_New(testProduct.itemID, 1, testProduct.price))
  orderLine.addToBasket(OrderLine_New(testProduct2.itemID, 1, testProduct2.price))
  orderLine.addToBasket(OrderLine_New(testProduct3.itemID, 1, testProduct3.price))

  it should "Add Item to basket" taggedAs AddToBasketSuccess in(

    orderLine.findOrderLine("701").get.prodId == findProductByID("701").itemID shouldEqual true

    )

  it should "Does not Add to Basket" taggedAs AddToBasketFail in(
    orderLine.findOrderLine("799").isEmpty shouldEqual true
    )


  orderLine.removeItem("702")
  it should "Remove Item 702 from basket" taggedAs RemoveFromBasketSuccess in(
    orderLine.findOrderLine("702").isEmpty shouldEqual true
    )

  it should "Does not remove item from basket" taggedAs RemoveFromBasketFail in(
    orderLine.findOrderLine("701").isEmpty shouldEqual false
    )

  it should "Get Total Cost" taggedAs TotalCostSuccess in(
    orderLine.totalPrice(basketTest) == (testProduct.price + testProduct3.price) shouldEqual true
    )

}
object AddToBasketSuccess extends Tag("test.models.AddToBasketSuccess")
object AddToBasketFail extends Tag("test.models.AddToBasketFail")
object RemoveFromBasketSuccess extends Tag("test.models.RemoveFromBasketSuccess")
object RemoveFromBasketFail extends Tag("test.models.RemoveFromBasketFail")
object TotalCostSuccess extends Tag("test.models.TotalCostSuccess")