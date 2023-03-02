package com.knoldus.repo

import com.knoldus.db.BookTable
import com.knoldus.models.{Book, BookDAO, BookType}

import java.util.UUID

class BookRepository() extends BookDAO[Book] {

  private val bookTable = new BookTable

  override def AddBook(book: Book): Option[UUID] = {
    bookTable.addBook(book)
  }

  override def DeleteBook(id: Option[UUID]): Boolean = {
    bookTable.removeBook(id)
  }

  override def UpdateBookAndPublisherName(
      id: Option[UUID],
      name: String,
      publisher: String
  ): Boolean = {
    bookTable.updateBookInfo(id, name, publisher)
  }

  override def GetAllBook(): List[Book] = {
    bookTable.getBook()
  }

  override def GetBookById(id: Option[UUID]): String = {
    bookTable.getBookById(id)
  }

  override def GetBookByPublisher(publisher: String): List[String] = {
    bookTable.getBookByPublisher(publisher)
  }

  override def GetBookByType(bookType: BookType): List[String] = {
    bookTable.getBookByBookType(bookType)
  }
}
