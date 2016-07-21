package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import play.api.data.{Form, _}
import play.api.data.Forms._
import models._
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Luke on 07/07/2016.
  */
@Singleton
class PaymentController @Inject()(val messagesApi: MessagesApi) extends Controller {

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

  private val checkoutForm = Form(
    single(
      "payment" -> nonEmptyText
    )
  )

  def checkout = Action {
    implicit request =>
      Ok(views.html.checkout(checkoutForm)(request.session))
  }

  def checkoutBasket = Action {
    implicit request =>
      if(request.session.get("connected").isEmpty){
        Redirect(routes.LoginController.login())
      }
      else{
        val in = checkoutForm.bindFromRequest().data("payment")
        var payMthd : PaymentMethod.Value = null
        if(in == "payNow"){
          payMthd = PaymentMethod.PayNow
        } else if(in == "payLater"){
          payMthd = PaymentMethod.PayLater
        } else {
          payMthd = PaymentMethod.Other
        }
        val cust = Login.findLogin(request.session.data("connected")).get.lid
        val ol = OrderLine.basket
        val price = OrderLine.totalPrice(ol)
        val status = OrderStatus.Ordered
        val time = Order.today

        val o = Order(cust, ol, price, status, payMthd, time)

      //TODO Direct to a card payment page
      Ok(views.html.payment(o)(cardForm)(request.session))
  }

  /**
    * Load the payment page, passing in the card form
    *
    * @return
    */
//  def payment = Action {
//    implicit request =>
//    Ok(views.html.payment(order)(cardForm)(request.session))  }

  /**
    * Payment is confirmed, redirect of payment confirmation screen
    *
    * @param orderID
    * @return
    */
  def paymentProcessed(orderID: String) = Action {
    implicit request =>
    Ok(views.html.paymentConfirmed(orderID)(request.session))
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
