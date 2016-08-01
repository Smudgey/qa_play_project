package controllers

import com.google.inject.Inject
import models.{Account_New, Login, MongoDatabaseConnector}
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, text}
import play.api.mvc.{Action, Controller}
import reactivemongo.bson.{BSONDocument, BSONDocumentReader}

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success

/**
  * Created by rytis on 07/07/16.
  */
class LoginController @Inject extends Controller with MongoDatabaseConnector {
  // this variable will create login form
  val loginForm: Form[Login] = Form(
    mapping(
      "" -> text,
      "email" -> nonEmptyText,
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
      val account = findAccountByEmail(loginForm.bindFromRequest().data("email"))

      if (account.isEmpty) {
        Redirect(routes.LoginController.login())
      } else {
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