name := "ml_repository"

version := "1.0"

scalaVersion := "2.11.11"

lazy val hdfsDependencies = {
  val hadoopV = "2.8.0"
  Seq(
    "org.apache.hadoop" % "hadoop-client" % hadoopV,
    "org.apache.hadoop" % "hadoop-hdfs" % hadoopV
  )
}

lazy val akkaDependencies = {
  val akkaV = "2.4.14"
  val akkaHttpV = "10.0.0"
  Seq(
    "com.typesafe.akka" %% "akka-http-core" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-jackson" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-xml" % akkaHttpV,
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "ch.megard" %% "akka-http-cors" % "0.1.10"
  )
}

libraryDependencies ++= akkaDependencies
libraryDependencies ++= hdfsDependencies

