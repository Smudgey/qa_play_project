package controllers
import javax.inject._

import models.Product
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

@Singleton
class ProductViewController @Inject() extends Controller {

  private val prodFor = Form(
    single(
      "pid" -> number
    )
  )

  def viewProduct(pid: Int) = Action {
    implicit request =>
      Product.findProduct(pid).map {
        product => Ok(views.html.productview(prodFor)(product))
      }.getOrElse(NotFound)
  }


}
