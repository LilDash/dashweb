package viewModels

import common.enums.LanguageEnum
import common.enums.LanguageEnum.LanguageEnum
import common.enums.PageTypeEnum.PageTypeEnum

abstract class BaseViewModel {
  def pageType: PageTypeEnum
  def language: LanguageEnum
}
