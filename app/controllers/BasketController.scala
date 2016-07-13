package controllers

import javax.inject._

import play.api.mvc._
import _root_.models.{Login, OrderLine, Product}
import play.api._
import play.api.data.Form
import play.api.data.Forms._

/**
  * Created by Marko on 11/07/2016.
  */
@Singleton
class BasketController @Inject() extends Controller {

//  val loginForm: Form[Login] = Form(
//    mapping(
//      "basket" -> seq(number)
//    )
//    (Login.apply)
//    (Login.unapply)
//  )

  def add(pid: Int) =  Action {

    //Load this product into value for ease
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

  def checkout() = Action {

    Ok(views.html.checkoutBasket())

  }
}
