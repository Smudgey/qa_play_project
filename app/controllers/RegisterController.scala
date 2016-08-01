package controllers

import com.google.inject.Inject
import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}


/*
  * Create By rytis
  *
  * Last worked on by Rytis on 26/07/2916
  */

class RegisterController @Inject extends Controller with Formatter {

  // this will create register form
  private val userForm = Form(
    tuple(
      "fullName" -> nonEmptyText,
      "email" -> nonEmptyText,
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
      Ok(views.html.registerStart(request.session))
  }

  /**
    * this function will display address part of register form
    */
  def address = Action {
    implicit request =>
      Ok(views.html.registerAddress(request.session))
  }

  /**
    * this function will display card detail part of register form
    */
  def bank = Action {
    implicit request =>
      Ok(views.html.registerCard(request.session))
  }


  //TODO create error messages

  /**
    * This function will create Login object for the customer
    */
  def createUser() = Action {
    implicit request => {
      if (Login.findLogin(userForm.bindFromRequest().data("email")).isEmpty) {

        val loginID = randomID
        val detailsID = randomID
        Login.createUser(loginID, userForm.bindFromRequest().data("email"), userForm.bindFromRequest().data("password"))
        CustomerDetails.addDetails(detailsID, userForm.bindFromRequest().data("fullName"), "None")
        Account.createAccount(randomID, loginID, detailsID)
        Redirect(routes.RegisterController.address()).withSession("tmp" -> userForm.bindFromRequest().data("email"))

      } else {
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

      val randomAddressID = randomID
      val account = Account.getAccountViaEmail(Login.findLogin(request.session.data("tmp")).get.lid).get
      account.addressID = randomAddressID
      Address.addAddress(
        account.accountID,
        addressForm.bindFromRequest().data("houseNumber"),
        addressForm.bindFromRequest().data("streetName"),
        addressForm.bindFromRequest().data("town"),
        addressForm.bindFromRequest().data("city"),
        addressForm.bindFromRequest().data("county"),
        addressForm.bindFromRequest().data("postcode"))
      Redirect(routes.RegisterController.bank())
    }
  }

  /**
    * this function will create CardDetails object for the customer
    *
    * @return
    */
  def createCard() = Action {
    implicit request => {
      val account = Account.getAccountViaEmail(Login.findLogin(request.session.data("tmp")).get.lid).get
      val randomCardID = randomID
      account.cardID = randomCardID
      CardDetails.addCard(
        account.cardID,
        cardForm.bindFromRequest().data("cardholder"),
        cardForm.bindFromRequest().data("cardnumber"),
        cardForm.bindFromRequest().data("cv"),
        cardForm.bindFromRequest().data("expirationMonth"),
        cardForm.bindFromRequest().data("expirationMonth"))
      val tmp = request.session.data("tmp")
      Redirect(routes.HomeController.index()).withSession().withSession("connected" -> tmp.replaceAll(" ", ""))
    }
  }

}
