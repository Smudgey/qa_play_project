package controllers

import com.google.inject.Inject
import models.{Login, Order, Product}
import play.api.mvc.{Action, Controller}

/**
  * Created by Paul on 14/07/2016.
  */
class PurchaseHistoryController @Inject extends Controller {

  def showPurchase = Action {
    implicit request =>
      Product.generate()

      if (request.session.get("connected").isEmpty) {
        Redirect(routes.LoginController.login())
      } else {
        Ok(views.html.purchaseHistory(Order.getOrderHistory(Login.findLogin(request.session.data("connected")).get.lid).toArray)(request.session))
      }
  }

}
