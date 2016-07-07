package controllers

import models.{LoginList, ProductList}
import play.api.mvc._

class Application extends Controller {

  def diddly = Action {
    var products = new ProductList
    products.generate()
    var logins = new LoginList
    logins.generate()
    Ok(views.html.test(products, logins))
  }

}