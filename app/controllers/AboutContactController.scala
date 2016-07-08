package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
  * Created by Paul on 07/07/2016.
  */
class AboutContactController @Inject() extends Controller{

  def aboutAndContact = Action {
    Ok(views.html.aboutContact())
  }

}
