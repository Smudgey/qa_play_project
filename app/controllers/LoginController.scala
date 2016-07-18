package controllers

import com.google.inject.Inject
import models.{Login, LoginSession}
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, number, text}
import play.api.mvc.{Action, Controller}
import play.api.mvc.Cookie
import play.api.mvc.DiscardingCookie

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
    implicit request => {
      Ok(views.html.login(request.session))
    }
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

          Redirect(routes.HomeController.index()).withSession("connected" -> loginForm.bindFromRequest().data("email"))
        } else {
          println("incorrect password")
          Redirect(routes.LoginController.login())
        }
      }
    }
  }

  def logout = Action {
    implicit request =>
      Redirect(routes.HomeController.index()).withSession()
  }
}