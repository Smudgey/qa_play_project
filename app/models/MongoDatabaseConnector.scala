package models

import com.google.inject.Singleton
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.{MongoConnection, MongoDriver}
import reactivemongo.bson.BSONDocument
import reactivemongo.core.nodeset.Authenticate

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}


/**
  * Created by rytis on 28/07/16.
  * Last worked on by Mark on 09/08/2016
  */
@Singleton
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

  val driver = new MongoDriver

  def connectToDatabase(collectionName: String, databaseName: String): Future[BSONCollection] = {
    val credentials = List(Authenticate(databaseName, "appaccess", "appaccess"))

    def servs: List[String] = List("192.168.1.15:27017")
    val conn = driver.connection(servs, authentications = credentials)
    conn.database(databaseName).map(_.collection(collectionName))
  }

  def getOrderHistory(email: String): ArrayBuffer[Order] = {
    var toReturn = ArrayBuffer[Order]()

    val accId = findAccountByEmail(email).head.accountID

    //println("acc: "+ accId)

    connectToDatabase(CollectionNames.ORDER_COLLECTION, DatabaseNames.ORDERS_DATABASE).onComplete {
      case Success(result) =>
        println("connected")
        val query = BSONDocument(
          "accountID" -> accId
        )
        val ordersList = result.find(query).cursor[Order].collect[List]()
        ordersList.onComplete {
          case Success(orders) =>
            for (ord <- orders) {
              toReturn += ord
            }
          case Failure(t) => println("failed in query")
        }
      case Failure(fail) =>
        println("failed in connection")
    }

    Thread.sleep(500)

    toReturn

  }

  def findOrder(oid: Int): Order = {
    var toReturn = ArrayBuffer[Order]()

    connectToDatabase(CollectionNames.ORDER_COLLECTION, DatabaseNames.ORDERS_DATABASE).onComplete {
      case Success(result) =>
        val query = BSONDocument("orderID" -> oid)

        result.find(query).one[Order].onComplete {
          case Success(order) =>
            if (order.isDefined)
              toReturn += order.get
          case Failure(fail) =>
            println("not found")
            toReturn
        }
      case Failure(fail) =>
        toReturn
    }
    Thread.sleep(500)
    toReturn.head
  }

  def findAccountByEmail(email: String): ArrayBuffer[Account] = {
    var toReturn = ArrayBuffer[Account]()

    connectToDatabase(CollectionNames.ACCOUNT_COLLECTION, DatabaseNames.ACCOUNT_DATABASE).onComplete {
      case Success(result) =>
        val query = BSONDocument(
          "Email" -> email
        )
        result.find(query).one[Account].onComplete {
          case Success(account) =>
            if (account.isDefined) {
              toReturn += Account(account.get.accountID, account.get.username, account.get.password, account.get.firstName, account.get.lastName, account.get.phone, account.get.address, account.get.paymentCards)
            }

          case Failure(fail) =>
            throw fail
        }

      case Failure(fail) =>
        throw fail
    }

    Thread.sleep(500)
    toReturn
  }

  def findProductByID(itemID: String): Product = {
    var returnHere = ArrayBuffer[Product]()

    connectToDatabase(CollectionNames.PRODUCT_COLLECTION, DatabaseNames.ORDERS_DATABASE).onComplete {
      case Success(result) =>

        val query = BSONDocument(
          "itemID" -> itemID
        )
        result.find(query).one[Product].onComplete {
          case Success(product) =>

            returnHere += Product(product.get.itemID, product.get.product, product.get.images, product.get.category, product.get.description, product.get.stock, product.get.price)

          case Failure(fail) =>
            throw fail
        }
      case Failure(fail) =>
        throw fail
    }
    Thread.sleep(500)
    returnHere.head
  }

  def findByCategory(category: String): Array[Product] = {
    var categoryBuffer = ArrayBuffer[Product]()

    connectToDatabase(CollectionNames.PRODUCT_COLLECTION, DatabaseNames.ORDERS_DATABASE).onComplete {
      case Success(result) =>
        val query = BSONDocument("Category" -> BSONDocument("$regex" -> s"(.*$category).*"))
        result.find(query).cursor[Product].collect[ArrayBuffer]().map {
          case products =>
            categoryBuffer = products
          case _ =>
            None
        }

    }
    Thread.sleep(500)

    categoryBuffer.toArray
  }

  def allProducts(itemName: String): Array[Product] = {
    var returnBuffer = ArrayBuffer[Product]()

    connectToDatabase(CollectionNames.PRODUCT_COLLECTION, DatabaseNames.ORDERS_DATABASE).onComplete {

      case Success(result) =>
        result.find(BSONDocument()).cursor[Product].collect[ArrayBuffer]().onComplete {
          case Success(coll) =>
            returnBuffer = coll
          case Failure(fail) =>
            throw fail
        }
    }
    Thread.sleep(500)
    returnBuffer.toArray
  }

  def findProductsInPriceRance(min: Double, max: Double): Array[Product] = {
    var r = ArrayBuffer[Product]()

    connectToDatabase(CollectionNames.PRODUCT_COLLECTION, DatabaseNames.ORDERS_DATABASE).onComplete {
      case Success(result) =>
        val query = BSONDocument(
          "price" -> BSONDocument(
            "$gte" -> min,
            "$lte" -> max
          )
        )
        result.find(query).cursor[Product].collect[ArrayBuffer]().onComplete {
          case Success(prods) =>
            r = prods
          case Failure(fail) =>
            throw fail
        }
      case Failure(fail) =>
        throw fail
    }
    Thread.sleep(500)
    r.toArray
  }
}

