package models

/**
  * Created by Marko on 14/07/2016.
  */
object PaymentMethod extends Enumeration{

  val PayNow    = Value("Pay Now")
  val PayLater  = Value("Pay Later")
  val Other     = Value("Other")
}
