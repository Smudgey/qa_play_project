package controllers

import javax.inject._

import models.Product
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

/**
  * Created by Marko on 08/07/2016.
  */
@Singleton
class CatalogueController @Inject() extends Controller {

  private val prodFor = Form(
    single(
      "pid" -> number
    )
  )

  def show(query: String) = Action {
    implicit request =>
    if (Product.list.isEmpty) {
      Product.generate()
    }
    println(query)
    println(Product.searchByName(query).isEmpty)
    Ok(views.html.catalogue(prodFor)(Product.searchByName(query).toArray)(request.session))
  }
}
