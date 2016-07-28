package controllers

import com.google.inject.Inject
import models.{Account_New, Login, MongoDatabaseConnector}
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, text}
import play.api.mvc.{Action, Controller}
import reactivemongo.bson.{BSONDocument, BSONDocumentReader}
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
      var flag = false


      connectToDatabase(CollectionNames.ACCOUNT_COLLECTION, DatabaseNames.ACCOUNT_DATABASE).onComplete {
        case Success(result) =>

          def findAccount()(implicit ec: ExecutionContext, reader: BSONDocumentReader[Account_New]): Unit = {
            val query = BSONDocument(
              "Username" -> loginForm.bindFromRequest().data("email")
            )

            val ppl = result.find(query).cursor[Account_New].collect[List]()
            ppl.map {
              people => for (p <- people)
                if (p.username == loginForm.bindFromRequest().data("email") && p.password == loginForm.bindFromRequest().data("password")) {
                  flag = true
                }
            }
          }
      }

      if (flag) {

        Redirect(routes.HomeController.index()).withSession("connected" -> loginForm.bindFromRequest().data("email"))
      } else {
        Redirect(routes.LoginController.login())

      }


    }


      Redirect(routes.HomeController.index()).withSession("connected" -> loginForm.bindFromRequest().data("email"))

  }

  def logout = Action {
    implicit request =>
      Redirect(routes.HomeController.index()).withSession()
  }
}