package modelsTest

import models.{MongoDatabaseConnector, Product_New}
import org.scalatest.{FlatSpec, Matchers, Tag}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 09/08/2016.
  */
class SearchCatalogueTest extends FlatSpec with Matchers with MongoDatabaseConnector{



//  val filterItems = findByCategory("Gnome")
//
//  def checkFilterItems():Boolean  = (
//  for(fI <- filterItems){
//    if(fI.category.)
//  }
//  )
//
//  it should "Find Gnomes" taggedAs FindGnome in(
//
//    )



}
object FindGnome extends Tag("test.modelsTest.FindGnome")