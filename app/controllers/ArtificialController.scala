
package controllers

import javax.inject._
import play.api._
import play.api.mvc._


/**
  * Created by Administrator on 12/07/2016.
  */
class ArtificialController @Inject extends Controller{

  def artificial = Action {
    Ok(views.html.artificial())
  }

}
