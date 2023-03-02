package com.knoldus.servicesTest

import com.knoldus.models.{Book, BookType}
import com.knoldus.repo.BookRepository
import com.knoldus.services.Operations
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AsyncFlatSpec

import java.util.UUID

class OperationsUnitTest extends AsyncFlatSpec {

  val mockedBookRepository = mock[BookRepository]
  val operations = new Operations(mockedBookRepository)

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

  "addNewBook " should "add a book " in {
    when(mockedBookRepository.AddBook(book1)) thenReturn Some(UUID.randomUUID())
    when(mockedBookRepository.AddBook(book2)) thenReturn Some(UUID.randomUUID())
    when(mockedBookRepository.AddBook(book3)) thenReturn Some(UUID.randomUUID())
    when(mockedBookRepository.AddBook(book4)) thenReturn Some(UUID.randomUUID())
    val result1 = operations.addNewBook(book1)
    val result2 = operations.addNewBook(book2)
    val result3 = operations.addNewBook(book3)
    val result4 = operations.addNewBook(book4)

    assert(result1.nonEmpty)
    assert(result2.nonEmpty)
    assert(result3.nonEmpty)
    assert(result4.nonEmpty)
  }

  "updateBookData " should "update book info " in {
    when(
      mockedBookRepository.UpdateBookAndPublisherName(
        book3.id,
        "Wings Of Fire",
        "APJ Abdul Kalam"
      )
    ) thenReturn true
    val result =
      operations.updateBookData(book3.id, "Wings Of Fire", "APJ Abdul Kalam")
    assert(result)
  }

  "deleteBook " should "delete a book " in {
    when(mockedBookRepository.DeleteBook(book4.id)) thenReturn true
    val result = operations.deleteBook(book4.id)
    assert(result)
  }

  "getBooks " should "return a list " in {
    when(mockedBookRepository.GetAllBook()) thenReturn List(book1, book2, book3)
    val result = operations.getBooks
    assert(result.nonEmpty)
  }

  "getBookById " should "return a book name " in {
    when(mockedBookRepository.GetBookById(book1.id)) thenReturn book1.name
    val result = operations.getBookById(book1.id)
    assert(result == book1.name)
  }

  "getBooksByPublisher " should "return a bookList " in {
    when(
      mockedBookRepository.GetBookByPublisher("J. K. Rowling")
    ) thenReturn List(book1.name, book2.name)
    val result = operations.getBooksByPublisher("J. K. Rowling")
    assert(result.nonEmpty)
  }
  it should "return an empty bookList " in {
    when(mockedBookRepository.GetBookByPublisher("Yash")) thenReturn List()
    val result = operations.getBooksByPublisher("Yash")
    assert(result.isEmpty)
  }

  "getBooksByType " should "return a list " in {
    when(
      mockedBookRepository.GetBookByType(BookType.Action_and_Adventure)
    ) thenReturn List(book1.name, book2.name)
    val result = operations.getBooksByType(BookType.Action_and_Adventure)
    assert(result.nonEmpty)
  }
  it should "return an empty list " in {
    when(
      mockedBookRepository.GetBookByType(BookType.Comic_Book)
    ) thenReturn List()
    val result = operations.getBooksByType(BookType.Comic_Book)
    assert(result.isEmpty)
  }

}
