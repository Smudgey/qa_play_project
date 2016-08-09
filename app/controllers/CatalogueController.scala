package controllers
import javax.inject._

import _root_.models.{Category, Formatter, MongoDatabaseConnector, Product}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import reactivemongo.api.MongoConnectionOptions

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marko on 08/07/2016.
  */
@Singleton
class CatalogueController @Inject() extends Controller with Formatter with MongoDatabaseConnector{

  private val prodFor = Form(
    single(
      "itemID" -> text
    )
  )
  def showCategory(cat: String) = Action {
    implicit request =>
    //Take in a Category enumeration as a string and match it to a value
      println(getCatVal(cat))
      Ok(views.html.catalogue(findByCategory(getCatVal(cat)))(request.session))

  }

  def show(query: String) = Action {
    implicit request =>
    Ok(views.html.catalogue(allProducts(query))(request.session))
  }

  def showClearance = Action {
    implicit request =>
      Ok(views.html.catalogue(Array())(request.session))
  }

  def priceFilter(min: Double, max: Double) = Action {
    implicit request =>
//      Ok(views.html.catalogue(prodFor)(Product.searchByPrice(min, max).toArray)(request.session))
      Redirect(routes.HomeController.index())
  }
}
