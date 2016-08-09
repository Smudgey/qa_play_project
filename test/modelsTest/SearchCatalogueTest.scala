package modelsTest

import models.{MongoDatabaseConnector}
import org.scalatest.{FlatSpec, Matchers, Tag}


/**
  * Created by Administrator on 09/08/2016.
  */
class SearchCatalogueTest extends FlatSpec with Matchers with MongoDatabaseConnector {

  val filterItems = findByCategory("Gnome")
  it should "Filter List is Not Empty" taggedAs FindGnome in {
    filterItems.isEmpty shouldEqual false
  }

  it should "Check Empty" taggedAs CheckEmpty in {
    filterItems.nonEmpty shouldEqual true
  }

  it should "Find Gnome" taggedAs FindAGnome in{
    val gnome = filterItems.find(_.itemID == "701")
    gnome.isEmpty shouldEqual false
  }

}
object FindGnome extends Tag("test.modelsTest.FindGnome")
object CheckEmpty extends Tag("test.modelsTest.CheckEmpty")
object FindAGnome extends Tag("test.modelsTest.FindAGnome")