package controllers
import javax.inject._
import play.api._
import play.api.mvc._
import _root_.models.{Product}

class ProductViewController @Inject() extends Controller {


  def viewProduct(pid: Int) = Action {

    implicit request =>
      Product.findProduct(pid).map {
        product => Ok(views.html.productview(product))
      }.getOrElse(NotFound)
  }


}
