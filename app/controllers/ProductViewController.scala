package controllers

import javax.inject._

import models.Product
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

@Singleton
class ProductViewController @Inject() extends Controller {

  def viewProduct(pid: Int) = Action {
    implicit request =>
      Product.generate()
      Ok(views.html.productView(Product.findProduct(pid).get)(request.session))
  }
}
