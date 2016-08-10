package modelsTest

import models.{MongoDatabaseConnector}
import org.scalatest.{FlatSpec, Matchers, Tag}

/**
  * Created by Paul on 08/08/2016.
  */
class ProductTest extends FlatSpec with Matchers with MongoDatabaseConnector{

  val productTest = findProductByID("701")
  val productTest2 = findProductByID("702")
//  val product1 = new Product_New()


  it should "Find Product in Database" taggedAs FindProductSuccess in{
    productTest.itemID.equals("701") shouldEqual true
  }

  productTest.incrementStock(1)
  it should "Increase quantity of stock" taggedAs IncrementStock in{
    productTest.stock == 13 shouldEqual true
  }

  productTest2.decrementStock(1)
  it should "Decrease quantity of stock" taggedAs DecrementStock in{
    productTest2.stock == 11 shouldEqual true
  }

}

object FindProductSuccess extends Tag("test.modelsTest.FindProductSuccess")
object IncrementStock extends Tag("test.modelsTest.IncrementStock")
object DecrementStock extends Tag("test.modelsTest.Decrement")
