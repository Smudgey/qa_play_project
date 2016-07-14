package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller, Flash}
import play.api._
import play.api.mvc
import play.api.mvc.Controller
import play.api.data._
import play.api.data.Forms._
import models.{CustomerDetails, LoginSession, Order}

class ViewAccountController @Inject extends Controller {
  private val viewAccountForm: Form[CustomerDetails] = Form(
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

  def ViewAccounts() = Action {
    if(LoginSession.getCurrentUser != "") {
      Ok(views.html.ViewAccount(viewAccountForm))
    }

    else {
      Redirect(routes.LoginController.login())

    }

  }

  def View2 = Action {
    Order.orderList

    Ok(views.html.ViewAccount())

  }


}
