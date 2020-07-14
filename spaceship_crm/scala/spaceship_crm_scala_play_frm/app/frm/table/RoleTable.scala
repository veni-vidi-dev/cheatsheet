/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package frm.table

import frm.entity.Role
import slick.jdbc.PostgresProfile.api._

object RoleTable {
  lazy val roleQuery = TableQuery[RoleTable]
}

class RoleTable(tag: Tag) extends Table[Role](tag, "role")  {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  override def * = (id, name) <> (Role.tupled, Role.unapply)
}
