/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package frm.entity

import play.api.libs.json._

import scala.beans.BeanProperty

case class User(
  @BeanProperty id: Long,
  @BeanProperty username: String,
  password: String,
  @BeanProperty firstName: String,
  @BeanProperty lastName: String
) {

  @BeanProperty var userInfo: UserInfo = _

  @BeanProperty var roles: Seq[Role] = Seq()

  @BeanProperty var dreams: Seq[Dream] = Seq()
}

object User {
  implicit val userFormat = Json.format[User]
  implicit val userReads = Json.reads[User]
  implicit val userWrites = Json.writes[User]
}
