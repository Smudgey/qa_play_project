package controllersTest

import models.MongoDatabaseConnector
import org.mindrot.jbcrypt.BCrypt
import org.scalatest.{FlatSpec, Matchers, Tag}

//hashing

/**
  * Created by Administrator on 08/08/2016.
  */
class LoginTest extends FlatSpec with Matchers with MongoDatabaseConnector{

  val userTest = findAccountByEmail("x")


  it should "Password Matches" taggedAs PasswordSuccess in(
      BCrypt.checkpw("x", userTest.head.password) shouldEqual true
    )

  it should "Password Fail" taggedAs PasswordFail in(
      BCrypt.checkpw("l", userTest.head.password) shouldEqual false
    )
}

object PasswordSuccess extends Tag("test.modelsTest.PasswordSuccess")
object PasswordFail extends Tag("test.modelsTest.PasswordFail")

//object LoginSuccess extends Tag("test.modelsTest.LoginSuccess")
//object LoginFail extends Tag("test.modelsTest.LoginFail")
