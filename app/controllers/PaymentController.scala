package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms.{mapping, number, nonEmptyText}
import models._

/**
  * Created by Luke on 07/07/2016.
  */
@Singleton
class PaymentController @Inject() extends Controller {

  /**
    * Create a form of type Payment
    */
  private val cardForm: Form[Payment] = Form(
    mapping(
      "cardholder" -> nonEmptyText,
      "cardNumber" -> nonEmptyText,
      "cv" -> number,
      "expirationMonth" -> nonEmptyText,
      "expirationYear" -> nonEmptyText
    )
    (Payment.apply)
    (Payment.unapply)
  )

  /**
    * Load the payment page, passing in the card form
    * @return
    */
  def payment() = Action {
    Ok(views.html.payment(cardForm))
  }

  /**
    * Payment is confirmed, redirect of payment confirmation screen
    * @param orderID
    * @return
    */
  def paymentProcessed(orderID: String) = Action {
    Ok(views.html.paymentConfirmed(orderID))
  }

  //TODO
  /**
    * Find if card already exists, if not register, if so confirm payment
    *
    * @return
    */
  def registerPayment() = Action {
    implicit request => {
      CardDetails.addCard(
        LoginSession.getCurrentUser,
        cardForm.bindFromRequest().data("cardholder"),
        cardForm.bindFromRequest().data("cardNumber"),
        cardForm.bindFromRequest().data("cv"),
        cardForm.bindFromRequest().data("expirationMonth"),
        cardForm.bindFromRequest().data("expirationYear")
      )
      Redirect(routes.HomeController.index())

/*      //Check if any fields were left empty
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
      }*/
    }
  }
}
