package controllers

import com.google.inject.Inject
import models.{Account, Login, Order}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}

/**
  * Created by Paul on 14/07/2016.
  *
  * Last worked on by Rytis on 25/07/2016.
  */
class PurchaseHistoryController @Inject extends Controller {

  /**
    * this function will display customers order history
    *
    * @return
    */
  def showPurchase = Action {
    implicit request =>

      if (request.session.get("connected").isEmpty) {
        Redirect(routes.LoginController.login())
      } else {
        Ok(views.html.purchaseHistory(Order.getOrderHistory(Account.getAccountViaEmail(Login.findLogin(request.session.data("connected")).get.lid).get.accountID).toArray)(request.session))
      }
  }

  // form for getting star rating amount
  private val starForm = Form(
    tuple(
      "rating" -> number,
      "oid" -> number
    )
  )


  /**
    * this function will add star rating to user order
    *
    * @return
    */
  def orderRating = Action {
    implicit request =>
      Order.setStarRating(starForm.bindFromRequest().data("oid"), starForm.bindFromRequest().data("rating").toInt)
      Redirect(routes.PurchaseHistoryController.showPurchase())
  }

}
