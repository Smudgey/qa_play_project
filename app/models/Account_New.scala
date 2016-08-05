package models

import org.mindrot.jbcrypt.BCrypt
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONObjectID}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by rytis on 28/07/16.
  */


/**
  * Offers a(n instantiable [in contrast to the Account_New *object*]) data structure to represent an instance of Account_New. :).
  */
case class Account_New(accountID: String, username: String, password: String, firstName: String, lastName: String, phone: String, address: Array[Address_New], paymentCards: Array[PaymentCards]) {}


/**
  * Account_New class, has a case class that accepts in the appropriate parameters required to
  * create a new account user (i.e accountID, username, etc.)
  */
object Account_New {

  /**
    * The necessary logic to read in from the database an entry of type Account_New from a
    * BSON document. Returns an instance of Account_New.
    *
    * This is using a custom AccountReader object - MongoReactive offers a variety of
    * default BSONDocumentReaders, we're overriding the standard BSONDocumentReader.
    *
    * This is because the Account_New is a custom object, we're not just inputting into
    * our collection standard default values such as a collection of Strings types/objects.
    **/
  implicit object AccountReader extends BSONDocumentReader[Account_New] {
    //inheriting methods pertaining to BSONDocumentReader
    def read(doc: BSONDocument): Account_New =
    Account_New(
      doc.getAs[String]("ID").get,
      /*this is a bit iffy, basically what's being said is if there's a key
      * that has the value of "ID" then wrap it around a Some class, if not
      * then you'll be return with None... look into the Option class it's quite
      * clever. Then you're calling on the Option wrapper (Some and None are sub-classes
      * of Option - basically the purpose of Option is to avoid icky handling of Null values)
       * it's get method *expecting* it to return a value - if it's None effectively
      * the program is liable to crash or throw an error. Look into getOrElse method.
      * Obviously we can kind of safely agree that the collection isn't going to be empty,
      * so this is basically fine, I'm just trying to inform you all :).*/
      doc.getAs[String]("Email").get,
      doc.getAs[String]("Password").get,
      doc.getAs[String]("First Name").get,
      doc.getAs[String]("Last Name").get,
      doc.getAs[String]("Phone").get,
      doc.getAs[Array[Address_New]]("Address").get,
      doc.getAs[Array[PaymentCards]]("PaymentCards").get)
  }

  /**
    * See comments on AccountReader. Very similar.
    */
  implicit object AccountWriter extends BSONDocumentWriter[Account_New] {
    def write(account_New: Account_New): BSONDocument = {
      BSONDocument(
        "ID" -> account_New.accountID,
        "Email" -> account_New.username,
        "Password" -> BCrypt.hashpw(account_New.password, BCrypt.gensalt()),
        "First Name" -> account_New.firstName,
        "Last Name" -> account_New.lastName,
        "Phone" -> account_New.phone,
        "Address" -> account_New.address,
        "PaymentCards" -> account_New.paymentCards
      )
    }
  }

  /**
    * Create method, takes in a personCollection and shoves into the database it
    */
  def create(personCollection: BSONCollection, account: Account_New)(implicit ec: ExecutionContext, writer: BSONDocumentWriter[Account_New]): Future[Unit] = {
    val writeResult = personCollection.insert(account)
    writeResult.map(_ => {
      /*presumably this is just using the writer implicitly to know how to output every key we loop through
      * as a valid entry in a BSONDocument as an Account_New object.... I'll try to improve this description later*/
    })
  }

}
