package modelsTest

import models.MongoDatabaseConnector
import org.scalatest.{FlatSpec, Matchers, Tag}

/**
  * Created by Administrator on 05/08/2016.
  */
class AccountTest extends FlatSpec with Matchers with MongoDatabaseConnector{

  val accountPass = findAccountByEmail("paultan@hotmail.com")

  val accountFail = findAccountByEmail("lukas@hotmail.com")

  it should "Find user" taggedAs FindAccountSuccess in{
    accountPass.isEmpty shouldEqual false
  }

  it should "Account Does not Exist" taggedAs FindAccountFail in{
    accountFail.isEmpty shouldEqual true
  }

}

object FindAccountSuccess extends Tag("test.modelsTest.FindAccountSuccess")

object FindAccountFail extends Tag("test.modelsTest.FindAccountFail")