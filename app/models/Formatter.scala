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

  val todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime)
  val timeNow = new SimpleDateFormat("hh:mm:ss" ).format(Calendar.getInstance().getTime)

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
  def randomInt : Int  = scala.util.Random.nextInt(2147483647)

}
