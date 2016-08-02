package controllers

import com.google.inject.Inject
import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import reactivemongo.bson.BSONDocumentReader

import scala.concurrent._
import ExecutionContext.Implicits.global

/**
  * Created by Paul on 14/07/2016.
  *
  * Last worked on by Mark on 02/08/2016.
  */
class PurchaseHistoryController @Inject extends Controller with MongoDatabaseConnector{

  /**
    * this function will display customers order history
    *
    * @return
    */
  def showPurchase = Action {
    Redirect(routes.PurchaseHistoryController.viewPurchase())
  }

  def viewPurchase(implicit ec: ExecutionContext, reader: BSONDocumentReader[Order_New]) = Action {
    implicit request =>

      if (request.session.get("connected").isEmpty) {
        Redirect(routes.LoginController.login())
      } else {

        val accId = findAccountByEmail(request.session.data("connected")).head.accountID
        println(accId)


//        connectToDatabase(CollectionNames.ORDER_COLLECTION, DatabaseNames.ORDERS_DATABASE).onComplete(
//          for (order <- orders) {
//            if(order.)
//          }
//        )



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
      Redirect(routes.PurchaseHistoryController.viewPurchase())
  }

}
