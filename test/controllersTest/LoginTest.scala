package controllersTest

import models.MongoDatabaseConnector
import org.mindrot.jbcrypt.BCrypt
import org.scalatest.{FlatSpec, Matchers, Tag}

/**
  * Created by Administrator on 08/08/2016.
  */
class LoginTest extends FlatSpec with Matchers with MongoDatabaseConnector{

  val userTest = findAccountByEmail("x")


  it should "Validate Email" taggedAs UsernameSuccess in(
    userTest.head.username.equals("x") shouldEqual true
    )

  it should "Fail Email" taggedAs UsernameFail in (
    userTest.head.username.equals("paultan@hotmail.com") shouldEqual false
    )

  it should "Password Matches" taggedAs PasswordSuccess in(
      BCrypt.checkpw("x", userTest.head.password) shouldEqual true
    )

  it should "Password Fail" taggedAs PasswordFail in(
      BCrypt.checkpw("l", userTest.head.password) shouldEqual false
    )
}

object UsernameSuccess extends Tag("test.modelsTest.UsernameSuccess")
object UsernameFail extends Tag("test.modelsTest.UsernameFail")
object PasswordSuccess extends Tag("test.modelsTest.PasswordSuccess")
object PasswordFail extends Tag("test.modelsTest.PasswordFail")

