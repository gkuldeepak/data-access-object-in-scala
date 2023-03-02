package com.knoldus.repoTest

import com.knoldus.models.{Book, BookType}
import com.knoldus.repo.BookRepository
import org.scalatest.flatspec.AsyncFlatSpec

import java.util.UUID

class BookRepositoryIntegrationTest extends AsyncFlatSpec {

  val bookRepository = new BookRepository

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

  "AddBook " should "Add book " in {
    val result = bookRepository.AddBook(book1)
    assert(result.nonEmpty)
  }

  "DeleteBook " should "remove book " in {
    val id = bookRepository.AddBook(book3)
    val result = bookRepository.DeleteBook(id)
    assert(result)
  }

  "UpdateBookAndPublisherName" should "update info " in {
    val id = bookRepository.AddBook(book3)
    val result = bookRepository.UpdateBookAndPublisherName(
      id,
      "Wings Of Fire",
      "APJ Abdul Kalam"
    )
    assert(result)
  }

  "GetAllBook " should "return a List " in {
    bookRepository.AddBook(book1)
    bookRepository.AddBook(book2)
    bookRepository.AddBook(book3)
    val result = bookRepository.GetAllBook()
    assert(result.nonEmpty)
  }

  "GetBookById " should "return a book name " in {
    val id = bookRepository.AddBook(book1)
    val result = bookRepository.GetBookById(id)
    assert(result == "Harry Potter Part 1")
  }

  "GetBookByPublisher " should "return a List " in {
    bookRepository.AddBook(book1)
    bookRepository.AddBook(book2)
    bookRepository.AddBook(book3)
    val result = bookRepository.GetBookByPublisher("J. K. Rowling")
    assert(result.nonEmpty)
  }

  "GetBookByType " should "return a List " in {
    bookRepository.AddBook(book1)
    bookRepository.AddBook(book2)
    bookRepository.AddBook(book3)
    val result = bookRepository.GetBookByType(BookType.Action_and_Adventure)
    assert(result.nonEmpty)
  }

  it should "return a Empty List " in {
    bookRepository.AddBook(book1)
    bookRepository.AddBook(book2)
    bookRepository.AddBook(book3)
    val result = bookRepository.GetBookByType(BookType.Comic_Book)
    assert(result.isEmpty)
  }
}
