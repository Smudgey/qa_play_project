package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
  * Created by Luke on 07/07/2016.
  */
@Singleton
class PaymentConfirmedController @Inject() extends Controller {
  def paymentProcessed(orderID: String) = Action {
    Ok(views.html.paymentConfirmed(orderID))
  }
}
