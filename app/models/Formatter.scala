package models

/**
  * Created by Administrator on 21/07/2016.
  */
trait Formatter {

  def priceFormat(price: Double): Double = {
    BigDecimal(price).setScale(2, BigDecimal.RoundingMode.UP).toDouble
  }

  def decodeUri(str: String): String = {
    str.replace("%20", " ")
  }


}
