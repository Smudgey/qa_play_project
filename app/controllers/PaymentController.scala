package controllers

import javax.inject._

import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.MessagesApi
import play.api.mvc._

/**
  * Created by Luke on 07/07/2016.
  */
@Singleton
class PaymentController @Inject()(val messagesApi: MessagesApi) extends Controller with Formatter {

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
      "registerCard" -> nonEmptyText
    )
  )

  def checkout = Action {
    implicit request =>
      Ok(views.html.checkout(checkoutForm)(request.session))
  }

  def checkoutBasket = Action {
    implicit request =>
//      if (request.session.get("connected").isEmpty) {
//        Redirect(routes.LoginController.login())
//      }
//      else {
        val in = checkoutForm.bindFromRequest().data("registerCard")
        var payMthd: PaymentMethod.Value = null
        in match {
          case "payNow" => payMthd = PaymentMethod.PayNow
          case "payLater" => payMthd = PaymentMethod.PayLater
          case _ => payMthd = PaymentMethod.Other
        }
        val cust = Login.findLogin(request.session.data("connected")).get.lid
        val ol = OrderLine_New.basket
        val price = OrderLine_New.totalPrice(ol)
        val status = OrderStatus.Ordered.toString
        val td = todaysDate
        val time = timeNow

        val o = Order_New(randomInt, cust, td, time, status, payMthd.toString, ol.toArray, price, 0)
        println("YES: " + o)

        //TODO Direct to the card registerCard page
        Ok(views.html.registerCard(request.session))

//      }
  }

  /**
    * Load the registerCard page, passing in the card form
    *
    * @return
    */
  //  def registerCard = Action {
  //    implicit request =>
  //    Ok(views.html.registerCard(order)(cardForm)(request.session))  }

  //TODO
  /**
    * Find if card already exists, if not register, if so confirm registerCard
    *
    * @return
    */
  def registerPayment() = Action {
    implicit request => {

      Redirect(routes.HomeController.index())

    }
  }
}


