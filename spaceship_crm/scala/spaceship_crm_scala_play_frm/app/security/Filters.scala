/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package security

import be.objectify.deadbolt.scala.filters.DeadboltRoutePathFilter
import javax.inject.Inject
import play.api.http.HttpFilters

class Filters @Inject() (
                          deadbolt: DeadboltRoutePathFilter
                        ) extends HttpFilters {

  val filters = Seq(deadbolt)
}
