package models

import org.mindrot.jbcrypt.BCrypt
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONObjectID}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by rytis on 28/07/16.
  */


/**
  * Offers a(n instantiable [in contrast to the Account *object*]) data structure to represent an instance of Account. :).
  */
case class Account(accountID: String, username: String, password: String, firstName: String, lastName: String, phone: String, address: Array[Address], paymentCards: Array[PaymentCards]) {}


/**
  * Account class, has a case class that accepts in the appropriate parameters required to
  * create a new account user (i.e accountID, username, etc.)
  */
object Account {

  /**
    * The necessary logic to read in from the database an entry of type Account from a
    * BSON document. Returns an instance of Account.
    *
    * This is using a custom AccountReader object - MongoReactive offers a variety of
    * default BSONDocumentReaders, we're overriding the standard BSONDocumentReader.
    *
    * This is because the Account is a custom object, we're not just inputting into
    * our collection standard default values such as a collection of Strings types/objects.
    **/
  implicit object AccountReader extends BSONDocumentReader[Account] {
    //inheriting methods pertaining to BSONDocumentReader
    def read(doc: BSONDocument): Account =
    Account(
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
      doc.getAs[Array[Address]]("Address").get,
      doc.getAs[Array[PaymentCards]]("PaymentCards").get)
  }

  /**
    * See comments on AccountReader. Very similar.
    */
  implicit object AccountWriter extends BSONDocumentWriter[Account] {
    def write(Account: Account): BSONDocument = {
      BSONDocument(
        "ID" -> Account.accountID,
        "Email" -> Account.username,
        "Password" -> BCrypt.hashpw(Account.password, BCrypt.gensalt()),
        "First Name" -> Account.firstName,
        "Last Name" -> Account.lastName,
        "Phone" -> Account.phone,
        "Address" -> Account.address,
        "PaymentCards" -> Account.paymentCards
      )
    }
  }

  /**
    * Create method, takes in a personCollection and shoves into the database it
    */
  def create(personCollection: BSONCollection, account: Account)(implicit ec: ExecutionContext, writer: BSONDocumentWriter[Account]): Future[Unit] = {
    val writeResult = personCollection.insert(account)
    writeResult.map(_ => {
      /*presumably this is just using the writer implicitly to know how to output every key we loop through
      * as a valid entry in a BSONDocument as an Account object.... I'll try to improve this description later*/
    })
  }

}
