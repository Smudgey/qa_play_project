package controllersTest

import models.{MongoDatabaseConnector, OrderLine_New, Product_New}
import org.scalatest.{FlatSpec, Matchers, Tag}

/**
  * Created by Administrator on 09/08/2016.
  */
class CheckOrdersTest extends FlatSpec with Matchers with MongoDatabaseConnector{

  val orderLine = OrderLine_New

  val basketTest = orderLine.basket

  val expensiveProduct = Product_New("1001","200ft Tall Gnome", Array[String]("image"),Array[String]("GiantGnome"),"Showoff the biggest gnome", 4, 5999.99)

  val orderLineTest = new OrderLine_New(expensiveProduct.itemID, 2, expensiveProduct.price)

  basketTest += orderLineTest

  it should "Check Total Cost of Orders above 10000" taggedAs CheckTotalCost in(
    orderLine.totalPrice(basketTest) > 10000.00 shouldEqual true
    )

}
object CheckTotalCost extends Tag("test.modelsTest.Reject10000")