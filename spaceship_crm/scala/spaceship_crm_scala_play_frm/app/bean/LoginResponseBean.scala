/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package bean

import frm.entity.User
import play.api.libs.json.Json

import scala.beans.BeanProperty

case class LoginResponseBean
(
  @BeanProperty var user: User,
  @BeanProperty var token: String
)

object LoginResponseBean {
  implicit val loginResponseBeanFormat = Json.format[LoginResponseBean]
}