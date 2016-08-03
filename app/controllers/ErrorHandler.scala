package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller}

/**
  * Created by rytis on 02/08/16.
  */
class ErrorHandler @Inject extends Controller {


  def notFound() = Action {
    implicit request =>
      Ok(views.html.notFound())
  }

}
