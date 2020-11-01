package nova

import io.grpc.Status
import io.grpc.nova.user._
import scala.math._
import scala.io.Source
import scalapb.json4s.JsonFormat
import scalapb.zio_grpc.ServerMain
import scalapb.zio_grpc.ServiceList
import zio.{ZEnv, UIO}
import zio.console._
import zio.clock.Clock
import zio.clock._
import zio.stream.ZStream

object Main extends ServerMain {
  override def port: Int = 8980

  val database = JsonFormat.fromJsonString[UserDatabase](
    Source.fromResource("user_db.json").mkString
  )

  val userService = UIO.succeed(UserService(database.results))

  def services: ServiceList[zio.ZEnv] = ServiceList.addM(userService)
}
