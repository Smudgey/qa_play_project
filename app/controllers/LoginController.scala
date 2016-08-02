package controllers

import com.google.inject.Inject
import models.MongoDatabaseConnector
import play.api.data.Form
import play.api.data.Forms.{nonEmptyText, tuple}
import play.api.mvc.{Action, Controller}
import play.api.i18n.Messages.Implicits._
import play.api.i18n.{I18nSupport, Messages, MessagesApi}


/**
  * Created by rytis on 07/07/16.
  */
class LoginController @Inject()(val messagesApi: MessagesApi)  extends Controller with MongoDatabaseConnector with I18nSupport  {
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
      Ok(views.html.login(request.session, request.flash))
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
        Redirect(routes.LoginController.login()).flashing("type" -> "fail", "class" -> Messages("login.fail.class"), "message" -> Messages("login.fail"))
      } else {
        if (account.head.username == loginForm.bindFromRequest().data("email") && account.head.password == loginForm.bindFromRequest().data("password")) {
          Redirect(routes.HomeController.index()).withSession("connected" -> loginForm.bindFromRequest().data("email"))
        } else {
          Redirect(routes.LoginController.login()).flashing("type" -> "fail", "class" -> "alert alert-danger", "message" -> Messages("login.fail"))
        }
      }
    }


  }

  def logout = Action {
    implicit request =>
      Redirect(routes.HomeController.index()).withSession()
  }
}