package viewModels

import common.enums.LanguageEnum.LanguageEnum
import common.enums.PageTypeEnum.PageTypeEnum
import models.Post

case class ArticlePageViewModel(val pageType: PageTypeEnum, val language: LanguageEnum, val article: Post)
  extends BaseViewModel