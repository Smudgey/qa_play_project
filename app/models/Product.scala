package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 06/07/2016.
  */


case class Product(pid: Int, name: String, description: String, var stock: Int, var pwareStock: Int, var price: Double, var category: Array[Category.Value]) extends URL {


// URL: String
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

object Product extends Formatter{

  var inventory       = ArrayBuffer[Product]()
  var clearanceStock  = ArrayBuffer[Product]()

  //TODO remove calls to this method and replace with database connection
  def dummyConnection(): Unit = {
    inventory.clear()
    this.add(inventory, Product(701, "Rytis Gnome", "Our worst selling gnome, horrible.", 6, 0, 99.99, Array[Category.Value](Category.GnomeBig)))
    this.add(inventory, Product(702, "Beach Gnome", "This gnome is always ready for the beach, whatever the weather", 87, 58, 11.99, Array[Category.Value](Category.GnomeSmall)))
    this.add(inventory, Product(703, "Supportive Gnome", "His supportive face will support you in your times of need.", 14, 2, 6.99, Array[Category.Value](Category.GnomeBig)))
    this.add(inventory, Product(704, "Evil Gnome", "Possessed by evil demons, do not let inside your house.", 2, 1, 5.98, Array[Category.Value](Category.GnomeMedium)))
    this.add(inventory, Product(705, "Harry Potter Gnome", "Looks exactly like Daniel Radcliffe", 23, 4, 3.58, Array[Category.Value](Category.GnomeMedium)))
    this.add(inventory, Product(706, "Terminator", "I'll be back!", 23, 4, 9.99, Array[Category.Value](Category.GnomeBig)))
    this.add(inventory, Product(707, "Dictator Gnome", "Your favorite dictator is now available as a garden gnome!", 11, 11, 6.66, Array[Category.Value](Category.GnomeMedium)))
    this.add(inventory, Product(708, "Donald Gnome", "Amazing hair.", 12, 1, 5.99, Array[Category.Value](Category.GnomeMedium)))
    this.add(inventory, Product(709, "Invisible Gnome", "It's an invisible gnome.", 5, 0, 10.05, Array[Category.Value](Category.GnomeSmall)))
    this.add(inventory, Product(710, "Super Gnome", "Your very own superhero gnome.", 25, 0, 10.99, Array[Category.Value](Category.GnomeBig)))
    this.add(inventory, Product(711, "Ninja Gnome", "Much stealth, very silent, wow.", 12, 0, 58.99,  Array[Category.Value](Category.GnomeSmall)))
    this.add(inventory, Product(712, "Thug Gnome", "Beware of Tyrone the gnome.", 12, 0, 58.99,  Array[Category.Value](Category.GnomeMedium)))
    this.add(inventory, Product(713, "Wooden Rake", "A perfect classic feeling wooden rake for all of your raking needs.", 5, 0, 8.99,  Array[Category.Value](Category.ToolRake)))
    this.add(inventory, Product(714, "Metal Rake", "If you need a more rugged versatile rake, then this hardy rake is perfect for you.", 5, 0, 8.99,  Array[Category.Value](Category.ToolRake)))
    this.add(inventory, Product(715, "Hard Spade", "A spade for general use made to last a lifetime of normal wear.", 5, 0, 8.99,  Array[Category.Value](Category.ToolSpade)))
    this.add(inventory, Product(716, "Spetsnaz shovel", "If you need to dig the hole and kill your neighbour this is tools for you", 5, 0, 8.99,  Array[Category.Value](Category.ToolSpade)))
    this.add(inventory, Product(717, "Nice Spade", "The perfect pink shovel, as fabulous as you are.", 5, 0, 8.99,  Array[Category.Value](Category.ToolSpade)))

    this.add(inventory, Product(719, "Pickaxe", "Weather you're using it to break tough ground on a project or tunnelling your way into your local branch this axe will serve you well.", 5, 0, 8.99,  Array[Category.Value](Category.ToolAxe)))
    this.add(inventory, Product(720, "Wooden Axe", "Looking for a well made traditional axe? Look no further this classic is perfect for felling, chopping and everything else..", 5, 0, 8.99,  Array[Category.Value](Category.ToolAxe)))
    this.add(inventory, Product(721, "Metal Axe", "A versatile axe with a weather proof metal handle for longer lasting use.", 5, 0, 8.99,  Array[Category.Value](Category.ToolAxe)))
    this.add(inventory, Product(722, "Fishing gnome", "This little fella ", 5, 0, 8.99,  Array[Category.Value](Category.GnomeSmall)))
    this.add(inventory, Product(723, "Gnome Trousers", "Does your favourite gnome need a  get up? These trousers will add a whole  level of style and sophistication", 5, 0, 8.99,  Array[Category.Value](Category.ClothingTrouser, Category.Clothing)))
    this.add(inventory, Product(724, "Gnome Shoes", "These stylish shoes will keep your gnomes base protected as well as keeping them stylish under your favourite tree. ", 5, 0, 8.99, Array[Category.Value](Category.ClothingShoe, Category.Clothing)))
    this.add(inventory, Product(725, "Gnome Hat", "Will keep the rain off your favourite little friends head in all conditions.", 5, 0, 8.99,  Array[Category.Value]( Category.ClothingHat, Category.Clothing)))
    this.add(inventory, Product(726, "Gnome Bikini", "From Highgate to Honolulu transform your garden into a tropical paradise with the quintessential two piece. ", 5, 0, 8.99,  Array[Category.Value](Category.ClothingUnderwear, Category.Clothing)))
    this.add(inventory, Product(727, "Classic gnome", "The classic garden gnome, this cheeky little chap will bring character to any garden.", 5, 0, 8.99,  Array[Category.Value](Category.GnomeMedium)))
    this.add(inventory, Product(728, "Gnome Underwear", "Gnome themed underwear.", 5, 0, 8.99,  Array[Category.Value](Category.ClothingUnderwear, Category.Clothing)))
    this.add(inventory, Product(729, "Fake Plant", "This bright little pot plant adds a touch of the outdoors to the indoors requiring absolutely no effort.", 5, 0, 8.99,  Array[Category.Value](Category.PlantPotted)))
    this.add(inventory, Product(730, "Fake Bush", "Shrug at real shrubs? Fake it until you make it, this spiral shaped plant will make you look like a garden master.", 5, 0, 8.99,  Array[Category.Value](Category.PlantPotted)))
    this.add(inventory, Product(731, "Fake Tree", "No waiting time to grow required this large artificial tree can instantly be used to shelter under.", 5, 0, 8.99, Array[Category.Value]( Category.PlantTree)))
    this.add(inventory, Product(732, "Fake Forest", "A package of fake trees to give you land the desired theme", 5, 0, 8.99,  Array[Category.Value](Category.PlantTree)))
    this.add(inventory, Product(733, "Bird House", "A large bird house to accommodate a whole flock if need be", 5, 0, 8.99,  Array[Category.Value](Category.BirdHouse)))
    this.add(inventory, Product(734, "Bird Feeder", "The classic bird feeder can accommodate up to four birds at a time.", 5, 0, 8.99,  Array[Category.Value](Category.BirdFeeder)))
    this.add(inventory, Product(735, "Plant Pot", "A clay plant pot with a small hole in the  base to stop your plants from drowning when it rains.", 5, 0, 8.99,  Array[Category.Value](Category.PlantPot)))
    this.add(inventory, Product(736, "Tree Pot", "A tree trunk themed pot to grow your plants in.", 5, 0, 8.99,  Array[Category.Value](Category.PlantPot)))
    this.add(inventory, Product(737, "Nice Gazebo", "A wicker framed gazebo for the fanciest of fancy garden parties, or when it rains on your parade.", 5, 0, 8.99, Array[Category.Value]( Category.FurnitureGazebo)))
    this.add(inventory, Product(738, "Big Gazebo", "Gigantic gazebo you could use as a hangar if you really wanted. More than enough room to swing a cat.", 5, 0, 8.99, Array[Category.Value]( Category.FurnitureGazebo)))
    this.add(inventory, Product(739, "Mini Gazebo", "A gazebo for ants.", 5, 0, 8.99,  Array[Category.Value](Category.FurnitureGazebo)))
    this.add(inventory, Product(740, "Deck Chair", "Sit on a flag", 5, 0, 8.99,  Array[Category.Value](Category.FurnitureChair)))
    this.add(inventory, Product(741, "Table", "An outdoor table perched ontop of a hippo", 5, 0, 8.99,  Array[Category.Value](Category.FurnitureTable)))
    this.add(inventory, Product(742, "Normal Chair", "An average chair with a fancy cover", 5, 0, 8.99, Array[Category.Value]( Category.FurnitureChair)))

    Product.findProduct(701).get.urlList += "/assets/images/ugly-gnome-15606748new.jpg"
    Product.findProduct(702).get.urlList += "/assets/images/Snerdley_Shell_Seeking_Gnomes.png"
    Product.findProduct(703).get.urlList += "/assets/images/109913-277x425-History_gnomes.png"
    Product.findProduct(704).get.urlList += "/assets/images/military-lawn-gnomes.png"
    Product.findProduct(705).get.urlList += "/assets/images/il_570xN.638630311_1f17.jpg"
    Product.findProduct(706).get.urlList += "/assets/images/gnominator-gnome-4.jpg"
    Product.findProduct(707).get.urlList += "/assets/images/9495989170_6d9cd470c6.jpg"
    Product.findProduct(708).get.urlList += "/assets/images/$_1.png"
    Product.findProduct(709).get.urlList += "/assets/images/ddee-002i.jpg"
    Product.findProduct(710).get.urlList += "/assets/images/85baf35b3dab24c2c01627f3528eff53.jpg"

    Product.findProduct(711).get.urlList += "/assets/images/ninja-garden-gnome-24b.jpg"
    Product.findProduct(712).get.urlList += "/assets/images/jgEhW7H.jpg"
    Product.findProduct(713).get.urlList += "/assets/images/rud01-hay-rake-800-6.jpg"
    Product.findProduct(714).get.urlList += "/assets/images/garden_rake_cranked_54_12_teeth_tubular_metal_handle.jpg"
    Product.findProduct(715).get.urlList += "/assets/images/GL50.jpg"
    Product.findProduct(716).get.urlList += "/assets/images/51QE4ttqtdL._SL1000_.jpg"
    Product.findProduct(717).get.urlList += "/assets/images/10_Square_Mouth_Shovel.jpg"

    Product.findProduct(719).get.urlList += "/assets/images/m79fDnft9EjVGjveIgyZa1g.jpg"

    Product.findProduct(720).get.urlList += "/assets/images/gransfors-bruks-wildlife-hatchet-73-p.jpg"
    Product.findProduct(721).get.urlList += "/assets/images/21QtK7L02lL.jpg"
    Product.findProduct(722).get.urlList += "/assets/images/983188a8982e2640012890eb6d799f1b.jpg"
    Product.findProduct(723).get.urlList += "/assets/images/caramel_ss16_aniseedbabytrouser_brownstar_s16bs_01.jpg"
    Product.findProduct(724).get.urlList += "/assets/images/1405942-3-4x.jpg"
    Product.findProduct(725).get.urlList += "/assets/images/mPor0W20U3gvRjDxJ9_Ioeg.png"
    Product.findProduct(726).get.urlList += "/assets/images/s-l300.png"
    Product.findProduct(727).get.urlList += "/assets/images/DB556802.jpg"
    Product.findProduct(728).get.urlList += "/assets/images/Sweet-little-garden-gnome-Underwear.png"
    Product.findProduct(729).get.urlList += "/assets/images/artificial_plants_fejka_seo.png"

    Product.findProduct(730).get.urlList += "/assets/images/31CnsUm9NtL.jpg"
    Product.findProduct(731).get.urlList += "/assets/images/artificial_trees.jpg"
    Product.findProduct(732).get.urlList += "/assets/images/forest1.png"
    Product.findProduct(733).get.urlList += "/assets/images/decorative-purple-martin-club-house-birdhouse-HB-2048L.png"
    Product.findProduct(734).get.urlList += "/assets/images/R401636_2.jpg"
    Product.findProduct(735).get.urlList += "/assets/images/157670_R_Z001.jpg"
    Product.findProduct(736).get.urlList += "/assets/images/contemporary-indoor-pots-and-planters.jpg"
    Product.findProduct(737).get.urlList += "/assets/images/Masters-Regent-Gazebo.jpg"
    Product.findProduct(738).get.urlList += "/assets/images/party-gazebo-party-gazebo-wedding-tents-for-sale-big-gazebos-for-sale-big-gazebos-for-sale-1.jpg"
    Product.findProduct(739).get.urlList += "/assets/images/inexpensive-small-outdoor-garden-curved-gable-roof-gazebo.jpg"

    Product.findProduct(740).get.urlList += "/assets/images/Deckchairs-Online-UK_218175_image.jpg"
    Product.findProduct(741).get.urlList += "/assets/images/Hippo-Table-Design1.jpg"
    Product.findProduct(742).get.urlList += "/assets/images/Plastic-Patio-Chair---e114.jpg"

    Product.markProductAsClearance(701, 2.50, 4)

  }

  def markProductAsClearance(pid: Int, Price: Double, quantity: Int): Unit = {

    val p = findProduct(pid).get.copy(price = priceFormat(Price), stock = quantity)
    p.urlList = findProduct(pid).get.urlList

    add(clearanceStock, p)
    Product.findProduct(pid).get.decrementStock(quantity, 0)
  }

  def sizeOf(l1: ArrayBuffer[Product]): Int = {
    def accumulate(l2: ArrayBuffer[Product], total: Int): Int = {
      if(l2.isEmpty)
        total
      else
        accumulate(l2.tail, total + l2.head.stock)
    }
    accumulate(l1, 0)
  }

  def searchByName(query: String) =  inventory.filter(_.name.toLowerCase.contains(query.toLowerCase()))

  def searchDescription(query: String) = inventory.filter(_.description.toLowerCase.contains(query.toLowerCase()))

  //def searchByPrice(price: Double) = inventory.filter(_.price <= price)

  def searchByPrice(min: Double, max: Double) = inventory.filter(_.price >= min).filter(_.price <= max).sortBy(_.price)

//  def searchByCategory(query: String) = inventory.filter(_.category.toString.toLowerCase.contains(query.toLowerCase()))

  def searchByCategory(query: String) = {

    var a = ArrayBuffer[Product]()
    inventory.foreach( p =>
      p.category.foreach( c =>
        if (c.toString.toLowerCase.contains(query.toLowerCase) && !a.contains(p)) a += p
      )
    )
    a
  }
  /**
    * Returns a list of products with a particular category tag
    *
    */
    def listByCat(cat: Category.Value): ArrayBuffer[Product] = {
    var ret =  ArrayBuffer[Product]()
    for (p <- inventory) {
      if (p.category.contains(cat)) {
        ret += p
      }
    }
    ret
  }


  def findProduct(pid: Int) = inventory.find(_.pid == pid)

  def add(list: ArrayBuffer[Product], prod: Product): Unit = {
    list += prod
  }


}
