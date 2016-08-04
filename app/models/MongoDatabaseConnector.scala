package models

import play.api.libs.json.Json
import reactivemongo.api.MongoDriver
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.{BSONDocument, BSONDocumentReader}
import reactivemongo.core.nodeset.Authenticate

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future
import scala.util.{Failure, Success}

import scala.concurrent.{ExecutionContext, Future}


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

    //val accId = findAccountByEmail(email).head.accountID

    //println("acc: "+ accId)

    connectToDatabase(CollectionNames.ORDER_COLLECTION, DatabaseNames.ORDERS_DATABASE).onComplete {
      case Success(result) =>
        println("connected")
        val query = BSONDocument(
          "accountID" -> "108921209"
        )
        val ordersList = result.find(query).cursor[Order_New].collect[List]()
        ordersList.onComplete {
          case Success(orders) =>
            for (ord <- orders) {
              toReturn += ord
            }
          case Failure(t) => println("failed")
        }
      case Failure(fail) =>
        println("failed")
    }

    Thread.sleep(3000)
    println(toReturn.length)
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
            if (!account.isEmpty) {
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

  def findProductByID(itemID: String): Product_New = {
    var returnHere = ArrayBuffer[Product_New]()

    connectToDatabase(CollectionNames.PRODUCT_COLLECTION, DatabaseNames.ORDERS_DATABASE).onComplete {
      case Success(result) =>

        val query = BSONDocument(
          "itemID" -> itemID
        )
        result.find(query).one[Product_New].onComplete {
          case Success(product) =>

            returnHere += Product_New( product.get.itemID,product.get.product, product.get.images, product.get.category, product.get.description, product.get.stock, product.get.price)

          case Failure(fail) =>
            returnHere
        }
      case Failure(fail) =>
        returnHere
    }
    Thread.sleep(500)
    returnHere.head
  }

  def findByCategory(category: String): Array[Product_New] = {
    var categoryBuffer = ArrayBuffer[Product_New]()

    connectToDatabase(CollectionNames.PRODUCT_COLLECTION, DatabaseNames.ORDERS_DATABASE).onComplete {
      case Success(result) =>
        val query = BSONDocument(
          "category" -> category
        )
        result.find(query).one[Product_New].onComplete {
          case Success(product) =>
            categoryBuffer += Product_New(product.get.product, product.get.itemID, product.get.images, product.get.category, product.get.description, product.get.stock, product.get.price)

          case Failure(fail) =>
            categoryBuffer
        }
      case Failure(fail) =>
        categoryBuffer
    }
    Thread.sleep(500)
    categoryBuffer.toArray
  }

  def allProducts(itemName: String): Array[Product_New] = {
    var returnBuffer = ArrayBuffer[Product_New]()

    connectToDatabase(CollectionNames.PRODUCT_COLLECTION, DatabaseNames.ORDERS_DATABASE).onComplete {

      case Success(result) =>


        val query = BSONDocument()

        result.find(query).cursor[Product_New].collect[ArrayBuffer]().onComplete {
          case Success(coll) =>
            returnBuffer = coll
        }
    }

    Thread.sleep(500)
    println(returnBuffer.length)
    returnBuffer.toArray
  }
}
