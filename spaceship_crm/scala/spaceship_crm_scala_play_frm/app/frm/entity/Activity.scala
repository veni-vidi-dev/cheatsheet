/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package frm.entity

import java.time.LocalDateTime

import play.api.libs.json.Json

import scala.beans.BeanProperty

case class Activity (
  @BeanProperty id: Long,
  @BeanProperty name: String,
  @BeanProperty startDate: LocalDateTime,
  @BeanProperty endDate: LocalDateTime
)

object Activity {
  implicit val userFormat = Json.format[Activity]
}
