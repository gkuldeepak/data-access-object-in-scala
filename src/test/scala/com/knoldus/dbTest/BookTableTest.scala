package com.knoldus.dbTest

import com.knoldus.db.BookTable
import com.knoldus.models.{Book, BookType}
import org.scalatest.flatspec.AsyncFlatSpec
import java.util.UUID

class BookTableTest extends AsyncFlatSpec {

  val bookTable = new BookTable()

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

  "addBook " should "add book " in {
    val result = bookTable.addBook(book1)
    assert(Some(result).nonEmpty)
  }

  "getBooks" should "return a list " in {
    val result = bookTable.addBook(book1)
    assert(result.nonEmpty)
  }

  "updateBookInfo" should "update details " in {
    val id = bookTable.addBook(book3)
    val result =
      bookTable.updateBookInfo(id, "Wings of Fire", "APJ Abdul Kalam")
    assert(result)
  }

  "removeBook " should "remove a book " in {
    val id = bookTable.addBook(book1)
    val result = bookTable.removeBook(id)
    assert(result)
  }

  "getBookByID" should "return a bookName" in {
    val id = bookTable.addBook(book1)
    val result = bookTable.getBookById(id)
    assert(result == "Harry Potter Part 1")
  }

  "getBookByPublisher " should "return a List" in {
    bookTable.addBook(book1)
    bookTable.addBook(book2)
    bookTable.addBook(book3)
    val result = bookTable.getBookByPublisher("J. K. Rowling")
    assert(result.nonEmpty)
  }

  "getBookByBookType " should "return a list " in {
    bookTable.addBook(book1)
    bookTable.addBook(book2)
    bookTable.addBook(book3)
    val result = bookTable.getBookByBookType(BookType.Action_and_Adventure)
    assert(result.nonEmpty)
  }
}
