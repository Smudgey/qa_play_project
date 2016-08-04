package models

import reactivemongo.bson.{BSONDocument, BSONDocumentReader}

/**
  * Created by Administrator on 03/08/2016.
  */
case class Product_New(itemID: String, product: String, images: Array[String], category: Array[String], description: String, stock: Int, price: Double){}


object Product_New {
  implicit object productReader extends BSONDocumentReader[Product_New] {
    def read(doc: BSONDocument): Product_New =
      Product_New(
        doc.getAs[String]("itemID").get,
        doc.getAs[String]("Product").get,
        doc.getAs[Array[String]]("images").get,
        doc.getAs[Array[String]]("Category").get,
        doc.getAs[String]("Description").get,
        doc.getAs[Int]("Stock").get,
        doc.getAs[Double]("price").get
      )
  }
}
