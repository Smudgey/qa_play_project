package controllers

import javax.inject._

import play.api.mvc._
import models._
import play.api._
import play.api.data.Form
import play.api.data.Forms._

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marko on 11/07/2016.
  */
@Singleton
class BasketController @Inject() extends Controller with MongoDatabaseConnector {

  private val prodFor = Form(
    single(
      "pid" -> text
    )
  )

  private val updateFor = Form(
    tuple(
      "quant" -> list(number),
      "pid" -> list(text)
    )
  )

  def basket = Action {
    implicit request =>
      Ok(views.html.basket(updateFor)(request.session))
  }

  def add(pid: String) = Action {
    implicit request =>
      //Load this product into value for ease
      println(pid)
      val p = findProductByID(pid)

      //If product is available, add to basket.  Otherwise show appropriate error message
      if (p.hasXAvailable(1)) {
        OrderLine_New.addToBasket(OrderLine_New(p.itemID, 1, p.price))
        println()
      } else {
        //TODO Add some user feedback here
      }
      Redirect(routes.BasketController.basket()).withSession("basketItemCount" -> OrderLine.getSize.toString)
  }

  def clear = Action {
    implicit request =>
      OrderLine.clear()
      println("Basket: " + Product.inventory.size + "\nClearance" + Product.clearanceStock.size )
      Redirect(routes.BasketController.basket).withSession("basketItemCount" -> "")
  }

  def update = Action {
    implicit request =>
      val quant = updateFor.bindFromRequest().get._1(0)
      val pid = updateFor.bindFromRequest().get._2(0)

      val p = findProductByID(pid)

      p.stock += OrderLine_New.findOrderLine(pid).get.quantity

      OrderLine_New.findOrderLine(pid).get.quantity = quant
      p.stock -= quant

      OrderLine.getSize
      Redirect(routes.BasketController.basket)
  }

  def removeItem(pid: String) = Action {
    implicit request =>

      if (OrderLine_New.findOrderLine(pid).isDefined) {
        OrderLine_New.removeItem("")
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
