package controllers

import akka.actor.FSM.Failure
import com.google.inject.Inject
import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import play.mvc.Http.Session
import reactivemongo.bson.{BSONDocument, BSONDocumentReader}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.ExecutionContext
import scala.util.Success


/*
  * Create By rytis
  *
  * Last worked on by Rytis on 26/07/2916
  */

class RegisterController @Inject extends Controller with Formatter with MongoDatabaseConnector {

  // this will create register form
  private val userForm = Form(
    tuple(
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "phone" -> nonEmptyText,
      "email" -> nonEmptyText,
      "password" -> nonEmptyText
    )
  )
  // this will create address form
  private val addressForm = Form(
    tuple(
      "addressLine1" -> nonEmptyText,
      "addressLine2" -> nonEmptyText,
      "city" -> nonEmptyText,
      "county" -> nonEmptyText,
      "postcode" -> nonEmptyText
    )
  )
  // this will create card detail form
  private val cardForm = Form(
    tuple(
      "cardholder" -> nonEmptyText,
      "cardnumber" -> nonEmptyText,
      "expirationMonth" -> nonEmptyText,
      "expirationYear" -> nonEmptyText
    )
  )

  /**
    * this function will display login part of register form
    */
  def register = Action {
    implicit request =>
      Ok(views.html.registerStart(request.session))
  }

  /**
    * this function will display address part of register form
    */
  def address = Action {
    implicit request =>
      Ok(views.html.registerAddress(request.session))
  }

  /**
    * this function will display card detail part of register form
    */
  def bank = Action {
    implicit request =>
      Ok(views.html.registerCard(request.session))
  }


  //TODO create error messages

  /**
    * This function will create Login object for the customer
    */
  def createUser() = Action {
    implicit request => {
      if (findAccountByEmail(userForm.bindFromRequest().data("email")).isEmpty) {
        //Redirect to next step, address, passing both the session and flash data
        Redirect(routes.RegisterController.address()).withSession(
          "Email" -> userForm.bindFromRequest().data("email"),
          "Password" -> userForm.bindFromRequest().data("password"),
          "FirstName" -> userForm.bindFromRequest().data("firstName"),
          "LastName" -> userForm.bindFromRequest().data("lastName"),
          "Phone" -> userForm.bindFromRequest().data("phone"))
      } else {
        //user already exists
        Redirect(routes.RegisterController.register())
      }
    }
  }

  /**
    * this function will create Address object for customer
    *
    * @return
    */
  def createAddress() = Action {
    implicit request => {
      //Pass through all the data to the next step in the register process
      Redirect(routes.RegisterController.bank()).withSession(
        "Email" -> request.session.data("Email"),
        "Password" -> request.session.data("Password"),
        "FirstName" -> request.session.data("FirstName"),
        "LastName" -> request.session.data("LastName"),
        "Phone" -> request.session.data("Phone"),
        "AddressLine1" -> addressForm.bindFromRequest().data("addressLine1"),
        "AddressLine2" -> addressForm.bindFromRequest().data("addressLine2"),
        "AddressCity" -> addressForm.bindFromRequest().data("city"),
        "AddressCounty" -> addressForm.bindFromRequest().data("county"),
        "AddressPostcode" -> addressForm.bindFromRequest().data("postcode")
      )
    }
  }


  /**
    * this function will create CardDetails object for the customer
    *
    * @return
    */
  def createCard() = Action {
    implicit request => {
      //flag used to check if user already exists
      val flag = true
      //connect to the specified database and collection
      connectToDatabase(CollectionNames.ACCOUNT_COLLECTION, DatabaseNames.ACCOUNT_DATABASE).onComplete {
        case Success(result) =>
          if (findAccountByEmail(request.session.data("Email")).isEmpty) {
            //Create addresses
            val addressArray = Array[Address](
              Address(
                request.session.data("AddressLine1"),
                request.session.data("AddressLine2"),
                request.session.data("AddressCity"),
                request.session.data("AddressCounty"),
                request.session.data("AddressPostcode")
              )
            )

            //create payment cards
            val paymentCardsArray = Array[PaymentCards](
              PaymentCards(
                cardForm.bindFromRequest().data("cardholder"),
                cardForm.bindFromRequest().data("cardnumber"),
                cardForm.bindFromRequest().data("expirationMonth") + "/" + cardForm.bindFromRequest().data("expirationYear")
              )
            )
            //Create new Account in database
            Account.create(result, Account(
              randomID,
              request.session.data("Email"),
              request.session.data("Password"),
              request.session.data("FirstName"),
              request.session.data("LastName"),
              request.session.data("Phone"),
              addressArray,
              paymentCardsArray
            ))
          }
      }

      //wait for the connection method to finish, as it may set the flag
      Thread.sleep(3000)
      if (flag) {
        //new user was created, so write their name to the connection key in session and redirect
        Redirect(routes.HomeController.index()).withSession("connected" -> request.session.data("Email"))
      } else {
        //user already exists
        Redirect(routes.RegisterController.register())
      }
    }
  }
}
