package models

import scala.collection.mutable.Queue

/**
  * Created by emma on 12/07/2016.
  */
case class EnquiryDescription(name:String, email:String, enquiry:String){
  var dealtWith: Boolean = false
}
object Enquiry {
  var enquiries = Queue[EnquiryDescription](new EnquiryDescription("emma", "e.upton@qa.com", "How many gnomes do you sell?"))

  def createNewEnquiry(name:String, email:String, enquiry:String): Unit ={
    val tempDesc = new EnquiryDescription(name, email, enquiry)
    enquiries+= tempDesc
    println("New enquiry created with the following details: ")
    print(name)
    print(email)
    print(enquiry)
  }
}
