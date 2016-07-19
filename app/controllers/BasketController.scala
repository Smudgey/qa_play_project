package controllers

import javax.inject._

import play.api.mvc._
import _root_.models.{Login, OrderLine, Product}
import play.api._
import play.api.data.Form
import play.api.data.Forms._

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marko on 11/07/2016.
  */
@Singleton
class BasketController @Inject() extends Controller {

  private val prodFor = Form(
    single(
      "pid" -> number
    )
  )

  private val updateFor = Form(
    tuple(
      "quant"  -> list(number),
      "pid"       -> list(number)
    )
  )

  def basket = Action {
    implicit request =>
      Ok(views.html.basket(updateFor)(request.session))
  }

  def add = Action {
    implicit request =>
      //Load this product into value for ease
      val p = Product.findProduct(prodFor.bindFromRequest().get).get

      //If product is available, add to basket.  Otherwise show appropriate error message
      if (p.hasXAvailable(1)) {
        OrderLine.addToBasket(OrderLine(p))
      } else {
        //TODO Add some user feedback here
      }
      Redirect(routes.BasketController.basket)
  }

  def clear = Action {
    implicit request =>
      OrderLine.clear()
      Redirect(routes.BasketController.basket)
  }

  def update = Action {
    implicit request =>
      val quant = updateFor.bindFromRequest().get._1(0)
      val pid = updateFor.bindFromRequest().get._2(0)

      OrderLine.findOrderLine(pid).get.quantity = quant
      Product.findProduct(pid).get.stock -= quant

      OrderLine.getSize
      Redirect(routes.BasketController.basket)
  }

  def removeItem(pid: Int) = Action {
    implicit request =>

      if (OrderLine.findOrderLine(pid).isDefined) {
        OrderLine.removeItem(pid)
        Redirect(routes.BasketController.basket)
      } else {
        //TODO add error message saying that item has already been deleted
        Redirect(routes.BasketController.basket)
      }
  }

//  def checkout() = Action {
//    implicit request =>
//      Ok(views.html.checkout()(request.session))
//
//  }

}
