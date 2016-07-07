package controllers

import javax.inject._
import play.api._
import play.api.mvc._

class ProductViewController @Inject() extends Controller {


  def viewProoduct = Action {
    Ok(views.html.productview())
  }

}
