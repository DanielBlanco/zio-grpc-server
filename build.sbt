scalaVersion := Deps.vScala

resolvers += Resolver.sonatypeRepo("snapshots")

val grpcVersion = "1.30.1"

lazy val server = (project in file("server"))
  .aggregate(domain, shared)
  .settings(
    libraryDependencies ++= Seq(Deps.grpc, Deps.scalapb, Deps.scalapbJson4s),
    update / aggregate := false,
    Compile / PB.targets := Seq(
      scalapb.gen(grpc = true)          -> (sourceManaged in Compile).value,
      scalapb.zio_grpc.ZioCodeGenerator -> (sourceManaged in Compile).value
    )
  )

lazy val domain = (project in file("domain"))
  .settings(
    // other settings
  )

lazy val shared = (project in file("shared"))
  .settings(
    // other settings
  )

run / fork := true
