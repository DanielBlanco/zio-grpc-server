package nova

import io.grpc.Status
import scalapb.zio_grpc.ServerMain
import scalapb.zio_grpc.ServiceList
import zio.{Ref, ZEnv, ZIO}
import zio.stream.ZStream
import zio.console._

import io.grpc.examples.routeguide.route_guide._
import scala.math._
import zio.IO
import scalapb.json4s.JsonFormat
import scala.io.Source
import zio.clock.Clock
import zio.clock._
import zio.UIO

object Main extends ServerMain {
  override def port: Int = 8980

  val featuresDatabase = JsonFormat.fromJsonString[FeatureDatabase](
    Source.fromResource("route_guide_db.json").mkString
  )

  val createRouteGuide = for {
    routeNotes <- Ref.make(Map.empty[Point, List[RouteNote]])
  } yield new RouteGuideService(featuresDatabase.feature, routeNotes)

  def services: ServiceList[zio.ZEnv] =
    ServiceList.addM(createRouteGuide)
}
