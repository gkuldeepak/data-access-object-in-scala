package com.knoldus.services

import com.knoldus.models.{Book, BookDAO, BookType}

import java.util.UUID

class Operations(repository: BookDAO[Book]) {

  def addNewBook(book: Book) = {
    repository.AddBook(book)
  }

  def updateBookData(id: Option[UUID], name: String, publisher: String) = {
    repository.UpdateBookAndPublisherName(id, name, publisher)
  }

  def deleteBook(id: Option[UUID]) = {
    repository.DeleteBook(id)
  }

  def getBooks: List[Book] = {
    repository.GetAllBook()
  }

  def getBookById(id: Option[UUID]) = {
    repository.GetBookById(id)
  }

  def getBooksByPublisher(publisher: String) = {
    repository.GetBookByPublisher(publisher)
  }

  def getBooksByType(bookType: BookType) = {
    repository.GetBookByType(bookType)
  }

}
