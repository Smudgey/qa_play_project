
  package controllers
  import javax.inject._
  import play.api._
  import play.api.mvc._
  /**
    * Created by Administrator on 12/07/2016.
    */
  class ClothingController  @Inject extends Controller{

    def clothing = Action {
      Ok(views.html.clothing())
    }
  }



