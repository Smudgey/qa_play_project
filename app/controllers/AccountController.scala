package controllers

import com.google.inject.Inject
import models.{Account, CardDetails, CustomerDetails, Login}
import play.api.data.Forms._
import play.api.data._
import play.api.mvc.{Action, Controller}


/**
  * Created by Rytis
  *
  * Last worked on by Rytis & Yang on 25/07/2016
  */

class AccountController @Inject extends Controller {
  private val manageAccountForm: Form[CustomerDetails] = Form(
    mapping(
      "email" -> nonEmptyText,
      "name" -> nonEmptyText,
      "address" -> nonEmptyText,
      "city" -> nonEmptyText,
      "county" -> nonEmptyText,
      "postcode" -> nonEmptyText

    )
    (CustomerDetails.apply)
    (CustomerDetails.unapply)
  )

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

  def manageAccounts = Action {
    implicit request =>
      Ok(views.html.manageAccount(request.session))
  }

  def updateAccount() = Action {
    implicit request => {
      Redirect(routes.AccountController.manageAccounts())
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
      Ok(views.html.viewCard(CardDetails.getCard(Login.findLogin(request.session.data("connected")).get.lid).toArray)(request.session))
  }

  def registerCard = Action {
    implicit request =>
      Ok(views.html.registerCard(request.session))
  }

  def addCard() = Action {
    implicit request =>
      CardDetails.addCard(
        Login.findLogin(request.session.data("connected")).get.lid,
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