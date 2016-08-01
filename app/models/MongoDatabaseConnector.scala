package models

import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.BSONDocument
import reactivemongo.core.nodeset.Authenticate

import scala.collection.mutable.ArrayBuffer
import scala.util.{Failure, Success}

/**
  * Created by rytis on 28/07/16.
  */
trait MongoDatabaseConnector {

  object DatabaseNames extends Enumeration {
    type DatabaseNames = Value
    val ACCOUNT_DATABASE = "nb_gardens_accounts"
    val ORDERS_DATABASE = "nb_gardens_orders"
  }

  object CollectionNames extends Enumeration {
    type CollectionNames = Value
    val ACCOUNT_COLLECTION = "accounts"
    val ORDER_COLLECTION = "orders"
    val PRODUCT_COLLECTION = "products"

  }

  import reactivemongo.api.MongoDriver

  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Future


  /*
    val database = for {
      uri <- Future.fromTry(MongoConnection.parseURI(mongoUri))
      con = driver.connection(uri)
      dn <- Future(uri.db.get)
      db <- con
    } yield db
  */
  private val driver = new MongoDriver

  def connectToDatabase(collectionName: String, databaseName: String): Future[BSONCollection] = {


    val credentials = List(Authenticate(databaseName, "appaccess", "appaccess"))



    def servs: List[String] = List("192.168.1.15:27017")

    val conn = driver.connection(servs, authentications = credentials)

    /*if (collectionName == CollectionNames.ACCOUNT_COLLECTION && databaseName == DatabaseNames.ACCOUNT_DATABASE ||
      collectionName == CollectionNames.ORDER_COLLECTION && databaseName == DatabaseNames.ORDERS_DATABASE ||
      collectionName == CollectionNames.PRODUCT_COLLECTION && databaseName == DatabaseNames.ORDERS_DATABASE) {*/
    conn.database(databaseName).map(_.collection(collectionName))

  }


  /*connectToDatabase(conn).onComplete {
    case Success(result) =>
      val query = BSONDocument(
        "name" -> "rytis"
      )
      println(result)
  }*/


  /*database.onComplete {
    case Success(result) =>
      val a: BSONCollection = result.collection("account")


    /*def findPerson()(implicit ec: ExecutionContext, reader: BSONDocumentReader[Person]) : Future[List[Person]] ={
      val query = BSONDocument("name" -> "rytis")



      val ppl = a.find(query).cursor[Person].collect[List]()
      ppl.map {
        people => for (p <- people)
          println(s"found $p")
      }
      ppl
    }


    def callFindPerson(): Unit = findPerson()

    callFindPerson()*/

    /*val document1 = BSONDocument("name" -> "Luke")

    def insertDoc1(coll: BSONCollection, doc: BSONDocument): Future[Unit] = {
      val writeRes: Future[WriteResult] = coll.insert(document1)

      writeRes.onComplete { // Dummy callbacks
        case Failure(e) => e.printStackTrace()
        case Success(writeResult) =>
          println(s"successfully inserted document with result: $writeResult")
      }

      writeRes.map(_ => {}) // in this example, do nothing with the success
    }

    insertDoc1(a, document1)*/




    case resolution =>
      println(resolution)
      driver.close()
  }*/


  def findAccountByEmail(email: String): ArrayBuffer[Account_New] = {

    var toReturn = ArrayBuffer[Account_New]()


    connectToDatabase(CollectionNames.ACCOUNT_COLLECTION, DatabaseNames.ACCOUNT_DATABASE).onComplete {
      case Success(result) =>

        val query = BSONDocument(
          "Username" -> email
        )

        result.find(query).one[Account_New].onComplete {

          case Success(account) =>

            toReturn += Account_New(account.get.accountID, account.get.username, account.get.password, account.get.name, account.get.phone, account.get.address, account.get.paymentCards)

          case Failure(fail) =>
            toReturn
        }

      case Failure(fail) =>
        toReturn

    }

    Thread.sleep(500)
    toReturn

  }


}
