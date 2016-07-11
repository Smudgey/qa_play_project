package controllers

import javax.inject._
import play.api.mvc._
import _root_.models.{OrderLine, Product}

/**
  * Created by Marko on 11/07/2016.
  */
@Singleton
class BasketController @Inject() extends Controller {

  //TODO separate the add functionality from the show functionality.

  def add(pid: Int) =  Action {

    val p = Product.findProduct(pid)

    OrderLine.addToBasket(OrderLine(p.get))

    p.map {
      product => Ok(views.html.basket(p.get))
    }.getOrElse(NotFound)
  }

  def show: Unit = {
    Ok(views.html....)
  }
}
