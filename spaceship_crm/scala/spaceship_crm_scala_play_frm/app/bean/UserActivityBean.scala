/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package bean

import java.time.LocalDateTime

import play.api.libs.json._

import scala.beans.BeanProperty

object UserActivityBean {
  implicit val userActivityBeanFormat = Json.format[UserActivityBean]
  implicit val userActivityBeanReads = Json.reads[UserActivityBean]
  implicit val userActivityBeanWrites = Json.writes[UserActivityBean]
}

case class UserActivityBean 
(
  @BeanProperty var userId: Long,
  @BeanProperty var firstName: String,
  @BeanProperty var lastName: String,
  @BeanProperty var activityName: String,
  @BeanProperty var start: LocalDateTime,
  @BeanProperty var end: LocalDateTime,
  @BeanProperty var added: LocalDateTime
)