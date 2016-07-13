package controllers

import com.google.inject.Inject
import models.{Login, Product, SearchQuery}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}

/**
  * Created by rytis on 12/07/16.
  */
class SearchController @Inject extends Controller {

  val searchForm: Form[SearchQuery] = Form(
    mapping(
      "searchQuery" -> nonEmptyText
    )
    (SearchQuery.apply)
    (SearchQuery.unapply)
  )

  def search() = Action {
    implicit request => {
      Redirect(routes.CatalogueController.show(searchForm.bindFromRequest().data("searchQuery")))
    }
  }

}
