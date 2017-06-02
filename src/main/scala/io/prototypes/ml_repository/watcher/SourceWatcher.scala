package io.prototypes.ml_repository.watcher

import akka.actor.Actor
import akka.event.Logging

/**
  * Created by Bulat on 31.05.2017.
  */
trait SourceWatcher extends Actor {
    val log = Logging(context.system, this)
}
