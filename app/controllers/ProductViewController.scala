package controllers
import javax.inject._

import models.Product
import play.api.mvc._

class ProductViewController @Inject() extends Controller {


  def viewProduct(pid: Int) = Action {

    implicit request =>
      Product.findProduct(pid).map {
        product => Ok(views.html.productview(product))
      }.getOrElse(NotFound)
  }


}
