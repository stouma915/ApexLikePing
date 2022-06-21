package net.st915.apexlikeping

import cats.effect.IO
import org.bukkit.plugin.java.JavaPlugin

object ApexLikePing {

  private var instance: ApexLikePing = _

  def getInstance: IO[ApexLikePing] = IO.pure(instance)

}

final class ApexLikePing extends JavaPlugin {

  import cats.effect.unsafe.implicits.global

  ApexLikePing.instance = this

  // region startup tasks

  private val startPlugin = for {
    _ <- IO {
      getLogger.info("ApexLikePing in enabled.")
    }
  }

  // endregion

  // region shutdown tasks

  private val shutdownPlugin = for {
    _ <- IO {
      getLogger.info("ApexLikePing is disabled.")
    }
  }

  // endregion

  override def onEnable(): Unit =
    startPlugin.unsafeRunSync()

  override def onDisable(): Unit =
    shutdownPlugin.unsafeRunSync()

}
