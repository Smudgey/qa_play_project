package controllers

import javax.inject._

import models.{MongoDatabaseConnector, Product}
import play.api.mvc._

@Singleton
class ProductViewController @Inject() extends Controller with MongoDatabaseConnector{

  def viewProduct(pid: String) = Action {
    implicit request =>
      Ok(views.html.productView(findProductByID(pid))(request.session))
  }
}
