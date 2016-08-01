package controllers

import com.google.inject.Inject
import models._
import play.api.data.Forms._
import play.api.data._
import play.api.mvc.{Action, Controller, Session}

/*
  * Create By rytis
  *
  * Last worked on by Rytis on 26/07/2916
  */

class AccountController @Inject extends Controller with Formatter with MongoDatabaseConnector {

  //form for customer adding card details
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
  //form for updating customer details
  private val updateDetailsForm: Form[CustomerDetails] = Form(
    mapping(
      "name" -> text,
      "email" -> text,
      "phone" -> text
    )
    (CustomerDetails.apply)
    (CustomerDetails.unapply)
  )
  //form form adding address
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


  /**
    * this function will display manage account details page
    */
  def manageAccounts = Action {
    implicit request =>
      Ok(views.html.manageAccount(request.session))
  }


  /**
    * this function will update the customer details with new details
    *
    * @return
    */
  def updateAccount() = Action {
    implicit request => {
      CustomerDetails.updateDetails(Account.getAccountViaEmail(Login.findLogin(request.session.data("connected")).get.lid).get.detailsID, updateDetailsForm.bindFromRequest().data("name"), updateDetailsForm.bindFromRequest().data("phone"), updateDetailsForm.bindFromRequest().data("email"))
      Redirect(routes.AccountController.manageAccounts()).withSession("connected" -> updateDetailsForm.bindFromRequest().data("email"))
    }
  }


  /**
    * this function will display account details page
    *
    * @return
    */
  def viewAccount = Action {
    implicit request =>

      //Temporary check statement whilst suing dummy data
      if (request.session.get("connected").isEmpty) {
        Redirect(routes.HomeController.index())
      } else {
        Ok(views.html.viewAccount(findAccountByEmail(request.session.data("connected")).head))   //searches for the account related to the email address in the cookie
      }
  }

  /**
    * this function page will display all cards customer has added
    *
    * @return
    */
  def viewCard = Action {
    implicit request =>
      Ok(views.html.viewCard(findAccountByEmail(request.session.data("connected")).head.paymentCards)(request.session))
  }

  /**
    * this function will display all address customer has added
    *
    * @return
    */
  def viewAddress = Action {
    implicit request =>
      Ok(views.html.viewAddress(findAccountByEmail(request.session.data("connected")).head.address))
  }

  /**
    * this function will display page that will allow customer to add new cards
    *
    * @return
    */
  def addNewCard() = Action {
    implicit request =>
      Ok(views.html.addNewCard(request.session))
  }

  /**
    * this function will display page that will allow customer to add new address
    *
    * @return
    */
  def addNewAddress() = Action {
    implicit request =>
      Ok(views.html.addNewAddress(request.session))
  }

  /**
    * this function will allow the customer to add new cards
    *
    * @return
    */
  def addCard() = Action {
    implicit request =>

      CardDetails.addCard(
        Account.getAccountViaEmail(Login.findLogin(request.session.data("connected")).get.lid).get.cardID,
        cardForm.bindFromRequest().data("cardholder"),
        cardForm.bindFromRequest().data("cardnumber"),
        cardForm.bindFromRequest().data("cv"),
        cardForm.bindFromRequest().data("expirationMonth"),
        cardForm.bindFromRequest().data("expirationYear")
      )
      Redirect(routes.AccountController.addNewCard())
  }

  /**
    * this function will remove customer card
    *
    * @param cardNumber Long card number
    * @return
    */
  def removeCard(cardNumber: String) = Action {
    implicit request =>
      CardDetails.removeCard(cardNumber)
      Redirect(routes.AccountController.viewCard())
  }

  /**
    * this function will remove customer address
    *
    * @param addressID AddressID
    * @return
    */
  def removeAddress(addressID: String) = Action {
    implicit request =>
      Address.removeAddress(addressID)
      Redirect(routes.AccountController.viewAddress())
  }

  /**
    * this function will allow the customer to add new address
    *
    * @return
    */
  def addAddress() = Action {
    implicit request =>
      Address.addAddress(
        Account.getAccountViaEmail(Login.findLogin(request.session.data("connected")).get.lid).get.addressID,
        addressForm.bindFromRequest().data("houseNumber"),
        addressForm.bindFromRequest().data("streetName"),
        addressForm.bindFromRequest().data("town"),
        addressForm.bindFromRequest().data("city"),
        addressForm.bindFromRequest().data("county"),
        addressForm.bindFromRequest().data("postcode"))
      Redirect(routes.AccountController.addNewAddress())
  }
}