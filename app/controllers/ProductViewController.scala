package controllers

import javax.inject._

import models.Product
import play.api.mvc._

@Singleton
class ProductViewController @Inject() extends Controller {

  def viewProduct(pid: Int) = Action {
    implicit request =>
      Ok(views.html.productView(Product.findProduct(pid).get)(request.session))
  }
}
