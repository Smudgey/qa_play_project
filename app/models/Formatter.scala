package models

import java.text.SimpleDateFormat
import java.util.Calendar

/**
  * Created by Administrator on 21/07/2016.
  */
trait Formatter {

  def priceFormat(price: Double): Double = {
    BigDecimal(price).setScale(2, BigDecimal.RoundingMode.UP).toDouble
  }

  val today = new SimpleDateFormat("hh:mm aa d-M-y").format(Calendar.getInstance().getTime)

  def decodeUri(str: String): String = {
    str.replace("%20", " ")
  }

  def formatCategory(cat: String): String = {
    cat.replaceAll("\\w*\\/","")
  }

  def getCatVal(cat: String): String ={
    cat.replaceAll("\\/", "")
  }

  def pluralise(str: String): String = {

    if(!str.endsWith("s"))
      str + "s"
    else
      str
  }

  def randomID : String = java.util.UUID.randomUUID.toString

}
