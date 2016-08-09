package controllers

import javax.inject._

import models.MongoDatabaseConnector
import play.api.mvc.{Action, Controller}


@Singleton
class ProductViewController @Inject() extends Controller with MongoDatabaseConnector{


  def viewProduct(pid: String) = Action {
    implicit request =>

      Ok(views.html.productView(findProductByID(pid))(request.session))

  }
}
