package controllersTest

import controllers.AccountController
import models.{Address_New, MongoDatabaseConnector}
import org.scalatest.{FlatSpec, Matchers, Tag}

/**
  * Created by Paul on 08/08/2016.
  */
class AddAddressTest extends FlatSpec with Matchers with MongoDatabaseConnector{


  val address = Address_New("1","Street", "Salford", "Greater Manchester", "M50 3JT")

  val accountController = new AccountController

  accountController.addAddress()


}
object AddAddress extends Tag("test.models.AddAddress")