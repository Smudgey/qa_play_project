package models

/**
  * Created by Marko on 14/07/2016.
  */
object OrderStatus extends Enumeration {

  val Ordered     = Value("Ordered")
  val Processing  = Value("Processing")
  val Dispatched  = Value("Dispatched")

}
