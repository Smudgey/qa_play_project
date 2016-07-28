package models

import com.typesafe.config.ConfigFactory
import reactivemongo.api.{FailoverStrategy, MongoDriver}
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.{BSONDocument, BSONDocumentReader}
import reactivemongo.core.nodeset.Authenticate

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

import models.Person

/**
  * Created by Luke on 25/07/2016.
  */
object MongoConnection {
  val dbn = "test"
  val user = "testuser"
  val pass = "testuser"
  val credentials = List(Authenticate(dbn, user, pass))
  val conn = driver.connection(servs, authentications = credentials)
  val coll = db.collection[BSONCollection]("collN")
  val strat = FailoverStrategy()

  def servs: List[String] = List("192.168.1.15:27017")

  def config = ConfigFactory.load()

  def driver = new MongoDriver(Some(config))

  def db = conn.db(dbn, strat)

  //Read data
  def findPerson()
                (implicit ec: ExecutionContext, reader: BSONDocumentReader[Person]) : Future[List[Person]] ={
    val query = BSONDocument("fName" -> BSONDocument("$gt" -> 24))

    Thread.sleep(10000)

    val ppl = coll.find(query).cursor[Person].collect[List]()
    ppl.map {
      people => for (p <- people)
        println(s"found $p")
    }
    ppl
  }

  def callFindPerson(): Unit = findPerson()

  //val findLukeAge: Future[List[Person]] = findPerson(coll)

  //Create new entry
  val doc = BSONDocument("fname" -> "Tom", "lName" -> "Bob", "age" -> 24)

  val futIns: Future[WriteResult] = coll.insert[BSONDocument](doc)
  futIns.onComplete {
    case Failure(e) => throw e
    case Success(writeResult) => println(s"success: $writeResult")
  }

  val end: Future[Unit] = futIns.map {
    case writeResult if writeResult.code contains 11000 =>
      println("Warning 11000: Duplicate")
    case _ => ()
  }
}