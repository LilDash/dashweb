package services

import javax.inject.{Inject, Singleton}

import models.repositories.PostRepository
import models.{Category, Image, Post, PostDetail}

import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.matching.Regex


@Singleton
class PostService @Inject()(
                             imageService: ImageService,
                             categoryService: CategoryService){

  val filterHtmlRegx = "&lt;((?!&gt;).)*&gt;".r
  val summaryLength = 100

  def savePost(post: Post): Future[Int] = {
    PostRepository.save(post)
  }

  def softDeletePost(id: Long): Future[Int] = {
    PostRepository.softDelete(id)
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
        getPostDetail(post.get).map { postDetail =>
          Option(postDetail)
        }
      } else {
        Future { None }
      }
    )
  }

  def getPostDetail(postBase: Post): Future[PostDetail] = {
    val getImageF = imageService.getImage(postBase.titleImageId)
    val getCategoryF = categoryService.getCategory(postBase.categoryId)

    for{
      image <- getImageF
      category <- getCategoryF
    } yield {
      populatePostDetail(postBase, image, category)
    }
  }

//  def listAllPosts: Future[Seq[Post]] = {
//    PostRepository.listsAll
//  }

  def getNLatestPosts(n: Int): Future[Seq[PostDetail]] = {
    PostRepository.getNLatest(n).flatMap { posts: Seq[Post] =>
      val futures = posts.map(p => getPostDetail(p))
      Future.sequence(futures)
    }
  }

  def getPosts(page: Int, numPerPage: Int): Future[Seq[PostDetail]] = {
    val offset = page * numPerPage
    PostRepository.getAllActive(offset, numPerPage).flatMap { posts: Seq[Post] =>
      val future = posts.map(p => getPostDetail(p))
      Future.sequence((future))
    }
  }

  def listAllPosts(page: Int, numPerPage: Int): Future[Seq[PostDetail]] = {
    val offset = page * numPerPage
    PostRepository.getAll(offset, numPerPage).flatMap { posts: Seq[Post] =>
      val future = posts.map(p => getPostDetail(p))
      Future.sequence((future))
    }
  }

  def getPostsByCategory(
                          categoryId: Long,
                          page: Int,
                          numPerPage: Int,
                          includeInactive: Boolean = false): Future[Seq[PostDetail]] = {
    val offset = page * numPerPage
    PostRepository.getByCategoryId(categoryId, offset, numPerPage, includeInactive).flatMap { posts: Seq[Post] =>
      val future = posts.map(p => getPostDetail(p))
      Future.sequence((future))
    }
  }

  def populatePostDetail(postBase: Post,
                         titleImage: Option[Image],
                         category: Option[Category]): PostDetail = {

    val summary = getContentSummary(postBase.content)

    val (categoryId: Long, categoryName: String) =
    if (category.isDefined) {
      (category.get.id, category.get.name)
    } else {
      (0L, "aa")
    }

    val (titleImageId: Long, titleImageSrc: String) =
    if (titleImage.isDefined) {
      (titleImage.get.id, titleImage.get.url)
    } else {
      (0L, "")
    }

    PostDetail(
      postBase.id,
      postBase.title,
      summary,
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

  def getContentSummary(content: String): String = {
    val filtered = filterHtmlRegx.replaceAllIn(content, "")
    filtered.substring(0, Math.min(filtered.length, summaryLength))
  }

}
