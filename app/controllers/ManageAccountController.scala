package controllers
import com.google.inject.Inject
import play.api.mvc.{Action, Controller, Flash}
import play.api._
import play.api.mvc
import play.api.mvc.Controller

/**
  * Created by Administrator on 07/07/2016.
  */
class ManageAccountController @Inject extends Controller {
def ManageAccounts = Action {
  Ok(views.html.ManageAccount())
}
}
