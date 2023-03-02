package com.knoldus.models

import java.util.UUID

case class Book(id : Option[UUID], name : String, publisher : String, price : Double, bookType : BookType)
