package viewModels

import common.enums.LanguageEnum.LanguageEnum
import common.enums.PageTypeEnum.PageTypeEnum

case class HomePageViewModel(val pageType: PageTypeEnum, val language: LanguageEnum)
  extends BaseViewModel