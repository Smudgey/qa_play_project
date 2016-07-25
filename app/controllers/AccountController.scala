package controllers

import com.google.inject.Inject
import models.{CardDetails, CustomerDetails, Login}
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
      "eMail" -> nonEmptyText,
      "telephoneNumber" -> nonEmptyText,
      "fName" -> nonEmptyText,
      "lName" -> nonEmptyText,
      "addrLine1" -> nonEmptyText,
      "addrLine2" -> nonEmptyText,
      "city" -> nonEmptyText,
      "county" -> nonEmptyText,
      "postCode" -> nonEmptyText

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
      if (manageAccountForm.bindFromRequest().data("eMail").length != 0) {
        // def findCustomerEmail(eMail: String): ArrayBuffer[CustomerDetails]
        if (CustomerDetails.findCustomerEmail(manageAccountForm.bindFromRequest().data("eMail").toLowerCase).isEmpty) {
          println(manageAccountForm.bindFromRequest().data("eMail"))
          println("Account not found")
        } else {
          println(manageAccountForm.bindFromRequest().data("eMail"))
          println("Email found")

          var temp: CustomerDetails = CustomerDetails.findCustomerEmail(manageAccountForm.bindFromRequest().data("eMail")).head
          if (manageAccountForm.bindFromRequest().data("telephoneNumber").length != 0) {
            //add new telephone number
            temp = temp.copy(phoneNumber = manageAccountForm.bindFromRequest().data("telephoneNumber"))

          }
          if (manageAccountForm.bindFromRequest().data("fName").length != 0) {
            temp = temp.copy(fName = manageAccountForm.bindFromRequest().data("fName"))
          }
          if (manageAccountForm.bindFromRequest().data("lName").length != 0) {
            temp = temp.copy(lName = manageAccountForm.bindFromRequest().data("lName"))
          }

          if (manageAccountForm.bindFromRequest().data("addrLine1").length != 0) {
            temp = temp.copy(addressLine1 = manageAccountForm.bindFromRequest().data("addrLine1"))
          }

          if (manageAccountForm.bindFromRequest().data("addrLine2").length != 0) {
            temp = temp.copy(addressLine2 = manageAccountForm.bindFromRequest().data("addrLine2"))
          }

          if (manageAccountForm.bindFromRequest().data("city").length != 0) {
            temp = temp.copy(city = manageAccountForm.bindFromRequest().data("city"))
          }

          if (manageAccountForm.bindFromRequest().data("county").length != 0) {
            temp = temp.copy(county = manageAccountForm.bindFromRequest().data("county"))
          }

          if (manageAccountForm.bindFromRequest().data("postCode").length != 0) {
            temp = temp.copy(postCode = manageAccountForm.bindFromRequest().data("postCode"))
          }
          CustomerDetails.updateAccount(temp)
        }
      }

      Redirect(routes.AccountController.manageAccounts())
    }
  }

  def viewAccount = Action {
    implicit request =>

      //Temporary check statement whilst suing dummy data
      if (request.session.get("connected").isEmpty) {
        Redirect(routes.HomeController.index())
      } else {
        Ok(views.html.viewAccount(CustomerDetails.findCustomerEmail(request.session.data("connected")))(request.session))

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