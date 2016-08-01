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
      "fullName" -> nonEmptyText,
      "email" -> nonEmptyText,
      "phone" -> nonEmptyText,
      "password" -> nonEmptyText
    )
  )
  // this will create address form
  private val addressForm = Form(
    tuple(
      "houseNumber" -> nonEmptyText,
      "streetName" -> nonEmptyText,
      "town" -> nonEmptyText,
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
      if (Login.findLogin(userForm.bindFromRequest().data("email")).isEmpty) {
        //Redirect to next step, address, passing both the session and flash data
        Redirect(routes.RegisterController.address()).withSession(
          "Username" -> userForm.bindFromRequest().data("email"),
          "Password" -> userForm.bindFromRequest().data("password"),
          "Name" -> userForm.bindFromRequest().data("fullName"),
          "Phone" -> "07758787847")
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
        "Username" -> request.session.data("Username"),
        "Password" -> request.session.data("Password"),
        "Name" -> request.session.data("Name"),
        "Phone" -> "07758787847",
        "AddressLine1" -> s" ${addressForm.bindFromRequest().data("houseNumber")} ${addressForm.bindFromRequest().data("streetName")}",
        "AddressLine2" -> addressForm.bindFromRequest().data("town"),
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
      val flag = false
      //connect to the specified database and collection
      connectToDatabase(CollectionNames.ACCOUNT_COLLECTION, DatabaseNames.ACCOUNT_DATABASE).onComplete {
        case Success(result) =>
          if (findAccountByEmail(request.session.data("Username")).isEmpty) {
            //Create addresses
            val addressArray = Array[Address_New](
              Address_New(
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
            Account_New.create(result, Account_New(
              randomID,
              request.session.data("Username"),
              request.session.data("Password"),
              request.session.data("Name"),
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
        Redirect(routes.HomeController.index()).withSession("connected" -> request.session.data("Username"))
      } else {
        //user already exists
        Redirect(routes.RegisterController.register())
      }
    }


    /*val account = Account.getAccountViaEmail(Login.findLogin(request.session.data("tmp")).get.lid).get
    val randomCardID = randomID
    account.cardID = randomCardID
    CardDetails.addCard(
      account.cardID,
      cardForm.bindFromRequest().data("cardholder"),
      cardForm.bindFromRequest().data("cardnumber"),
      cardForm.bindFromRequest().data("cv"),
      cardForm.bindFromRequest().data("expirationMonth"),
      cardForm.bindFromRequest().data("expirationYear"))
    val tmp = request.session.data("tmp")
    Redirect(routes.HomeController.index()).withSession().withSession("connected" -> tmp.replaceAll(" ", ""))*/
  }
}
