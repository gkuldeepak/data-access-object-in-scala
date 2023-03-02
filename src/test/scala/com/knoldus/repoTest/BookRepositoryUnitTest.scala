package com.knoldus.repoTest

import com.knoldus.db.BookTable
import com.knoldus.models.{Book, BookType}
import com.knoldus.repo.BookRepository
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AsyncFlatSpec

import java.util.UUID

class BookRepositoryUnitTest extends AsyncFlatSpec {

  val mockedBookTable: BookTable = mock[BookTable]
  val bookRepository = new BookRepository()

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

  "AddBook" should "add book " in {
    when(mockedBookTable.addBook(book1)) thenReturn Some(UUID.randomUUID())
    when(mockedBookTable.addBook(book2)) thenReturn Some(UUID.randomUUID())
    when(mockedBookTable.addBook(book3)) thenReturn Some(UUID.randomUUID())
    when(mockedBookTable.addBook(book4)) thenReturn Some(UUID.randomUUID())
    val result1 = bookRepository.AddBook(book1)
    val result2 = bookRepository.AddBook(book2)
    val result3 = bookRepository.AddBook(book3)
    val result4 = bookRepository.AddBook(book4)

    assert(result1.nonEmpty)
    assert(result2.nonEmpty)
    assert(result3.nonEmpty)
    assert(result4.nonEmpty)
  }

  "DeleteBook " should "remove book " in {
    when(mockedBookTable.removeBook(book4.id)) thenReturn true
    val result = bookRepository.DeleteBook(book4.id)
    assert(result)
  }

  "UpdateBookAndPublisherName" should "update info " in {
    when(
      mockedBookTable
        .updateBookInfo(book3.id, "Wings Of Fire", "APJ Abdul Kalam")
    ) thenReturn true
    val result = bookRepository.UpdateBookAndPublisherName(
      book3.id,
      "Wings Of Fire",
      "APJ Abdul Kalam"
    )
    assert(result)
  }

  "GetAllBook " should "return a List " in {
    when(mockedBookTable.getBook()) thenReturn List(book1, book2, book3)
    val result = bookRepository.GetAllBook()
    assert(result.nonEmpty)
  }

  "GetBookById " should "return a book name " in {
    when(mockedBookTable.getBookById(book1.id)) thenReturn book1.name
    val result = bookRepository.GetBookById(book1.id)
    assert(result == book1.name)
  }

  "GetBookByPublisher " should "return a List " in {
    when(mockedBookTable.getBookByPublisher("J. K. Rowling")) thenReturn List(
      book1.name,
      book2.name
    )
    val result = bookRepository.GetBookByPublisher("J. K. Rowling")
    assert(result.nonEmpty)
  }

  "GetBookByType " should "return a List " in {
    when(
      mockedBookTable.getBookByBookType(BookType.Action_and_Adventure)
    ) thenReturn List(book1.name, book2.name)
    val result = bookRepository.GetBookByType(BookType.Action_and_Adventure)
    assert(result.nonEmpty)
  }

  it should "return a Empty List " in {
    when(mockedBookTable.getBookByBookType(BookType.Classics)) thenReturn List()
    val result = bookRepository.GetBookByType(BookType.Comic_Book)
    assert(result.isEmpty)
  }
}
