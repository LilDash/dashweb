package controllers

import javax.inject._
import play.api.mvc._

class AdminController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    InternalServerError("oops")
  }
}
