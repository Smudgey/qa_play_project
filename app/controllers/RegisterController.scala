package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller}

/**
  * Created by rytis on 07/07/16.
  */
class RegisterController @Inject extends Controller {

  def register = Action {
    Ok(views.html.register())
  }
}
