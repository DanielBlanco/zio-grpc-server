import sbt._
import scalapb.compiler.Version.scalapbVersion

/** Lib Versions */
object Deps {
  val vScala         = "2.13.3"
  val vGrpc          = "1.33.1"
  val vZio           = "1.0.3"
  val vScalapb       = scalapbVersion
  val vScalapbJson4s = "0.10.1"

  val grpc          = "io.grpc"               % "grpc-netty"           % vGrpc
  val scalapb       = "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % vScalapb
  val scalapbJson4s =
    "com.thesamet.scalapb" %% "scalapb-json4s" % vScalapbJson4s
}
