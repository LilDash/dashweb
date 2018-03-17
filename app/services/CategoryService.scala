package services

import javax.inject.{Inject, Singleton}

import models.Category
import models.repositories.CategoryRepository

import scala.concurrent.Future

@Singleton
class CategoryService @Inject()() {
  def getCategory(id: Long): Future[Option[Category]] = {
    CategoryRepository.get(id)
  }

  def listAllCategories: Future[Seq[Category]] = {
    CategoryRepository.listsAll
  }

  def listActiveCategories: Future[Seq[Category]] = {
    CategoryRepository.listsAllActive
  }
}
