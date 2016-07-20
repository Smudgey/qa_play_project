package models
import scala.collection.mutable.ArrayBuffer
/**
  * Created by Administrator on 12/07/2016.
  */

object Category extends Enumeration{

  val Gnome         = Value("Gnome")
  val ClothingItem  = Value("Clothing Item")
  val PlantItem     = Value("Plant Item")
  val BirdItem      = Value("Bird Item")
  val PlantPot      = Value("Plant Pot")
  val Furniture     = Value("Furniture")
  val Tool          = Value("Tool")
}


