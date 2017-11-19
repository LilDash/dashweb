package controllers

import javax.inject._

import common.enums.{LanguageEnum, PageTypeEnum}
import play.api.mvc._
import viewModels.{AdminHomePageViewModel, HomePageViewModel}

class AdminController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    val vm = AdminHomePageViewModel(PageTypeEnum.AdminHome, LanguageEnum.zh)
    Ok(views.html.adminHome(vm))
  }
}
