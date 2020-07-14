/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package security

import be.objectify.deadbolt.scala._
import be.objectify.deadbolt.scala.cache._
import javax.inject.{Inject, Singleton}

@Singleton
class CustomHandlerCache @Inject()
(
  private val defaultHandler: CustomDeadboltHandler
) extends HandlerCache {

  // Get the default handler.
  override def apply(): DeadboltHandler = defaultHandler

  // Get a named handler
  override def apply(handlerKey: HandlerKey): DeadboltHandler = defaultHandler
}
