package controllers


import javax.inject.Inject

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}
import play.api.mvc.{Action, Controller}

class CustomerSatisfactionController @Inject extends Controller {

  private val csf = Form[CustomerSatisfactionObject](
    mapping(
      "" -> nonEmptyText,
      "" -> nonEmptyText,
      "comment" -> nonEmptyText,
      "rating" -> nonEmptyText
    )
    (CustomerSatisfactionObject.apply)
    (CustomerSatisfactionObject.unapply)
  )

  def show = Action {
    implicit request =>
      Ok(views.html.customerSatisfactionForm(request.session))
  }

  def submitForm = Action {
    implicit request =>
      println(csf.bindFromRequest().data("comment"))
      println(csf.bindFromRequest().data("rating"))
      Redirect(routes.CustomerSatisfactionController.show())
  }

}
