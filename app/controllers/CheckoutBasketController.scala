
package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class CheckoutBasketController @Inject() extends Controller {





  def checkoutBasket = Action {
    Ok(views.html.checkoutBasket())
  }


}

