package models

import com.typesafe.config.ConfigFactory
import reactivemongo.api.{FailoverStrategy, MongoDriver}
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.BSONDocument
import reactivemongo.core.nodeset.Authenticate

import scala.concurrent.ExecutionContext._
import scala.concurrent.Future
import scala.util.{Failure, Success}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Luke on 25/07/2016.
  */
object MongoConnection {
  val dbn = "scalaplay"
  val user = "appaccess"
  val pass = "appaccess"
  val credentials = List(Authenticate(dbn, user, pass))
  val conn = driver.connection(servs, authentications = credentials)
  val coll = db.collection[BSONCollection]("collN")
  val strat = FailoverStrategy()

  def servs: List[String] = List("192.168.1.15:27017")

  def config = ConfigFactory.load()

  def driver = new MongoDriver(Some(config))

  def db = conn.db(dbn, strat)

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