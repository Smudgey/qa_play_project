package models

import reactivemongo.api.MongoDriver
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.BSONDocument
import reactivemongo.core.nodeset.Authenticate

import scala.collection.mutable.ArrayBuffer
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

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

  private val driver = new MongoDriver

  def connectToDatabase(collectionName: String, databaseName: String): Future[BSONCollection] = {
    val credentials = List(Authenticate(databaseName, "appaccess", "appaccess"))

    def servs: List[String] = List("192.168.1.15:27017")

    val conn = driver.connection(servs, authentications = credentials)

    conn.database(databaseName).map(_.collection(collectionName))
  }

  def getOrderHistory(email: String): ArrayBuffer[Order_New] = {
    var toReturn = ArrayBuffer[Order_New]()

    val accId = findAccountByEmail(email).head.accountID

    println("acc: "+accId.getClass)

    connectToDatabase(CollectionNames.ORDER_COLLECTION, DatabaseNames.ORDERS_DATABASE).onComplete {
      case Success(result) =>
        println("connected")
        val query = BSONDocument(
          "accountID" -> accId
        )
        val ordersList = result.find(query).cursor[Order_New].collect[List]()
        ordersList.map {
          orders =>
            if(orders.isEmpty){
              println("empty")
            } else {
              for(ord <- orders) {
                println(ord)
              }
            }
          }

      case Failure(fail) =>
        println("failed")
    }

    Thread.sleep(3000)
    toReturn

  }

  def findAccountByEmail(email: String): ArrayBuffer[Account_New] = {
    var toReturn = ArrayBuffer[Account_New]()

    connectToDatabase(CollectionNames.ACCOUNT_COLLECTION, DatabaseNames.ACCOUNT_DATABASE).onComplete {
      case Success(result) =>
        val query = BSONDocument(
          "Email" -> email
        )
        result.find(query).one[Account_New].onComplete {
          case Success(account) =>
            if(!account.isEmpty) {
              toReturn += Account_New(account.get.accountID, account.get.username, account.get.password, account.get.firstName, account.get.lastName, account.get.phone, account.get.address, account.get.paymentCards)
            }

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
