/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package frm.table

import frm.entity.UserInfo
import slick.jdbc.PostgresProfile.api._
import UserTable.userQuery

object UserInfoTable {
  lazy val userInfoQuery = TableQuery[UserInfoTable]
}

class UserInfoTable(tag: Tag) extends Table[UserInfo](tag, "user_info") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def address = column[String]("address")
  def phone = column[String]("phone")
  def userId = column[Long]("user_id")

  override def * = (id, address, phone, userId) <> (UserInfo.tupled, UserInfo.unapply)

  def user = foreignKey("user",userId,userQuery)(_.id)

}
