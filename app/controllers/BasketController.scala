package controllers

import javax.inject._

import models.{OrderLine, Product}
import play.api.mvc._

/**
  * Created by Marko on 11/07/2016.
  */
@Singleton
class BasketController @Inject() extends Controller {

  //TODO separate the add functionality from the show functionality.

  def add(pid: Int) = Action {

    val p = Product.findProduct(pid)
    OrderLine.addToBasket(OrderLine(p.get))

    Ok(views.html.basket())
    //    p.map {
    //      product =>
    //    }.getOrElse(NotFound)
  }

  def clear = Action {
    OrderLine.clear()
    Ok(views.html.basket())
  }
}
