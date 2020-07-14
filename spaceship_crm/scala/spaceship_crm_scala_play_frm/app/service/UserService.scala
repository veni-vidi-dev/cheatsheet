/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package service

import frm.entity.User
import javax.inject.{Inject, Singleton}
import repository.{RoleRepository, UserRepository}

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class UserService @Inject() (
                   private val userRepository: UserRepository
                 )(implicit ec: ExecutionContext) {

  def loadUserByUsername(s: String): Future[Option[User]] = {
    userRepository.findByUsername(s)
  }

}
