package nova

import io.grpc.Status
import io.grpc.nova.user._
import scala.io.Source
import scala.math._
import scalapb.json4s.JsonFormat
import scalapb.zio_grpc.ServerMain
import scalapb.zio_grpc.ServiceList
import zio.{IO, Ref, ZEnv, ZIO}
import zio.stream.ZStream
import zio.console._
import zio.clock.Clock
import zio.clock._
import zio.UIO

case class UserService(
    users: Seq[User]
) extends ZioUser.ZUserService[ZEnv, Any] {

  /**
    * Streams all users contained in the users database.
    *
    * @param empty An empty type that we will ignore.
    */
  def find(empty: Empty): ZStream[ZEnv, Status, User] =
    ZStream.fromIterable(users)

}
