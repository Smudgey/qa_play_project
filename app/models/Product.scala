package models


import play.api.mvc.QueryStringBindable
import scala.util.matching.Regex.MatchIterator


import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 06/07/2016.
  */

case class Product(pid: Int, name: String, description: String, var stock: Int, var pwareStock: Int, price: Double, clearance: Double, special: Double, category: Category.Value) {


  def decrementStock(quantity: Int, pwareQuantity: Int): Unit = {
    //Add stock validation here?
    stock -= quantity
    pwareStock -= pwareQuantity
  }

  def incrementStock(quantity: Int, pwareQuantity: Int): Unit = {
    //Add stock validation here?
    stock += quantity
    pwareStock += pwareQuantity
  }

  def hasXAvailable(x: Int): Boolean ={
   this.stock >= x
  }
}

object Product {

  var list = new ArrayBuffer[Product]()

  //special price will be a negative int, minus from normal price on front end
  //clearance price will replace normal price if different, will be displayed in clearance section
  def generate(): Unit = {
    list.clear()

    this.add(Product(701, "Rytis Gnome", "Our worst selling gnome, horrible.", 6, 0, 99.99, 99.99, 0, Category.Gnome))
    this.add(Product(702, "Sexy Gnome", "This is a very sexy gnome dressed in a bikini, NSFW.", 87, 58, 11.99, 11.99, 0, Category.Gnome))
    this.add(Product(703, "Supportive Gnome", "His supportive face will support you in your times of need.", 14, 2, 6.99, 6.99, 0, Category.Gnome))
    this.add(Product(704, "Evil Gnome", "Possessed by evil demons, do not let inside your house.", 2, 1, 5.98, 5.98, 0, Category.Gnome))
    this.add(Product(705, "Harry Potter Gnome", "Looks exactly like Daniel Radcliffe", 23, 4, 3.58, 3.58, 0, Category.Gnome))
    this.add(Product(706, "Arnie Gnome", "GET TO DEE CHOPPAAA", 23, 4, 9.99, 9.99, 0, Category.Gnome))
    this.add(Product(707, "Kim Gnome", "Your favorite dictator is now available as a garden gnome!", 11, 11, 6.66, 6.66, 0, Category.Gnome))
    this.add(Product(708, "Donald Gnome", "Amazing hair.", 12, 1, 5.99, 5.99, 0, Category.Gnome))
    this.add(Product(709, "Invisible Gnome", "It's an invisible gnome.", 5, 0, 10.05, 10.05, 0, Category.Gnome))
    this.add(Product(710, "Super Gnome", "Your very own superhero gnome.", 25, 0, 10.99, 10.99, 0, Category.Gnome))
    this.add(Product(711, "Ninja Gnome", "Much stealth, very silent, wow.", 12, 0, 58.99, 58.99, 0, Category.Gnome))
    this.add(Product(712, "Thug Gnome", "Beware of Tyrone the gnome.", 12, 0, 58.99, 58.99, 0, Category.Gnome))
    this.add(Product(713, "Wooden Rake", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(714, "Metal Rake", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(715, "Hard Spade", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(716, "Super Spade", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(717, "Nice Spade", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(718, "Nice Rake", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(719, "Pickaxe", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(720, "Wooden Axe", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(721, "Metal Axe", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(722, "Gnome Shirt", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.ClothingItem))
    this.add(Product(723, "Gnome Trousers", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.ClothingItem))
    this.add(Product(724, "Gnome Shoes", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.ClothingItem))
    this.add(Product(725, "Gnome Hat", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.ClothingItem))
    this.add(Product(726, "Gnome Bikini", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.ClothingItem))
    this.add(Product(727, "Gnome Lingerie", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.ClothingItem))
    this.add(Product(728, "Gnome Underwear", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.ClothingItem))
    this.add(Product(729, "Fake Plant", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.PlantItem))
    this.add(Product(730, "Fake Bush", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.PlantItem))
    this.add(Product(731, "Fake Tree", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.PlantItem))
    this.add(Product(732, "Fake Forest", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.PlantItem))
    this.add(Product(733, "Bird House", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.BirdItem))
    this.add(Product(734, "Bird Feeder", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.BirdItem))
    this.add(Product(735, "Plant Pot", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.PlantPot))
    this.add(Product(736, "Tree Pot", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.PlantPot))
    this.add(Product(737, "Nice Gazebo", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.Furniture))
    this.add(Product(738, "Big Gazebo", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.Furniture))
    this.add(Product(739, "Mini Gazebo", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.Furniture))
    this.add(Product(740, "Deck Chair", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.Furniture))
    this.add(Product(741, "Table", "Perfect for raking", 5, 0, 8.99, 8.99, 0, Category.Furniture))
    this.add(Product(742, "Normal Chair", "Perfect for raking", 5, 0, 8.99, 6.0, 0, Category.Furniture))

  }

  def searchByName(query: String) =  list.filter(_.name.toLowerCase.contains(query.toLowerCase()))

  def searchByCategory(query: String) = list.filter(_.description.toLowerCase.contains(query.toLowerCase()))

  def searchByPrice(price: Int) = list.filter(_.price <= price)

  def searchByCat(categoryAttributes: Category.Value) = list.filter(_.category == categoryAttributes)

  def findProduct(pid: Int) = list.find(_.pid == pid)

  def add(prod: Product): Unit = {
    list += prod
  }
}
