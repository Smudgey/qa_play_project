package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms.{mapping, number, text}
import _root_.models.Payment

/**
  * Created by Luke on 07/07/2016.
  */
@Singleton
class PaymentController @Inject() extends Controller {
  //Create a form of type Payment
  private val paymentForm: Form[Payment] = Form(
    mapping(
      "cardholderName" -> text,
      "cardNumber" -> text,
      "cv" -> number,
      "expirationMonth" -> text,
      "expirationYear" -> text
    )
    (Payment.apply)
    (Payment.unapply)
  )

  def payment() = Action {
    Ok(views.html.payment(paymentForm))
  }

  /* Error check the data input */
  def registerPayment() = Action {
    implicit request => {
      //TODO - Create payment confirmed page
      Ok(views.html.payment(paymentForm))
    }
  }
}
