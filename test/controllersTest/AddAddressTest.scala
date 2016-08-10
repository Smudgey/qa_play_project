package controllersTest

import models.{MongoDatabaseConnector}
import org.scalatest.{FlatSpec, Matchers, Tag}
import reactivemongo.bson.BSONDocument
import scala.concurrent.ExecutionContext.Implicits.global

import scala.util.{Failure, Success}

/**
  * Created by Paul on 08/08/2016.
  */
class AddAddressTest extends FlatSpec with Matchers with MongoDatabaseConnector {

  it should "Find User's Address" taggedAs AddAddress in {
    connectToDatabase(CollectionNames.ACCOUNT_COLLECTION, DatabaseNames.ACCOUNT_DATABASE).onComplete {
      case Success(result) =>
        val person = BSONDocument(
          "Email" -> "x"
        )
        val query = BSONDocument(
          "$push" -> BSONDocument(
            "Address" -> BSONDocument(
              "AddressLine1" -> "101",
              "AddressLine2" -> "Street",
              "AddressCity" -> "Salford",
              "AddressCounty" -> "Greater Manchester",
              "AddressPostcode" -> "M50 3JT"
            )
          )
        )
        result.update(person, query)
      case Failure(fail) =>
        println("fail")

    }
    Thread.sleep(500)
    val userAddress = findAccountByEmail("x").head.address
    val address = userAddress.find(_.addressLineOne == "101")


    address.isEmpty shouldEqual false
  }

  it should "Fails when finding non-existent address" taggedAs AddAddressFail in{

    val userAddress = findAccountByEmail("x").head.address
    val address = userAddress.find(_.addressLineOne == "103")


    address.isEmpty shouldEqual true
  }
}

object AddAddress extends Tag("test.models.AddAddress") {}
object AddAddressFail extends Tag("test.models.AddAddressFail"){}