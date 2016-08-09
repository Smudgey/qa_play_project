package controllers

import com.google.inject.Inject
import models._
import play.api.data.Forms._
import play.api.data._
import play.api.mvc.{Action, Controller}
import reactivemongo.bson.BSONDocument

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

/*
  * Create By rytis
  *
  * Last worked on by Mark on 09/08/2016
  */

class AccountController @Inject extends Controller with Formatter with MongoDatabaseConnector {

  //form for customer adding card details
  private val cardForm: Form[CardDetails] = Form(
    mapping(
      "" -> text,
      "cardholder" -> nonEmptyText,
      "cardnumber" -> nonEmptyText,
      "expirationMonth" -> nonEmptyText,
      "expirationYear" -> nonEmptyText
    )
    (CardDetails.apply)
    (CardDetails.unapply)
  )
  //form for updating customer details
  private val updateDetailsForm = Form(
    tuple(
      "fname" -> text,
      "lname" -> text,
      "email" -> text,
      "phone" -> text
    )
  )
  //form form adding address

  private val addressFormNew = Form(
    tuple(
      "addressLine1" -> nonEmptyText,
      "addressLine2" -> nonEmptyText,
      "city" -> nonEmptyText,
      "county" -> nonEmptyText,
      "postcode" -> nonEmptyText
    )
  )


  /**
    * this function will display manage account details page
    */
  def manageAccounts = Action {
    implicit request =>
      Ok(views.html.manageAccount(request.session))
  }

  /**
    * this function will update the customer details with new details
    *
    * @return
    */
  def updateAccount() = Action {
    implicit request =>

      connectToDatabase(CollectionNames.ACCOUNT_COLLECTION, DatabaseNames.ACCOUNT_DATABASE).onComplete {
        case Success(result) =>
          val query = BSONDocument("Email" -> request.session.data("connected"))
          val modifier = BSONDocument(
            "$set" -> BSONDocument(
              "First Name" -> updateDetailsForm.bindFromRequest().data("fname"),
              "Last Name" -> updateDetailsForm.bindFromRequest().data("lname"),
              "Email" -> updateDetailsForm.bindFromRequest().data("email"),
              "Phone" -> updateDetailsForm.bindFromRequest().data("phone")
            )
          )
          result.update(query, modifier)
        case Failure(fail) =>
          throw fail
      }
      Redirect(routes.AccountController.viewAccount()).withSession("connected" -> updateDetailsForm.bindFromRequest().data("email"))

  }

  /**
    * this function will display account details page
    *
    * @return
    */
  def viewAccount = Action {
    implicit request =>

      //Temporary check statement whilst suing dummy data
      if (request.session.get("connected").isEmpty) {
        Redirect(routes.HomeController.index())
      } else {
        Ok(views.html.viewAccount(findAccountByEmail(request.session.data("connected")).head)) //searches for the account related to the email address in the cookie
      }
  }

  /**
    * this function page will display all cards customer has added
    *
    * @return
    */
  def viewCard = Action {
    implicit request =>
      Ok(views.html.viewCard(findAccountByEmail(request.session.data("connected")).head.paymentCards)(request.session))
  }


  /**
    * this function will display all address customer has added
    *
    * @return
    */
  def viewAddress = Action {
    implicit request =>
      Ok(views.html.viewAddress(findAccountByEmail(request.session.data("connected")).head.address))
  }

  /**
    * this function will display page that will allow customer to add new cards
    *
    * @return
    */
  def addNewCard() = Action {
    implicit request =>
      Ok(views.html.addNewCard(request.session))
  }

  /**
    * this function will display page that will allow customer to add new address
    *
    * @return
    */
  def addNewAddress() = Action {
    implicit request =>
      Ok(views.html.addNewAddress(request.session))
  }

  /**
    * this function will allow the customer to add new cards
    *
    * @return
    */
  def addCard() = Action {
    implicit request =>

      connectToDatabase(CollectionNames.ACCOUNT_COLLECTION, DatabaseNames.ACCOUNT_DATABASE).onComplete {
        case Success(result) =>
          val query = BSONDocument("Email" -> request.session.data("connected"))
          val modifier = BSONDocument(
            "$push" -> BSONDocument(
              "PaymentCards" -> BSONDocument(
                "CardName" -> cardForm.bindFromRequest().data("cardholder"),
                "CardNumber"-> cardForm.bindFromRequest().data("cardnumber"),
                "CardExpiry" -> (cardForm.bindFromRequest().data("expirationMonth") + "/" + cardForm.bindFromRequest().data("expirationYear"))
                )
              )
          )
          println(query +"\n" +modifier)
          result.update(query, modifier)
        case Failure(fail) =>
          throw fail
      }
      Redirect(routes.AccountController.addNewCard())
  }

  /**
    * this function will remove customer card
    *
    * @param cardNumber Long card number
    * @return
    */
  def removeCard(cardNumber: String) = Action {
    implicit request =>


      connectToDatabase(CollectionNames.ACCOUNT_COLLECTION, DatabaseNames.ACCOUNT_DATABASE).onComplete {
        case Success(collection) =>
          val query = BSONDocument(
            "$pull" -> BSONDocument(
              "PaymentCards" -> BSONDocument(
                "CardNumber" -> cardNumber
              )
            )
          )
          val person = BSONDocument(
            "Email" -> request.session.data("connected")
          )
          collection.update(person, query)
        case Failure(fail) =>
      }

      Thread.sleep(3000)
      Redirect(routes.AccountController.viewCard())
  }

  /**
    * this function will remove customer address
    *
    * @param addressID AddressID
    * @return
    */
  def removeAddress(addressID: String) = Action {
    implicit request =>
      //      Address.removeAddress(addressID)

      Redirect(routes.AccountController.viewAddress())
  }

  /**
    * this function will allow the customer to add new address
    *
    * @return
    */
  def addAddress() = Action {

    implicit request =>

      connectToDatabase(CollectionNames.ACCOUNT_COLLECTION, DatabaseNames.ACCOUNT_DATABASE).onComplete {
        case Success(result) =>

          val person = BSONDocument(
            "Email" -> request.session.data("connected")
          )
          val query = BSONDocument(
            "$push" -> BSONDocument(
              "Address" -> BSONDocument(
                "AddressLine1" -> addressFormNew.bindFromRequest().data("addressLine1"),
                "AddressLine2" -> addressFormNew.bindFromRequest().data("addressLine2"),
                "AddressCity" -> addressFormNew.bindFromRequest().data("city"),
                "AddressCounty" -> addressFormNew.bindFromRequest().data("county"),
                "AddressPostcode" -> addressFormNew.bindFromRequest().data("postcode")
              )
            )
          )
          result.update(person, query)
        case Failure(fail) =>
          println("fail")
      }
      Thread.sleep(3000)
      Redirect(routes.AccountController.viewAddress())

  }


}






