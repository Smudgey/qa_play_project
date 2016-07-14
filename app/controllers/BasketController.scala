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

  def add(pid: Int) = Action {
    implicit request =>
      //Load this product into value for ease
      val p = Product.findProduct(pid).get

      //If product is available, add to basket.  Otherwise show appropriate error message
      if (p.hasXAvailable(1)) {
        OrderLine.addToBasket(OrderLine(p))
      } else {
        //TODO Add some user feedback here
      }

      Ok(views.html.basket(request.session))

  }

  def clear = Action {
    implicit request =>
      OrderLine.clear()
      Ok(views.html.basket(request.session))
  }

  def removeItem(pid: Int) = Action {
    implicit request =>

      if (OrderLine.findOrderLine(pid).isDefined) {
        OrderLine.removeItem(pid)
        Ok(views.html.basket(request.session))
      } else {
        //TODO add error message saying that item has already been deleted
        Ok(views.html.basket(request.session))
      }


  }

  def checkout() = Action {
    implicit request =>
      Ok(views.html.checkoutBasket(request.session))

  }
}
