package modelsTest

import models.MongoDatabaseConnector
import org.scalatest.{FlatSpec, Matchers, Tag}

/**
  * Created by Paul on 08/08/2016.
  */
class OrderTest extends FlatSpec with Matchers with MongoDatabaseConnector{

  val orderHistoryTest = getOrderHistory("x")
  val findOrderTest = findOrder(7)

  it should "Get the Customer's Order History" taggedAs FindOrderHistory in(
    orderHistoryTest.isEmpty shouldEqual false
    )

  it should "Get an Order" taggedAs FindOrder in(
    findOrderTest.equals(findOrder(7)) shouldEqual true

    )

}
object FindOrderHistory extends Tag("test.modelsTest.FindOrderHistory")
object FindOrder extends Tag("test.modelTest.FindOrder")