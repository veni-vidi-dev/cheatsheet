/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package security

import be.objectify.deadbolt.scala.allOfGroup
import be.objectify.deadbolt.scala.filters._
import javax.inject.Inject

class CustomAuthorizedRoutes @Inject() (
                                         filterConstraints: FilterConstraints,
                                         private val defaultHandler: CustomDeadboltHandler
                                       ) extends AuthorizedRoutes {

  override val routes: Seq[AuthorizedRoute] =
    Seq(
      AuthorizedRoute(Get, "/activities", filterConstraints.subjectPresent, handler = Option(defaultHandler)),
      AuthorizedRoute(Any, "/activity/$activityId<[^/]+>/user/$userId<[^/]+>", filterConstraints.restrict(allOfGroup("ROLE_ADMIN")), handler = Option(defaultHandler)),
      AuthorizedRoute(Get, "/allusers", filterConstraints.restrict(allOfGroup("ROLE_ADMIN")), handler = Option(defaultHandler)),
      AuthorizedRoute(Get, "/allactivities", filterConstraints.restrict(allOfGroup("ROLE_ADMIN")), handler = Option(defaultHandler))
    )
}