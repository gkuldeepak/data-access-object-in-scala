package com.knoldus.models

sealed trait BookType

object BookType {
  case object Action_and_Adventure extends BookType
  case object Classics extends BookType
  case object Comic_Book extends BookType
  case object Mystery extends BookType
  case object Fantasy extends BookType
  case object Fiction extends BookType
  case object Horror extends BookType
  case object Science_Fiction extends BookType
  case object Biography extends BookType
}
