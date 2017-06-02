package io.prototypes.ml_repository.runtime.scikit

/**
  * Created by Bulat on 01.06.2017.
  */
case class ScikitMetadata(
                         model: String,
                         inputs: List[String],
                         outputs: List[String]
                         )

object ScikitMetadata {
  import io.prototypes.ml_repository.utils.MapAnyJson._
  import spray.json._

  implicit val scikitMetadataFormat = jsonFormat3(ScikitMetadata.apply)

  def fromJson(json: String): ScikitMetadata = {
    json.parseJson.convertTo[ScikitMetadata]
  }
}