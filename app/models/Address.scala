package models

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator

/**
  * Created by Administrator on 11/07/2016.
  */
trait Address {
  var addressLine1: String
  var addressLine2: String
  var city: String
  var county: String
  var postCode: String
}
