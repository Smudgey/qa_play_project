package controllers

import javax.inject._

import models.{Enquiry, EnquiryDescription, MongoConnection}
import play.api._
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}
import play.api.mvc._

/**
  * Created by Paul on 07/07/2016.
  */
class AboutContactController @Inject() extends Controller {

  val enquiryForm: Form[EnquiryDescription] = Form(
    mapping(
      "name" -> nonEmptyText,
      "email" -> nonEmptyText,
      "enquiry" -> nonEmptyText
    )
    (EnquiryDescription.apply)
    (EnquiryDescription.unapply)
  )

  def aboutAndContact() = Action {
    implicit request =>
      Ok(views.html.aboutContact(enquiryForm)(request.session))
  }

  def createEnquiry() = Action {
    implicit request => {
      Enquiry.createNewEnquiry(
        enquiryForm.bindFromRequest().data("name"),
        enquiryForm.bindFromRequest().data("email"),
        enquiryForm.bindFromRequest().data("enquiry")
      )
      //MongoConnection.end
      MongoConnection.findLuke
      Redirect(routes.AboutContactController.aboutAndContact())
    }
  }

  def printEnquiries() = Action {
    implicit request =>
      Ok(views.html.enquiriesAdmin(request.session))
  }

}
