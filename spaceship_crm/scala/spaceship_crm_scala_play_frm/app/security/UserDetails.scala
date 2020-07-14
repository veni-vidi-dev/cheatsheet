/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package security

import be.objectify.deadbolt.scala.models.{Permission, Role, Subject}
import frm.entity.User

case class UserDetails(private val user: User) extends Subject {

  override def identifier: String = user.username

  override def roles: List[Role] = user.roles.toList

  override def permissions: List[Permission] = List()
}
