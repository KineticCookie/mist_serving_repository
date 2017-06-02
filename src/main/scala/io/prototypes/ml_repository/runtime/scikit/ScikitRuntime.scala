package io.prototypes.ml_repository.runtime.scikit

import java.io._
import java.nio.file.Paths
import java.nio.file.Files

import io.prototypes.ml_repository.Model
import io.prototypes.ml_repository.runtime.MLRuntime
import io.prototypes.ml_repository.utils.FileUtils._

import scala.io.Source

/**
  * Created by Bulat on 31.05.2017.
  */
class ScikitRuntime(val scikitDir: File) extends MLRuntime {
  private def getMetadata(directory: File): ScikitMetadata = {
    val metadataPath = Paths.get(directory.getAbsolutePath, "metadata.json")
    val metadataSrc = Source.fromFile(metadataPath.toString)
    val metadataStr = try metadataSrc.getLines mkString "/n" finally metadataSrc.close()
    ScikitMetadata.fromJson(metadataStr)
  }

  override def getModel(directory: File): Option[Model] = {
    println(s"Directory: ${directory.getAbsolutePath}")
    val subDirs = directory.getSubDirectories

    val metadata = getMetadata(directory)

    val model = Model(directory.getName, "scikit", metadata.inputs, metadata.outputs)
    Some(model)
  }

  override def getModels: Seq[Model] = {
    val models = scikitDir.getSubDirectories
    models.map(getModel).filter(_.isDefined).map(_.get)
  }
}
