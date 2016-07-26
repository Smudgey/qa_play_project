package controllers

import com.google.inject.Inject
import models._
import play.api.data.Forms._
import play.api.data._
import play.api.mvc.{Action, Controller}


/**
  * Created by Rytis
  *
  * Last worked on by Rytis & Yang on 25/07/2016
  */

class AccountController @Inject extends Controller with Formatter {

  private val cardForm: Form[CardDetails] = Form(
    mapping(
      "" -> text,
      "cardholder" -> nonEmptyText,
      "cardNumber" -> nonEmptyText,
      "cv" -> nonEmptyText,
      "expirationMonth" -> nonEmptyText,
      "expirationYear" -> nonEmptyText
    )
    (CardDetails.apply)
    (CardDetails.unapply)
  )
  private val updateDetailsForm: Form[CustomerDetails] = Form(
    mapping(
      "name" -> text,
      "email" -> text,
      "phone" -> text
    )
    (CustomerDetails.apply)
    (CustomerDetails.unapply)
  )

  def manageAccounts = Action {
    implicit request =>
      Ok(views.html.manageAccount(request.session))
  }

  def updateAccount() = Action {
    implicit request => {
      CustomerDetails.updateDetails(Account.getAccountViaEmail(Login.findLogin(request.session.data("connected")).get.lid).get.details, updateDetailsForm.bindFromRequest().data("name"), updateDetailsForm.bindFromRequest().data("phone"), updateDetailsForm.bindFromRequest().data("email"))
      Redirect(routes.AccountController.manageAccounts()).withSession("connected" -> updateDetailsForm.bindFromRequest().data("email"))
    }
  }

  def viewAccount = Action {
    implicit request =>

      //Temporary check statement whilst suing dummy data
      if (request.session.get("connected").isEmpty) {
        Redirect(routes.HomeController.index())
      } else {

        Ok(views.html.viewAccount(CustomerDetails.getDetails(Account.getAccountViaEmail(Login.findLogin(request.session.data("connected")).get.lid).get.details).get)(request.session))

      }
  }

  def viewCard = Action {
    implicit request =>
      Ok(views.html.viewCard(CardDetails.getCards(Account.getAccountViaEmail(Login.findLogin(request.session.data("connected")).get.lid).get.accountID).toArray)(request.session))
  }

  def registerCard = Action {
    implicit request =>
      Ok(views.html.registerCard(request.session))
  }

  def addCard() = Action {
    implicit request =>

      CardDetails.addCard(
        Account.getAccountViaEmail(Login.findLogin(request.session.data("connected")).get.lid).get.accountID,
        randomID,
        cardForm.bindFromRequest().data("cardholder"),
        cardForm.bindFromRequest().data("cardNumber"),
        cardForm.bindFromRequest().data("cv"),
        cardForm.bindFromRequest().data("expirationMonth"),
        cardForm.bindFromRequest().data("expirationYear")
      )
      Redirect(routes.AccountController.registerCard())
  }

  def removeCard(cardNumber: String) = Action {
    implicit request =>
      CardDetails.removeCard(cardNumber)
      Redirect(routes.AccountController.viewCard())
  }
}