import sbt.Keys._
import sbtassembly.Plugin.AssemblyKeys._

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

assemblySettings
mergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
  case PathList("META-INF", "services", "org.apache.hadoop.fs.FileSystem") => MergeStrategy.filterDistinctLines
  case m if m.startsWith("META-INF") => MergeStrategy.discard
  case PathList("javax", "servlet", xs@_*) => MergeStrategy.first
  case PathList("org", "apache", xs@_*) => MergeStrategy.first
  case PathList("org", "jboss", xs@_*) => MergeStrategy.first
  case "about.html" => MergeStrategy.rename
  case "reference.conf" => MergeStrategy.concat
  case PathList("org", "datanucleus", xs@_*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}
test in assembly := {}