package controllers

import com.google.inject.Inject
import models.Login
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}


/**
  * Created by rytis on 07/07/16.
  */
class RegisterController @Inject extends Controller {

  // this will create register form
  private val registerForm: Form[Login] = Form(
    mapping(
      "" -> text,
      "fullName" -> nonEmptyText,
      "password" -> nonEmptyText,
      "email" -> nonEmptyText
    )
    (Login.apply)
    (Login.unapply)
  )

  /**
    * this function will create the register page
    */
  def register = Action {
    Ok(views.html.register())
  }

  //TODO create error messages
  /**
    * This function will create user based on user input
    */
  def createUser() = Action {
    implicit request => {
      if (Login.findLogin(registerForm.bindFromRequest().data("email")).isEmpty) {
        if (Login.createUser(
          registerForm.bindFromRequest().data("fullName"),
          registerForm.bindFromRequest().data("password"),
          registerForm.bindFromRequest().data("email"))) {
          Redirect(routes.HomeController.index())
        } else {
          Redirect(routes.RegisterController.register())
        }
      } else {
        Redirect(routes.RegisterController.register())
      }
    }
  }
}
