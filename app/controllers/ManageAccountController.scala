package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller, Flash}
import play.api._
import play.api.mvc
import play.api.mvc.Controller
import play.api.data._
import play.api.data.Forms._
import models.{CustomerDetails, LoginSession}

class ManageAccountController @Inject extends Controller {
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

  def ManageAccounts() = Action {
    if(LoginSession.getCurrentUser != "") {
      Ok(views.html.ManageAccount(manageAccountForm))
    }
    else {
      Redirect(routes.LoginController.login())
    }

  }

  def updateAccount() = Action {
    implicit request => {
      if (manageAccountForm.bindFromRequest().data("eMail").length != 0) {
        // def findCustomerEmail(eMail: String): ArrayBuffer[CustomerDetails]
        if(CustomerDetails.findCustomerEmail(manageAccountForm.bindFromRequest().data("eMail").toLowerCase).isEmpty) {
          println(manageAccountForm.bindFromRequest().data("eMail"))
          println("Account not found")
        } else {
          println(manageAccountForm.bindFromRequest().data("eMail"))
          println("Email found")

          var temp:CustomerDetails = CustomerDetails.findCustomerEmail(manageAccountForm.bindFromRequest().data("eMail")).head
          if(manageAccountForm.bindFromRequest().data("telephoneNumber").length != 0) {
            //add new telephone number
            temp = temp.copy(phoneNumber = manageAccountForm.bindFromRequest().data("telephoneNumber"))

          }
          if(manageAccountForm.bindFromRequest().data("fName").length != 0) {
            temp = temp.copy(fName = manageAccountForm.bindFromRequest().data("fName"))
          }
          if(manageAccountForm.bindFromRequest().data("lName").length != 0){
            temp = temp.copy(lName = manageAccountForm.bindFromRequest().data("lName"))
          }

          if(manageAccountForm.bindFromRequest().data("addrLine1").length != 0){
            temp = temp.copy(addressLine1 = manageAccountForm.bindFromRequest().data("addrLine1"))
          }

          if(manageAccountForm.bindFromRequest().data("addrLine2").length != 0){
            temp = temp.copy(addressLine2 = manageAccountForm.bindFromRequest().data("addrLine2"))
          }

          if(manageAccountForm.bindFromRequest().data("city").length != 0){
            temp = temp.copy(city = manageAccountForm.bindFromRequest().data("city"))
          }

          if(manageAccountForm.bindFromRequest().data("county").length != 0){
            temp = temp.copy(county = manageAccountForm.bindFromRequest().data("county"))
          }

          if(manageAccountForm.bindFromRequest().data("postCode").length != 0){
            temp = temp.copy(postCode = manageAccountForm.bindFromRequest().data("postCode"))
          }
          CustomerDetails.updateAccount(temp)
        }
      }
      Redirect(routes.ManageAccountController.ManageAccounts())
    }
  }
}

