package controllers

import javax.inject._
import play.api.mvc._
import _root_.models.{OrderLine, Product}

/**
  * Created by Marko on 11/07/2016.
  */
@Singleton
class BasketController @Inject() extends Controller {

  def add(pid: Int) =  Action {
    Product.findProduct(pid).map {
      product => Ok(views.html.basket(product))
    }.getOrElse(NotFound)
  }

}
