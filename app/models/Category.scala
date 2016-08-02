package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marko on 12/07/2016.
  */

object Category extends Enumeration with Formatter{

  val Gnome           = Value("Gnome")
  val GnomeSmall      = Value("Gnome/Small")
  val GnomeBig        = Value("Gnome/Big")
  val GnomeMedium     = Value("Gnome/Medium")

  val Clothing        = Value("Clothing")
  val ClothingShoe    = Value("Clothing/Shoe")
  val ClothingJumper  = Value("Clothing/Jumper")
  val ClothingTrouser = Value("Clothing/Trouser")
  val ClothingUnderwear = Value("Clothing/Underwear")
  val ClothingHat     = Value("Clothing/Hat")

  val Plant           = Value("Plants")
  val PlantTree       = Value("Plants/Tree")
  val PlantPotted     = Value("Plants/Potted")
  val PlantPot        = Value("Plants/Pot")

  val BirdItem        = Value("Birds")
  val BirdHouse       = Value("Birds/BirdHouse")
  val BirdFeeder      = Value("Birds/Feeder")

  val Furniture       = Value("Furniture")
  val FurnitureGazebo = Value("Furniture/Gazebo")
  val FurnitureTable  = Value("Furniture/Table")
  val FurnitureChair  = Value("Furniture/Chair")

  val Tool            = Value("Tool")
  val ToolRake        = Value("Tool/Rake")
  val ToolSpade       = Value("Tool/Spade")
  val ToolAxe         = Value("Tool/Axe")

  val parentCats      = ArrayBuffer(Gnome, Clothing, Plant, BirdItem, Furniture, Tool)
  val categoryMap     = Map(
          Gnome     -> List(GnomeBig, GnomeMedium, GnomeSmall),
          Clothing  -> List(ClothingShoe, ClothingUnderwear, ClothingHat, ClothingJumper, ClothingTrouser),
          Plant     -> List(PlantTree, PlantPotted, PlantPot),
          Furniture -> List(FurnitureChair, FurnitureTable, FurnitureGazebo),
          Tool      -> List(ToolAxe, ToolRake, ToolSpade),
          BirdItem  -> List(BirdHouse, BirdFeeder)
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




