scalaVersion := Deps.vScala

resolvers += Resolver.sonatypeRepo("snapshots")

lazy val commonSettings = Seq(
  scalaVersion := Deps.vScala
)

lazy val server = (project in file("server"))
  .aggregate(domain, shared)
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(Deps.grpc, Deps.scalapb, Deps.scalapbJson4s),
    update / aggregate := false,
    Compile / PB.targets := Seq(
      scalapb.gen(grpc = true)          -> (sourceManaged in Compile).value,
      scalapb.zio_grpc.ZioCodeGenerator -> (sourceManaged in Compile).value
    )
  )

lazy val domain = (project in file("domain"))
  .settings(
    commonSettings
    // other settings
  )

lazy val shared = (project in file("shared"))
  .settings(
    commonSettings
    // other settings
  )

run / fork := true
