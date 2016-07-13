import models.Product

/**
  * Created by rytis on 12/07/16.
  */

object stuff {

  def main(args: Array[String]) {
    Product.generate()
    val list = Product.list
    val s = "gnome"

    println(Product.searchByName("gnome").isEmpty)
    for (i <- Product.searchByName("gnome")) {
      println(i.name)
    }
  }


}
