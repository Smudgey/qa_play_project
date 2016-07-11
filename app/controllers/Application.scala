package controllers

import models.{Login, Product}
import play.api.mvc._

class Application extends Controller {

  def diddly = Action {

    Product.generate()

    Ok(views.html.test())
  }

  def loginDrop = Action {
    Ok(views.html.dropDownLogin())
  }

}
