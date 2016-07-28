package models

import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONObjectID}

/**
  * Created by Administrator on 28/07/2016.
  */
case class Person(id: BSONObjectID, fName: String, lName: String, age: Int) {}

object Person {

  implicit object PersonReader extends BSONDocumentReader[Person] {
    def read(doc: BSONDocument): Person =
      Person(doc.getAs[BSONObjectID]("_id").get,
        doc.getAs[String]("fName").get,
        doc.getAs[String]("lName").get,
        doc.getAs[Int]("age").get)
  }

  implicit object PersonWriter extends BSONDocumentWriter[Person] {
    def write(person: Person): BSONDocument =
      BSONDocument("lName" -> person.lName,
        "fName" -> person.fName,
        "age" -> person.age)
  }

}
