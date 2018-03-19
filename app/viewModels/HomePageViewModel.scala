package viewModels

import common.enums.LanguageEnum.LanguageEnum
import common.enums.PageTypeEnum.PageTypeEnum
import models.PostDetail

case class HomePageViewModel(pageType: PageTypeEnum, language: LanguageEnum, headlinePosts: Seq[PostDetail])
  extends BaseViewModel