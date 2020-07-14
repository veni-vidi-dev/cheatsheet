/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package bean

import play.api.libs.json.Json

case class LoginRequestBean
(
  username: String,
  password: String
)

object LoginRequestBean {
  implicit val loginRequestBeanFormat = Json.format[LoginRequestBean]
}
