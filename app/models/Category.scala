package models
/**
  * Created by Marko on 12/07/2016.
  */

object Category extends Enumeration {

  val Gnome     = Value("Gnome")
  val GnomeSmall    = Value("Small Gnome")
  val GnomeBig      = Value("Big Gnome")
  val GnomeMedium   = Value("Medium Gnome")

  val ClothingItem    = Value("Clothing Item")
  val ClothingShoe    = Value("Shoe")
  val ClothingJumper  = Value("Top")
  val ClothingTrouser = Value("Trouser")
  val ClothingUnderwear = Value("Underwear")
  val ClothingHat     = Value("Hat")

  val PlantItem     = Value("Plant Item")
  val PlantTree     = Value("Tree")
  val PlantPotted   = Value("Potted Plant")


  val BirdItem      = Value("Bird Item")
  val BirdHouse     = Value("Bird House")
  val BirdFeeder    = Value("Bird Feeder")

  val PlantPot      = Value("Plant Pot")

  val Furniture       = Value("Furniture")
  val FurnitureGazebo = Value("Gazebo")
  val FurnitureTable  = Value("Table")
  val FurnitureChair  = Value("Chair")

  val Tool          = Value("Tool")
  val ToolRake      = Value("Rake")
  val ToolSpade     = Value("Spade")
  val ToolAxe       = Value("Axe")


}




