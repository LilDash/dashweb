package services

import models.{Post, Posts}
import scala.concurrent.Future

object PostService {

  def addPost(post: Post): Future[String] = {
    Posts.add(post)
  }

  def deletePost(id: Long): Future[Int] = {
    Posts.delete(id)
  }

  def getPost(id: Long): Future[Option[Post]] = {
    Posts.get(id)
  }

  def listAllPosts: Future[Seq[Post]] = {
    Posts.listsAll
  }
}
