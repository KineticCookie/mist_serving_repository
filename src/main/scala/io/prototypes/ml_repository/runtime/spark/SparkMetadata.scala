package io.prototypes.ml_repository.runtime.spark

/**
  * Created by Bulat on 31.05.2017.
  */
case class SparkMetadata(
                     `class`: String,
                     timestamp: Long,
                     sparkVersion: String,
                     uid: String,
                     paramMap: Map[String, Any],
                     numFeatures: Option[Int],
                     numClasses: Option[Int],
                     numTrees: Option[Int]
                   )

object SparkMetadata {
  import io.prototypes.ml_repository.utils.MapAnyJson._
  import spray.json._

  implicit val sparkMetadataFormat = jsonFormat8(SparkMetadata.apply)

  def fromJson(json: String): SparkMetadata = {
    json.parseJson.convertTo[SparkMetadata]
  }

  def extractParams(sparkMetadata: SparkMetadata, params: Seq[String]): Seq[String] = {
    params.map(sparkMetadata.paramMap.get).filter(_.isDefined).map(_.get.asInstanceOf[String])
  }
}