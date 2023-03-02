package com.knoldus.db

import com.knoldus.models.{Book, BookType}

import java.util.UUID
import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}

class BookTable {

  private val dataRepository: collection.mutable.Map[Option[UUID], Book] =
    collection.mutable.Map()

  def addBook(book: Book): Option[UUID] = {
    dataRepository.addOne(book.id -> book)
    book.id
  }

  def getBook(): List[Book] = {
    dataRepository.values.toList
  }

  def updateBookInfo(id: Option[UUID], new_name: String, publisher: String) = {
    val user = Book(
      id,
      new_name,
      publisher,
      dataRepository(id).price,
      dataRepository(id).bookType
    )
    Try {
      dataRepository(id) = user
    } match {
      case Success(value) => true
      case Failure(_)     => false
    }
  }

  def removeBook(id: Option[UUID]) = {
    Try {
      dataRepository -= id
    } match {
      case Success(value) => true
      case Failure(_)     => false
    }
  }

  def getBookById(id: Option[UUID]): String = {
    dataRepository(id).name
  }

  def getBookByPublisher(p_name: String): List[String] = {
    val bookList: ListBuffer[String] = ListBuffer()
    dataRepository.values.foreach(book =>
      if (book.publisher == p_name) {
        bookList.addOne(book.name)
      }
    )
    bookList.toList
  }

  def getBookByBookType(bookType: BookType): List[String] = {
    val bookListByType: ListBuffer[String] = ListBuffer()
    dataRepository.values.foreach(book =>
      if (book.bookType == bookType) {
        bookListByType.addOne(book.name)
      }
    )
    bookListByType.toList
  }

}
