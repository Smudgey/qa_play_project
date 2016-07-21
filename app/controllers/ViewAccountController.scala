package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller, Flash}
import play.api._
import play.api.mvc
import play.api.mvc.Controller
import play.api.data._
import play.api.data.Forms._
import models.{CustomerDetails, LoginSession}
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 20/07/2016.
  */
class ViewAccountController @Inject extends Controller {
  def ViewAccount() = Action {
    implicit request =>

      //Temporary check statement whilst suing dummy data
      if(request.session.get("connected").isEmpty) {
        Redirect(routes.HomeController.index())
      } else {
        Ok(views.html.ViewAccount(CustomerDetails.findCustomerEmail(request.session.data("connected")))(request.session))

      }
  }
}
