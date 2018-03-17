package services

import javax.inject.{Inject, Singleton}

import models.repositories.PostRepository
import models.{Category, Image, Post, PostDetail}

import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global


@Singleton
class PostService @Inject()(
                             imageService: ImageService,
                             categoryService: CategoryService){

  def savePost(post: Post): Future[Int] = {
    PostRepository.save(post)
  }

  def deletePost(id: Long): Future[Int] = {
    PostRepository.delete(id)
  }

  def getPost(id: Long): Future[Option[Post]] = {
    PostRepository.get(id)
  }

  def getPostDetail(id: Long): Future[Option[PostDetail]] = {
    getPost(id).flatMap (post =>
      if (post.isDefined) {
        val postBase = post.get
        val getImageF = imageService.getImage(postBase.titleImageId)
        val getCategoryF = categoryService.getCategory(postBase.categoryId)

        for{
          image <- getImageF
          category <- getCategoryF
        } yield {
          val postDetail = populatePostDetail(postBase, image, category)
          Option(postDetail)
        }
      } else {
        Future { None }
      }
    )
  }

  def listAllPosts: Future[Seq[Post]] = {
    PostRepository.listsAll
  }

  def getNLatestPosts(n: Int): Future[Seq[Post]] = {
    PostRepository.getNLatest(n)
  }

  def populatePostDetail(postBase: Post,
                         titleImage: Option[Image],
                         category: Option[Category]): PostDetail = {

    val (categoryId: Long, categoryName: String) =
    if (category.isDefined) {
      (category.get.id, category.get.name)
    } else {
      (0, "")
    }

    val (titleImageId: Long, titleImageSrc: String) =
    if (titleImage.isDefined) {
      (titleImage.get.id, titleImage.get.url)
    } else {
      (0, "")
    }

    PostDetail(
      postBase.id,
      postBase.title,
      "TODO",  // TODO: Get summary
      List(), // TODO: get tags
      postBase.content,
      1, // TODO: get author id
      "Dash", // TODO: get author name
      postBase.publishTime,
      postBase.updateTime,
      postBase.status,
      categoryId,
      categoryName,
      titleImageId,
      titleImageSrc
    )
  }

}
