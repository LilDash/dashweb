package viewModels

import common.enums.LanguageEnum.LanguageEnum
import common.enums.PageTypeEnum.PageTypeEnum
import models.{PostDetail}

case class ArticlePageViewModel(val pageType: PageTypeEnum, val language: LanguageEnum, val article: PostDetail)
  extends BaseViewModel