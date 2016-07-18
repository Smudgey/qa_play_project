package controllers

import javax.inject._

import models.{Enquiry, EnquiryDescription}
import play.api._
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, number, text}
import play.api.mvc._
import play.api.mvc.Flash

/**
  * Created by Paul on 07/07/2016.
  */
class AboutContactController @Inject() extends Controller{

  val enquiryForm: Form[EnquiryDescription] = Form(
    mapping(
      "name" -> nonEmptyText,
      "email" -> nonEmptyText,
      "enquiry" -> nonEmptyText
    )
    (EnquiryDescription.apply)
    (EnquiryDescription.unapply)
  )

  def aboutAndContact = Action {
    implicit request =>
    Ok(views.html.aboutContact(enquiryForm)(request.session))
  }

  def createEnquiry() = Action {
    implicit request => {
      val name = enquiryForm.bindFromRequest().data("name")
      val email = enquiryForm.bindFromRequest().data("email")
      val enquiry = enquiryForm.bindFromRequest().data("enquiry")
      Enquiry.createNewEnquiry(name, email, enquiry)
      Redirect(routes.AboutContactController.aboutAndContact)
    }
  }

  def printEnquiries() = Action{
    implicit request =>
    Ok(views.html.enquiriesAdmin(request.session))
  }

}
