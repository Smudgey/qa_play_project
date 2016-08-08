package modelsTest

import models.MongoDatabaseConnector
import org.scalatest.{FlatSpec, Matchers, Tag}

/**
  * Created by Paul on 08/08/2016.
  */
class ProductTest extends FlatSpec with Matchers with MongoDatabaseConnector{

  val productTest = findProductByID("701")


  it should "Find Product in Database" taggedAs FindProductSuccess in(
    productTest.itemID.equals("701") shouldEqual true
    )



}

object FindProductSuccess extends Tag("test.models.FindProductSuccess")
