package controllers

import javax.inject._

import models.Product
import play.api.mvc.{Action, Controller}


class ProductViewController @Inject() extends Controller {

  def viewProduct(pid: Int) = Action {
    implicit request =>

      Product.findProduct(pid).map {
        order => Ok(views.html.productView(order)(request.session))
      }.getOrElse(NotFound(views.html.notFound()))
  }
}
