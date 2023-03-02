package com.knoldus.servicesTest

import com.knoldus.models.{Book, BookType}
import com.knoldus.repo.BookRepository
import com.knoldus.services.Operations
import org.scalatest.flatspec.AsyncFlatSpec

import java.util.UUID

class OperationsIntegrationTest extends AsyncFlatSpec {

  val bookRepository = new BookRepository()
  val operations = new Operations(bookRepository)

  val book1 = Book(
    Some(UUID.randomUUID()),
    "Harry Potter Part 1",
    "J. K. Rowling",
    1700.00,
    BookType.Action_and_Adventure
  )
  val book2 = Book(
    Some(UUID.randomUUID()),
    "Harry Potter Part 2",
    "J. K. Rowling",
    1750.00,
    BookType.Action_and_Adventure
  )
  val book3 = Book(
    Some(UUID.randomUUID()),
    "WOF",
    "KG",
    2700.00,
    BookType.Biography
  )
  val book4 = Book(
    Some(UUID.randomUUID()),
    "MyLife",
    "KG",
    2900.00,
    BookType.Biography
  )

  "addNewBook" should "add book " in {
    val id1 = operations.addNewBook(book1)
    val id2 = operations.addNewBook(book2)
    val id3 = operations.addNewBook(book3)
    val id4 = operations.addNewBook(book4)
    assert(id1.nonEmpty)
    assert(id2.nonEmpty)
    assert(id3.nonEmpty)
    assert(id4.nonEmpty)
  }

  "updateBookData " should "update info " in {
    val result =
      operations.updateBookData(book3.id, "Wings Of Fire", "APJ Abdul Kalam")
    assert(result)
  }

  "deleteBook " should "delete book " in {
    val result = operations.deleteBook(book4.id)
    assert(result)
  }

  "getBooks " should "return List " in {
    val result = operations.getBooks
    assert(result.nonEmpty)
  }

  "getBookById " should "return a book name " in {
    val result = operations.getBookById(book1.id)
    assert(result == book1.name)
  }

  "getBooksByPublisher " should "return a list " in {
    val result = operations.getBooksByPublisher("J. K. Rowling")
    assert(result.nonEmpty)
  }
  it should "return an empty List" in {
    val result = operations.getBooksByPublisher("Yash")
    assert(result.isEmpty)
  }

  "getBooksByType " should "return a list " in {
    val result = operations.getBooksByType(BookType.Action_and_Adventure)
    assert(result.nonEmpty)
  }
  it should "return an empty list " in {
    val result = operations.getBooksByType(BookType.Classics)
    assert(result.isEmpty)
  }

}
