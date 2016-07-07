package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller}

/**
  * Created by rytis on 07/07/16.
  */
class LoginController @Inject extends Controller{

  def login() = Action {
    Ok(views.html.login())
  }
}
