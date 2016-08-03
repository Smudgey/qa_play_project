import play.api.GlobalSettings
import play.api.mvc.RequestHeader
import play.api.mvc.Results._

import scala.concurrent.Future

object Global extends GlobalSettings {


  override def onHandlerNotFound(request: RequestHeader)  = {
    Future.successful(InternalServerError(
      views.html.notFound()
    ))
  }


}
