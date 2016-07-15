package controllers

import com.google.inject.Inject
import models.{Order, Product}
import play.api.mvc.{Action, Controller}

/**
  * Created by Paul on 14/07/2016.
  */
class PurchaseHistoryController @Inject extends Controller{

  def showPurchase = Action{
    implicit request =>
      Product.generate
      Order.generate
    Ok(views.html.purchaseHistory(request.session))
  }

}
