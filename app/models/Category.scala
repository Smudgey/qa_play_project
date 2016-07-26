package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marko on 12/07/2016.
  */

object Category extends Enumeration {




  val Gnome           = Value
  val GnomeSmall      = Value
  val GnomeBig        = Value
  val GnomeMedium     = Value

  val Clothing        = Value
  val ClothingShoe    = Value
  val ClothingJumper  = Value
  val ClothingTrouser = Value
  val ClothingUnderwear = Value
  val ClothingHat     = Value

  val Plant           = Value
  val PlantTree       = Value
  val PlantPotted     = Value
  val PlantPot        = Value

  val BirdItem        = Value
  val BirdHouse       = Value
  val BirdFeeder      = Value

  val Furniture       = Value
  val FurnitureGazebo = Value
  val FurnitureTable  = Value
  val FurnitureChair  = Value

  val Tool            = Value
  val ToolRake        = Value
  val ToolSpade       = Value
  val ToolAxe         = Value

  val parentCats      = ArrayBuffer(Gnome, Clothing, Plant, BirdItem, Furniture, Tool)
  val categoryMap     = Map(
          Gnome     -> List(GnomeBig, GnomeMedium, GnomeSmall),
          Clothing  -> List(ClothingShoe, ClothingUnderwear, ClothingHat, ClothingJumper, ClothingTrouser),
          Plant     -> List(PlantTree, PlantPotted, PlantPot),
          Furniture -> List(FurnitureChair, FurnitureTable, FurnitureGazebo),
          Tool      -> List(ToolAxe, ToolRake, ToolSpade)
  )

//  def categorise: Map[Category.Value, ArrayBuffer[Category.Value]] ={
//    var catMap = Map[Category.Value, ArrayBuffer[Category.Value]]()
//    for (parent <- Category.parentCats) {
//
//      val arr = ArrayBuffer[Category.Value]()
//      for (p <- Product.listByCat(parent)){
//        println(Product.listByCat(parent))
//        p.category.withFilter(cat => cat != parent).withFilter(cat => !arr.contains(cat)).foreach(cat => arr.append(cat))
//      }
//      catMap += (parent -> arr)
//    }
//      catMap
//
//  }
}




