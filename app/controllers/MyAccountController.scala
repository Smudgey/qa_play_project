package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import views.html.MyAccount

/**
  * Created by Administrator on 13/07/2016.
  */
class MyAccountController @Inject extends Controller{
  def MyAccount = Action {
    implicit request =>
    Ok(views.html.MyAccount(request.session))
  }
}
