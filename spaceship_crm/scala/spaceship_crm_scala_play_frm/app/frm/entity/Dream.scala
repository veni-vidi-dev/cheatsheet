/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package frm.entity

import scala.beans.BeanProperty

case class Dream (
  @BeanProperty id: Long,
  @BeanProperty description: String,
  @BeanProperty userId: Long
)
