package common.enums

object PostStatus extends Enumeration {
  type PostStatus = Value
  val inactive, active, deleted = Value
}