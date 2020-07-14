/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package frm.entity

import scala.beans.BeanProperty

case class Role (
  @BeanProperty id: Long,
  @BeanProperty name: String
) extends be.objectify.deadbolt.scala.models.Role
