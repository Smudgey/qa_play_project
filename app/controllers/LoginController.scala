package controllers

import com.google.inject.Inject
import models.MongoDatabaseConnector
import play.api.data.Form
import play.api.data.Forms.{tuple, nonEmptyText}
import play.api.mvc.{Action, Controller}

/**
  * Created by rytis on 07/07/16.
  */
class LoginController @Inject extends Controller with MongoDatabaseConnector {
  // this variable will create login form
  val loginForm = Form(
    tuple(
      "email" -> nonEmptyText,
      "password" -> nonEmptyText
    )
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
      val account = findAccountByEmail(loginForm.bindFromRequest().data("email"))

      if (account.isEmpty) {
        Redirect(routes.LoginController.login())
      } else {

        println(account.head.username)
        if (account.head.username == loginForm.bindFromRequest().data("email") && account.head.password == loginForm.bindFromRequest().data("password")) {
          Redirect(routes.HomeController.index()).withSession("connected" -> loginForm.bindFromRequest().data("email"))
        } else {
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