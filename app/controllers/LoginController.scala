package controllers

import com.google.inject.Inject
import models.{Login, LoginSession}
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, number, text}
import play.api.mvc.{Action, Controller}

/**
  * Created by rytis on 07/07/16.
  */
class LoginController @Inject extends Controller {
  // this variable will create login form
  val loginForm: Form[Login] = Form(
    mapping(
      "" -> text,
      "" -> text,
      "username" -> nonEmptyText,
      "password" -> nonEmptyText
    )
    (Login.apply)
    (Login.unapply)
  )

  /**
    * this function will create login page
    *
    * @return
    */
  def login() = Action {
    Ok(views.html.login(loginForm))
  }

  //TODO Error messages
  /**
    * this function will validate user
    *
    * @return
    */
  def validateLogin() = Action {
    implicit request => {

      if (Login.findLogin(loginForm.bindFromRequest().data("email")).isEmpty) {
        println("username doesn't exist")
        Redirect(routes.LoginController.login())
      } else {
        val user = Login.findLogin(loginForm.bindFromRequest().data("email")).get
        if (user.pass == loginForm.bindFromRequest().data("password")) {
          println("password is correct")
          Login.toggleLogin()
          LoginSession.setUser(loginForm.bindFromRequest().data("email"))
          Redirect(routes.HomeController.index())

        } else {
          println("incorrect password")

          Redirect(routes.LoginController.login())

        }
      }
    }
  }
}