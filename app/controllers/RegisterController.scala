package controllers

import com.google.inject.Inject
import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}

import scala.util.Random


/**
  * Created by rytis on 07/07/16.
  */
class RegisterController @Inject extends Controller {

  // this will create register form
  private val userForm: Form[Login] = Form(
    mapping(
      "" -> text,
      "fullName" -> nonEmptyText,
      "password" -> nonEmptyText,
      "email" -> nonEmptyText
    )
    (Login.apply)
    (Login.unapply)
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
      "cardNumber" -> nonEmptyText,
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
    Ok(views.html.registerBank(request.session))
  }


  //TODO create error messages

  /**
    * This function will create Login object for the customer
    */
  def createUser() = Action {
    implicit request => {
      if (Login.findLogin(userForm.bindFromRequest().data("email")).isEmpty) {
        Login.createUser(userForm.bindFromRequest().data("fullName"), userForm.bindFromRequest().data("password"), userForm.bindFromRequest().data("email"))
        Redirect(routes.RegisterController.address())
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

      Address.addAddress(
        LoginSession.getCurrentUser,
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
      CardDetails.addCard(
        LoginSession.getCurrentUser,
        cardForm.bindFromRequest().data("cardholder"),
        cardForm.bindFromRequest().data("cardNumber"),
        cardForm.bindFromRequest().data("cv"),
        cardForm.bindFromRequest().data("expirationMonth"),
        cardForm.bindFromRequest().data("expirationYear")
      )
      Login.toggleLogin()
      Redirect(routes.HomeController.index())
    }
  }

}
