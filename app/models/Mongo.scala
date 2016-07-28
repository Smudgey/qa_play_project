package models

import com.typesafe.config.ConfigFactory
import reactivemongo.api.{FailoverStrategy, MongoDriver}
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.{BSONDocument, BSONDocumentReader}
import reactivemongo.core.nodeset.Authenticate

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by rytis on 28/07/16.
  */
object Mongo {

  import reactivemongo.api.{MongoConnection, MongoDriver}

  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Future

  val mongoUri = "mongodb://192.168.1.15/rytis"
  val dbn = "rytis"
  val user = "testuser"
  val pass = "testuser"
  val credentials = List(Authenticate(dbn, user, pass))

  val driver = new MongoDriver

  def servs: List[String] = List("192.168.1.15:27017")

  val conn = driver.connection(servs, authentications = credentials)

  /*
    val database = for {
      uri <- Future.fromTry(MongoConnection.parseURI(mongoUri))
      con = driver.connection(uri)
      dn <- Future(uri.db.get)
      db <- con
    } yield db
  */

  def dbFromConnection(connection: MongoConnection): Future[BSONCollection] =
    connection.database("rytis").
      map(_.collection("account"))


  dbFromConnection(conn).onComplete {
    case Success(result) =>
      val query = BSONDocument(
        "name" -> "rytis"
      )
      println(result)
  }


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

  def main(args: Array[String]): Unit = {
    Mongo
  }

}
