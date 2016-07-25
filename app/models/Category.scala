package models
/**
  * Created by Marko on 12/07/2016.
  */

object Category extends Enumeration {

  val Gnome           = Value
  val GnomeSmall      = Value("Small Gnome")
  val GnomeBig        = Value("Big Gnome")
  val GnomeMedium     = Value("Medium Gnome")

  val Clothing        = Value
  val ClothingShoe    = Value("Shoe")
  val ClothingJumper  = Value("Top")
  val ClothingTrouser = Value("Trouser")
  val ClothingUnderwear = Value("Underwear")
  val ClothingHat     = Value("Hat")

  val Plant           = Value
  val PlantTree       = Value("Tree")
  val PlantPotted     = Value("Potted Plant")
  val PlantPot        = Value("Plant Pot")

  val Bird            = Value
  val BirdHouse       = Value("Bird House")
  val BirdFeeder      = Value("Bird Feeder")

  val Furniture       = Value
  val FurnitureGazebo = Value("Gazebo")
  val FurnitureTable  = Value("Table")
  val FurnitureChair  = Value("Chair")

  val Tool            = Value
  val ToolRake        = Value("Rake")
  val ToolSpade       = Value("Spade")
  val ToolAxe         = Value("Axe")


}




