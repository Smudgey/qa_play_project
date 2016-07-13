package controllers

import javax.inject._

import play.api.mvc._
import _root_.models.{OrderLine, Product}
import play.api._

/**
  * Created by Marko on 11/07/2016.
  */
@Singleton
class BasketController @Inject() extends Controller {

  def add(pid: Int) =  Action {

    //Load this product into value for ease
    println("PID: " + pid)
    val p = Product.findProduct(pid).get

    //If product is available, add to basket.  Otherwise show appropriate error message
    if (p.hasXAvailable(1)) {
      OrderLine.addToBasket(OrderLine(p))
    } else {
      //TODO Add some user feedback here
    }

    Ok(views.html.basket())

  }

  def clear = Action {
    OrderLine.clear()
    Ok(views.html.basket())
  }

  def checkout = Action {

    Ok(views.html.checkoutBasket())

  }
}
