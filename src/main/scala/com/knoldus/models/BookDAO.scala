package com.knoldus.models

import java.util.UUID

trait BookDAO[T] {

  def AddBook(t: T): Option[UUID]

  def DeleteBook(id: Option[UUID]): Boolean

  def UpdateBookAndPublisherName(
      id: Option[UUID],
      name: String,
      publisher: String
  ): Boolean

  def GetAllBook(): List[Book]

  def GetBookById(id: Option[UUID]): String

  def GetBookByPublisher(publisher: String): List[String]

  def GetBookByType(bookType: BookType): List[String]

}
