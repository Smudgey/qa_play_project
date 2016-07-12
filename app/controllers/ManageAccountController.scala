package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller, Flash}
import play.api._
import play.api.mvc
import play.api.mvc.Controller
import play.api.data._
import play.api.data.Forms._
import models.CustomerDetails


/**
  * Created by Administrator on 07/07/2016.
  */
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
    Ok(views.html.ManageAccount(manageAccountForm))

  }

  def updateAccount() = Action {
    implicit request => {
      if (manageAccountForm.bindFromRequest().data("eMail").length != 0 ||
        manageAccountForm.bindFromRequest().data("telephoneNumber").length != 0 ||
        manageAccountForm.bindFromRequest().data("fName").length != 0 ||
        manageAccountForm.bindFromRequest().data("lName").length != 0 ||
        manageAccountForm.bindFromRequest().data("addrLine1").length != 0 ||
        manageAccountForm.bindFromRequest().data("addrLine2").length != 0 ||
        manageAccountForm.bindFromRequest().data("city").length != 0 ||
        manageAccountForm.bindFromRequest().data("county").length != 0 ||
        manageAccountForm.bindFromRequest().data("postCode").length != 0) {
        // def findCustomerEmail(eMail: String): ArrayBuffer[CustomerDetails]
        if(CustomerDetails.findCustomerEmail(manageAccountForm.bindFromRequest().data("eMail").toLowerCase).isEmpty) {
          println(manageAccountForm.bindFromRequest().data("eMail"))
          println("Scrub")
        } else {
          println(manageAccountForm.bindFromRequest().data("eMail"))
          println("Email found")
        }

      }
      println("hello")
      Redirect(routes.ManageAccountController.ManageAccounts())
    }
  }
}

