package services

import models.repositories.PostRepository
import models.Post
import play.api.libs.json.{Json, Writes}

import scala.concurrent.Future

object PostService {

  def savePost(post: Post): Future[Int] = {
    PostRepository.save(post)
  }

  def deletePost(id: Long): Future[Int] = {
    PostRepository.delete(id)
  }

  def getPost(id: Long): Future[Option[Post]] = {
    PostRepository.get(id)
  }

  def listAllPosts: Future[Seq[Post]] = {
    PostRepository.listsAll
  }

  def getNLatestPosts(n: Int): Future[Seq[Post]] = {
    PostRepository.getNLatest(n)
  }



}
