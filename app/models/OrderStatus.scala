package models

/**
  * Created by Marko on 14/07/2016.
  */
object OrderStatus extends Enumeration {

  val Dispatched  = Value("Dispatched")
  val Ordered     = Value("Ordered")
  val Processing  = Value("Processing")
}
