package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import _root_.models.{Payment}

/**
  * Created by Luke on 07/07/2016.
  */
@Singleton
class PaymentController @Inject() extends Controller {
  val paymentForm = Form(
    mapping(
      "cardholderName" -> text,
      "cardNumber" -> number,
      "cv" -> number,
      "expirationMonth" -> text,
      "expirationYear" -> text
    )
    (Payment.apply)
    (Payment.unapply)
  )



  def index = Action {
    Ok(views.html.payment())
  }
}
