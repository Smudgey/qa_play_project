package controllersTest

import models.MongoDatabaseConnector
import org.scalatest.{FlatSpec, Matchers, Tag}
import reactivemongo.bson.BSONDocument
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success,Failure}

/**
  * Created by Paul on 08/08/2016.
  */
class AddCreditCardTest extends FlatSpec with Matchers with MongoDatabaseConnector{

it should "Find User's credit card" taggedAs AddingCardDetails in{

  connectToDatabase(CollectionNames.ACCOUNT_COLLECTION,DatabaseNames.ACCOUNT_DATABASE).onComplete{
    case Success(result) =>
      val person = BSONDocument(
        "Email" -> "x"
      )
      val query = BSONDocument(
        "$push" -> BSONDocument(
          "PaymentCards" -> BSONDocument(
            "CardName" -> "MR X X XXXX",
            "CardNumber" -> "XXXX XXXX XXXX XXXX",
            "CardExpiry" -> "10/2020"
          )
        )
      )
      result.update(person,query)
    case Failure(fail) =>
      println("fail")
  }
  Thread.sleep(500)
  val userCard = findAccountByEmail("x").head.paymentCards
  val card = userCard.find(_.name == "MR X X XXXX")

  card.isEmpty shouldEqual false

}

  it should "Fails when finding non-existent card" taggedAs AddingCardDetails in{

    val userCard = findAccountByEmail("x").head.paymentCards
    val card = userCard.find(_.cardNumber == "XXYZ XXYZ XXYZ XXYZ")

    card.isEmpty shouldEqual true

  }

}
object AddingCardDetails extends Tag("test.models.AddingCardDetails")
object AddingCardDetailsFail extends Tag("test.models.AddingCardDetailsFail")