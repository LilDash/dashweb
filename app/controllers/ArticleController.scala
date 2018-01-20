package controllers


import javax.inject._

import common.enums.{LanguageEnum, PageTypeEnum}
import play.api._
import play.api.mvc._
import services.PostService
import viewModels.ArticlePageViewModel
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class ArticleController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index(id: Long) = Action.async { implicit request: Request[AnyContent] =>
    PostService.getPost(id).map { post =>
      if (post.isDefined) {
        val vm = ArticlePageViewModel(PageTypeEnum.Article, LanguageEnum.zh, post.get)
        Ok(views.html.article(vm))
      } else {
        NotFound("Article Not Found")
      }
    }
  }


}
