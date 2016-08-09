package controllers

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}

/**
  * Created by rytis on 12/07/16.
  */
class SearchController @Inject extends Controller {

  val searchForm = Form(
    single(
      "searchQuery" -> nonEmptyText
    )
  )

  val filterForm = Form(
    tuple(
      "price-min"-> default(number, 0),
      "price-max"-> default(number, 9999)
    )
  )

  def search() = Action {
    implicit request => {
      Redirect(routes.CatalogueController.show(searchForm.bindFromRequest().data("searchQuery")))
    }
  }
  def filterSearch = Action {
    implicit request => {
      Redirect(routes.CatalogueController.priceFilter(filterForm.bindFromRequest().get._1, filterForm.bindFromRequest().get._2))
    }
  }

}
