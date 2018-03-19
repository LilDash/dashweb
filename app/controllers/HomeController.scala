package controllers

import javax.inject._

import common.enums.{LanguageEnum, PageTypeEnum}
import play.api._
import play.api.libs.json.Json
import play.api.mvc._
import services.PostService
import viewModels.HomePageViewModel
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(
                                cc: ControllerComponents,
                                postService: PostService) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action.async { implicit request: Request[AnyContent] =>
    // TODO: Get headline posts
    postService.getNLatestPosts(3).map { posts =>
      val vm = HomePageViewModel(PageTypeEnum.Home, LanguageEnum.zh, posts)
      Ok(views.html.home(vm))
    }
  }
}
