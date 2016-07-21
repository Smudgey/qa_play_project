package models


import play.api.mvc.QueryStringBindable
import scala.util.matching.Regex.MatchIterator


import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 06/07/2016.
  */


case class Product(pid: Int, name: String, description: String, var stock: Int, var pwareStock: Int, price: Double, clearance: Double, special: Double, category: Category.Value) extends URL with Formatter {


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

object Product extends Formatter {

  var list = new ArrayBuffer[Product]()

  //special price will be a negative int, minus from normal price on front end
  //clearance price will replace normal price if different, will be displayed in clearance section
  def generate(): Unit = {
    list.clear()
    this.add(Product(701, "Rytis Gnome", "Our worst selling gnome, horrible.", 6, 0, 99.99, 99.99, 0, Category.Gnome))
    this.add(Product(702, "Beach Gnome", "This gnome is always ready for the beach, whatever the weather", 87, 58, 11.99, 11.99, 0, Category.Gnome))
    this.add(Product(703, "Supportive Gnome", "His supportive face will support you in your times of need.", 14, 2, 6.99, 6.99, 0, Category.Gnome))
    this.add(Product(704, "Evil Gnome", "Possessed by evil demons, do not let inside your house.", 2, 1, 5.98, 5.98, 0, Category.Gnome))
    this.add(Product(705, "Harry Potter Gnome", "Looks exactly like Daniel Radcliffe", 23, 4, 3.58, 3.58, 0, Category.Gnome))
    this.add(Product(706, "Terminator", "I'll be back!", 23, 4, 9.99, 9.99, 0, Category.Gnome))
    this.add(Product(707, "Dictator Gnome", "Your favorite dictator is now available as a garden gnome!", 11, 11, 6.66, 6.66, 0, Category.Gnome))
    this.add(Product(708, "Donald Gnome", "Amazing hair.", 12, 1, 5.99, 5.99, 0, Category.Gnome))
    this.add(Product(709, "Invisible Gnome", "It's an invisible gnome.", 5, 0, 10.05, 10.05, 0, Category.Gnome))
    this.add(Product(710, "Super Gnome", "Your very own superhero gnome.", 25, 0, 10.99, 10.99, 0, Category.Gnome))
    this.add(Product(711, "Ninja Gnome", "Much stealth, very silent, wow.", 12, 0, 58.99, 58.99, 0, Category.Gnome))
    this.add(Product(712, "Thug Gnome", "Beware of Tyrone the gnome.", 12, 0, 58.99, 58.99, 0, Category.Gnome))
    this.add(Product(713, "Wooden Rake", "A perfect classic feeling wooden rake for all of your raking needs.", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(714, "Metal Rake", "If you need a more rugged versatile rake, then this hardy rake is perfect for you.", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(715, "Hard Spade", "A spade for general use made to last a lifetime of normal wear.", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(716, "Spetsnaz shovel", "If you need to dig the hole and kill your neighbour this is tools for you", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(717, "Nice Spade", "The perfect pink shovel, as fabulous as you are.", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(718, "Nice Rake", "The ideal premium rake for the advanced usser.", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(719, "Pickaxe", "Weather you're using it to break tough ground on a project or tunnelling your way into your local branch this axe will serve you well.", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(720, "Wooden Axe", "Looking for a well made traditional axe? Look no further this classic is perfect for felling, chopping and everything else..", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(721, "Metal Axe", "A versatile axe with a weather proof metal handle for longer lasting use.", 5, 0, 8.99, 8.99, 0, Category.Tool))
    this.add(Product(722, "Fishing gnome", "This little fella ", 5, 0, 8.99, 8.99, 0, Category.ClothingItem))
    this.add(Product(723, "Gnome Trousers", "Does your favourite gnome need a new get up? These trousers will add a whole new level of style and sophistication", 5, 0, 8.99, 8.99, 0, Category.ClothingItem))
    this.add(Product(724, "Gnome Shoes", "These stylish shoes will keep your gnomes base protected as well as keeping them stylish under your favourite tree. ", 5, 0, 8.99, 8.99, 0, Category.ClothingItem))
    this.add(Product(725, "Gnome Hat", "Will keep the rain off your favourite little friends head in all conditions.", 5, 0, 8.99, 8.99, 0, Category.ClothingItem))
    this.add(Product(726, "Gnome Bikini", "From Highgate to Honolulu transform your garden into a tropical paradise with the quintessential two piece. ", 5, 0, 8.99, 8.99, 0, Category.ClothingItem))
    this.add(Product(727, "Classic gnome", "The classic garden gnome, this cheeky little chap will bring character to any garden.", 5, 0, 8.99, 8.99, 0, Category.ClothingItem))
    this.add(Product(728, "Gnome Underwear", "Gnome themed underwear.", 5, 0, 8.99, 8.99, 0, Category.ClothingItem))
    this.add(Product(729, "Fake Plant", "This bright little pot plant adds a touch of the outdoors to the indoors requiring absolutely no effort.", 5, 0, 8.99, 8.99, 0, Category.PlantItem))
    this.add(Product(730, "Fake Bush", "Shrug at real shrubs? Fake it until you make it, this spiral shaped plant will make you look like a garden master.", 5, 0, 8.99, 8.99, 0, Category.PlantItem))
    this.add(Product(731, "Fake Tree", "No waiting time to grow required this large artificial tree can instantly be used to shelter under.", 5, 0, 8.99, 8.99, 0, Category.PlantItem))
    this.add(Product(732, "Fake Forest", "A package of fake trees to give you land the desired theme", 5, 0, 8.99, 8.99, 0, Category.PlantItem))
    this.add(Product(733, "Bird House", "A large bird house to accommodate a whole flock if need be", 5, 0, 8.99, 8.99, 0, Category.BirdItem))
    this.add(Product(734, "Bird Feeder", "The classic bird feeder can accommodate up to four birds at a time.", 5, 0, 8.99, 8.99, 0, Category.BirdItem))
    this.add(Product(735, "Plant Pot", "A clay plant pot with a small hole in the  base to stop your plants from drowning when it rains.", 5, 0, 8.99, 8.99, 0, Category.PlantPot))
    this.add(Product(736, "Tree Pot", "A tree trunk themed pot to grow your plants in.", 5, 0, 8.99, 8.99, 0, Category.PlantPot))
    this.add(Product(737, "Nice Gazebo", "A wicker framed gazebo for the fanciest of fancy garden parties, or when it rains on your parade.", 5, 0, 8.99, 8.99, 0, Category.Furniture))
    this.add(Product(738, "Big Gazebo", "Gigantic gazebo you could use as a hangar if you really wanted. More than enough room to swing a cat.", 5, 0, 8.99, 8.99, 0, Category.Furniture))
    this.add(Product(739, "Mini Gazebo", "A gazebo for ants.", 5, 0, 8.99, 8.99, 0, Category.Furniture))
    this.add(Product(740, "Deck Chair", "Sit on a flag", 5, 0, 8.99, 8.99, 0, Category.Furniture))
    this.add(Product(741, "Table", "An outdoor table perched ontop of a hippo", 5, 0, 8.99, 8.99, 0, Category.Furniture))
    this.add(Product(742, "Normal Chair", "An average chair with a fancy cover", 5, 0, 8.99, 6.0, 0, Category.Furniture))

    Product.findProduct(701).get.urlList += "https://thumbs.dreamstime.com/x/ugly-gnome-15606748.jpg"
    Product.findProduct(702).get.urlList += "http://www.gardengnomesetc.com/images/products/Snerdley_Shell_Seeking_Gnomes.jpg"
    Product.findProduct(703).get.urlList += "http://cf.ltkcdn.net/garden/images/std/109913-277x425-History_gnomes.jpg"
    Product.findProduct(704).get.urlList += "http://cdn.thisiswhyimbroke.com/images/military-lawn-gnomes.jpg"
    Product.findProduct(705).get.urlList += "https://img1.etsystatic.com/040/0/9374951/il_570xN.638630311_1f17.jpg"
    Product.findProduct(706).get.urlList += "http://ep.yimg.com/ay/gadgetbargains/gnominator-gnome-4.jpg"
    Product.findProduct(707).get.urlList += "http://farm3.staticflickr.com/2833/9495989170_6d9cd470c6.jpg"
    Product.findProduct(708).get.urlList += "http://i.ebayimg.com/00/s/MTYwMFgxMDcw/z/XUQAAOSwnLdWslS-/$_1.JPG"
    Product.findProduct(709).get.urlList += "http://cdn.miniaturemarket.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/d/d/ddee-002i.jpg"
    Product.findProduct(710).get.urlList += "https://s-media-cache-ak0.pinimg.com/564x/85/ba/f3/85baf35b3dab24c2c01627f3528eff53.jpg"
    Product.findProduct(711).get.urlList += "http://www.menkind.co.uk/media/catalog/product/cache/1/image/800x/9df78eab33525d08d6e5fb8d27136e95/n/i/ninja-garden-gnome-24b.jpg"
    Product.findProduct(712).get.urlList += "http://i.imgur.com/jgEhW7H.jpg?fb"
    Product.findProduct(713).get.urlList += "http://d1qjc4s8f3m2zr.cloudfront.net/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/r/u/rud01-hay-rake-800-6.jpg"
    Product.findProduct(714).get.urlList += "http://bulldoghandtools.co.uk/media/catalog/product/g/a/garden_rake_cranked_54_12_teeth_tubular_metal_handle.jpg"
    Product.findProduct(715).get.urlList += "http://www.toolstop.co.uk/components/com_virtuemart/shop_image/product/GL50.jpg"
    Product.findProduct(716).get.urlList += "https://images-na.ssl-images-amazon.com/images/I/51QE4ttqtdL._SL1000_.jpg"
    Product.findProduct(717).get.urlList += "http://nt.greenfingers.com/images/product_images/extra_images/LS9530D/10_Square_Mouth_Shovel.jpg"
    Product.findProduct(718).get.urlList += "https://res-2.cloudinary.com/ezvid-inc/image/upload/c_pad,f_auto,h_210,w_322,q_auto/sopjh606m7snale0jtxv"
    Product.findProduct(719).get.urlList += "http://thumbs2.ebaystatic.com/d/l225/m/m79fDnft9EjVGjveIgyZa1g.jpg"
    Product.findProduct(720).get.urlList += "http://www.robin-wood.co.uk/wp-content/uploads/2010/07/gransfors-bruks-wildlife-hatchet-73-p.jpg"
    Product.findProduct(721).get.urlList += "http://airtoolguy.com/wp-content/uploads/2016/02/21QtK7L02lL.jpg"
    Product.findProduct(722).get.urlList += "https://s-media-cache-ak0.pinimg.com/236x/98/31/88/983188a8982e2640012890eb6d799f1b.jpg"
    Product.findProduct(723).get.urlList += "http://www.caramel-shop.co.uk/media/catalog/product/cache/1/small_image/341x479/9df78eab33525d08d6e5fb8d27136e95/c/a/caramel_ss16_aniseedbabytrouser_brownstar_s16bs_01.jpg"
    Product.findProduct(724).get.urlList += "http://www.zappos.com/images/z/1/4/0/5/9/4/1405942-3-4x.jpg"
    Product.findProduct(725).get.urlList += "http://thumbs1.ebaystatic.com/d/l225/m/mPor0W20U3gvRjDxJ9_Ioeg.jpg"
    Product.findProduct(726).get.urlList += "http://i.ebayimg.com/images/g/K7wAAOxy0bRTDAqM/s-l300.jpg"
    Product.findProduct(727).get.urlList += "http://s4.thisnext.com/media/largest_dimension/DB556802.jpg"
    Product.findProduct(728).get.urlList += "https://image.spreadshirtmedia.net/image-server/v1/products/22223847/views/2,width=378,height=378,appearanceId=1,version=1450089781/Sweet-little-garden-gnome-Underwear.png"
    Product.findProduct(729).get.urlList += "http://www.ikea.com/ms/en_GB/img/site_images/seo/artificial_plants_fejka_seo.jpg"

    Product.findProduct(730).get.urlList += "https://images-na.ssl-images-amazon.com/images/I/31CnsUm9NtL.jpg"
    Product.findProduct(731).get.urlList += "https://www.plantart.co.uk/articles_gallery/images/artificial_trees.jpg"
    Product.findProduct(732).get.urlList += "https://knoji.com/images/user/forest1.JPG"
    Product.findProduct(733).get.urlList += "http://www.yardenvy.com/images/pz/23766/decorative-purple-martin-club-house-birdhouse-HB-2048L.jpg"
    Product.findProduct(734).get.urlList += "http://shopping.rspb.org.uk/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/R/4/R401636_2.jpg"
    Product.findProduct(735).get.urlList += "http://homebase.scene7.com/is/image/homebase/157670_R_Z001?$LISTER$&wid=420&hei=420"
    Product.findProduct(736).get.urlList += "http://st.hzcdn.com/simgs/dbc1a5780f60badc_4-0262/contemporary-indoor-pots-and-planters.jpg"
    Product.findProduct(737).get.urlList += "http://www.mastersoutdoorleisure.co.uk/wp-content/uploads/2015/08/Masters-Regent-Gazebo.jpg"
    Product.findProduct(738).get.urlList += "http://gazeboideas.xyz/wp-content/uploads/2016/04/party-gazebo-party-gazebo-wedding-tents-for-sale-big-gazebos-for-sale-big-gazebos-for-sale-1.jpg"
    Product.findProduct(739).get.urlList += "http://homened.com/wp-content/uploads/2015/11/inexpensive-small-outdoor-garden-curved-gable-roof-gazebo-ideas-with-solid-wood-framing-over-minimalist-black-grill-set-including-extendable-side-desks-as-well-as-patio-canopy-and-outdoor-living.jpg"

    Product.findProduct(740).get.urlList += "http://imageshotfroguk.blob.core.windows.net/companies/Deckchairs-Online-UK/images/Deckchairs-Online-UK_218175_image.jpg"
    Product.findProduct(741).get.urlList += "http://www.homebasics.net/wp-content/uploads/2012/05/Hippo-Table-Design1.jpg"
    Product.findProduct(742).get.urlList += "http://www.thorns.co.uk/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/P/l/Plastic-Patio-Chair---e114.jpg"

    Product.markExistingProductAsClearance(701, 2.50, 4)

  }

  def markExistingProductAsClearance(pid: Int, newPrice: Double, quantity: Int): Unit = {

    ClearanceProduct(pid, priceFormat(newPrice), quantity)
    Product.findProduct(pid).get.decrementStock(quantity, 0)
  }

  def markAsClearance(pid: Int, newPrice: Double, quantity: Int): Unit = {

    /**Doesnt take into account porousware stock like the other methods, would need to implement when adding this functionality
      */
    Product.findProduct(pid).get.decrementStock(quantity, 0)
    ClearanceProduct(pid, newPrice, quantity)
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
