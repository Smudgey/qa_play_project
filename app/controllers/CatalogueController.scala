package controllers
import javax.inject._

import _root_.models.{Category, Formatter, Product}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marko on 08/07/2016.
  */
@Singleton
class CatalogueController @Inject() extends Controller with Formatter {

  private val prodFor = Form(
    single(
      "pid" -> number
    )
  )
  def showCategory(cat: String) = Action {
    implicit request =>
    //Take in a Category enumeration as a string and match it to a value
      Ok(views.html.catalogue(prodFor)(Product.listByCat(Category.withName(decodeUri(cat))).toArray)(request.session))
  }

  def show(query: String) = Action {
    implicit request =>
    Ok(views.html.catalogue(prodFor)(Product.searchByName(query).toArray)(request.session))
  }

  def showClearance = Action {
    implicit request =>
      Ok(views.html.catalogue(prodFor)(Product.clearanceStock.toArray)(request.session))
  }


}
