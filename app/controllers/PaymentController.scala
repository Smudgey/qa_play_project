package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms.{mapping, number, nonEmptyText}
import _root_.models.Payment

/**
  * Created by Luke on 07/07/2016.
  */
@Singleton
class PaymentController @Inject() extends Controller {
  //Create a form of type Payment
  private val paymentForm: Form[Payment] = Form(
    mapping(
      "cardholderName" -> nonEmptyText,
      "cardNumber" -> nonEmptyText,
      "cv" -> number,
      "expirationMonth" -> nonEmptyText,
      "expirationYear" -> nonEmptyText
    )
    (Payment.apply)
    (Payment.unapply)
  )

  def payment() = Action {
    Ok(views.html.payment(paymentForm))
  }

  /* Find if card already exists, if not register, if so confirm payment */
  def registerPayment(orderID: String) = Action {
    implicit request => {
      //Check if any fields were left empty
      if (paymentForm.bindFromRequest().data("cardholderName").length != 0 ||
        paymentForm.bindFromRequest().data("cardNumber").length != 0 ||
        paymentForm.bindFromRequest().data("cv").length != 0 ||
        paymentForm.bindFromRequest().data("expirationMonth").length != 0 ||
        paymentForm.bindFromRequest().data("expirationYear").length != 0) {
        //Search to see if card exists, using card number input from form
        if (Payment.findCardNumber(paymentForm.bindFromRequest().data("cardNumber")).isEmpty) {
          //Did not find existing card
          println("Did not find existing card")
          Redirect(routes.PaymentConfirmedController.paymentProcessed(orderID))
        } else {
          //Found existing card
          println("Found existing card")
          //Redirect(routes.PaymentController.payment())
          Redirect(routes.PaymentConfirmedController.paymentProcessed(orderID))
        }
      } else {
        println("Fields were left blank")
        Redirect(routes.PaymentController.payment())
      }
    }
  }
}
