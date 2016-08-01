package controllers

import com.google.inject.Inject
import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import reactivemongo.bson.{BSONDocument, BSONDocumentReader}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.ExecutionContext
import scala.util.Success


/*
  * Create By rytis
  *
  * Last worked on by Rytis on 26/07/2916
  */

class RegisterController @Inject extends Controller with Formatter with MongoDatabaseConnector {

  // this will create register form
  private val userForm = Form(
    tuple(
      "fullName" -> nonEmptyText,
      "email" -> nonEmptyText,
      "phone" -> nonEmptyText,
      "password" -> nonEmptyText
    )
  )
  // this will create address form
  private val addressForm: Form[Address] = Form(
    mapping(
      "" -> text,
      "houseNumber" -> nonEmptyText,
      "streetName" -> nonEmptyText,
      "town" -> nonEmptyText,
      "city" -> nonEmptyText,
      "county" -> nonEmptyText,
      "postcode" -> nonEmptyText
    )
    (Address.apply)
    (Address.unapply)
  )
  // this will create card detail form
  private val cardForm: Form[CardDetails] = Form(
    mapping(
      "" -> text,
      "cardholder" -> nonEmptyText,
      "cardnumber" -> nonEmptyText,
      "cv" -> nonEmptyText,
      "expirationMonth" -> nonEmptyText,
      "expirationYear" -> nonEmptyText
    )
    (CardDetails.apply)
    (CardDetails.unapply)
  )

  /**
    * this function will display login part of register form
    */
  def register = Action {
    implicit request =>
      Ok(views.html.registerStart(request.session, request.flash))
  }

  /**
    * this function will display address part of register form
    */
  def address = Action {
    implicit request =>
      Ok(views.html.registerAddress(request.session, request.flash))
  }

  /**
    * this function will display card detail part of register form
    */
  def bank = Action {
    implicit request =>
      Ok(views.html.registerCard(request.session, request.flash))
  }


  //TODO create error messages

  /**
    * This function will create Login object for the customer
    */
  def createUser() = Action {
    implicit request => {
      if (Login.findLogin(userForm.bindFromRequest().data("email")).isEmpty) {
        //Redirect to next step, address, passing both the session and flash data
        Redirect(routes.RegisterController.address()) withSession(
          "Username" -> userForm.bindFromRequest().data("email"),
          "Password" -> userForm.bindFromRequest().data("password"),
          "Name" -> userForm.bindFromRequest().data("fullName"),
          "Phone" -> userForm.bindFromRequest().data("phone"))
      } else {
        //user already exists
        Redirect(routes.RegisterController.register())
      }
    }
  }

  /**
    * this function will create Address object for customer
    *
    * @return
    */
  def createAddress() = Action {
    implicit request => {
      Redirect(routes.RegisterController.bank()).withSession(
        "AddressLine1" -> (addressForm.bindFromRequest().data("houseNumber") + " " + addressForm.bindFromRequest().data("streetName")),
        "AddressLine2" -> addressForm.bindFromRequest().data("town"),
        "AddressCity" -> addressForm.bindFromRequest().data("city"),
        "AddressCounty" -> addressForm.bindFromRequest().data("county"),
        "AddressPostcode" -> addressForm.bindFromRequest().data("postcode")
      )
    }
  }

  /**
    * this function will create CardDetails object for the customer
    *
    * @return
    */
  def createCard() = Action {
    implicit request => {
      var flag = false

      //TODO

      if (!flag) {
        //Create addresses
        val addressArray: Array[Address_New] = Array.empty
        addressArray :+ Address_New(
          addressForm.bindFromRequest().data("houseNumber") + addressForm.bindFromRequest().data("streetName"),
          addressForm.bindFromRequest().data("town"),
          addressForm.bindFromRequest().data("city"),
          addressForm.bindFromRequest().data("county"),
          addressForm.bindFromRequest().data("postcode")
        )
        //create payment cards
        val paymentCardsArray: Array[PaymentCards] = Array.empty
        paymentCardsArray :+ PaymentCards(
          cardForm.bindFromRequest().data("cardholder"),
          cardForm.bindFromRequest().data("cardnumber"),
          cardForm.bindFromRequest().data("expirationMonth") + cardForm.bindFromRequest().data("expirationYear")
        )
        //Create new Account in database
        Account_New.create(result, Account_New(
          randomID,
          userForm.bindFromRequest().data("email"),
          userForm.bindFromRequest().data("password"),
          userForm.bindFromRequest().data("fullName"),
          userForm.bindFromRequest().data("phone"),
          addressArray,
          paymentCardsArray
        ))
      }

      Thread.sleep(3000)
      if (flag) {
        Redirect(routes.HomeController.index()).withSession("connected" -> request.flash.get("Username").toString)
      } else {
        Redirect(routes.RegisterController.register())
      }
    }


    /*val account = Account.getAccountViaEmail(Login.findLogin(request.session.data("tmp")).get.lid).get
    val randomCardID = randomID
    account.cardID = randomCardID
    CardDetails.addCard(
      account.cardID,
      cardForm.bindFromRequest().data("cardholder"),
      cardForm.bindFromRequest().data("cardnumber"),
      cardForm.bindFromRequest().data("cv"),
      cardForm.bindFromRequest().data("expirationMonth"),
      cardForm.bindFromRequest().data("expirationYear"))
    val tmp = request.session.data("tmp")
    Redirect(routes.HomeController.index()).withSession().withSession("connected" -> tmp.replaceAll(" ", ""))*/
  }
}

}
