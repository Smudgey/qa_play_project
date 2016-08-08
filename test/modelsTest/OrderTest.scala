package modelsTest

import models.MongoDatabaseConnector
import org.scalatest.{FlatSpec, Matchers, Tag}

/**
  * Created by Paul on 08/08/2016.
  */
class OrderTest extends FlatSpec with Matchers with MongoDatabaseConnector{

  val orderTest = getOrderHistory("x")

  it should "Get the Customer's Order History" taggedAs FindOrder in(
    orderTest.isEmpty shouldEqual false
    )

}
object FindOrder extends Tag("test.modelsTest.FindOrder")