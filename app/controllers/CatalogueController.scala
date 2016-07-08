package controllers

import javax.inject._
import _root_.models.{Product, Login}
import play.api.mvc._

/**
  * Created by Marko on 08/07/2016.
  */
@Singleton
class CatalogueController @Inject() extends Controller {

  def doGrid = Action {

    Product.generate()
    Login.generate()
    Ok(views.html.catalogue())
  }

}
