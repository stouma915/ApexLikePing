package net.st915.apexlikeping

import cats.effect.IO
import org.bukkit.plugin.java.JavaPlugin

object ApexLikePing {

  private var _instance: Either[Throwable, ApexLikePing] = Left(
    new IllegalStateException("ApexLikePing is not yet ready.")
  )

  def instance: IO[Either[Throwable, ApexLikePing]] = IO.pure(_instance)

}

final class ApexLikePing extends JavaPlugin {

  import cats.effect.unsafe.implicits.global

  ApexLikePing._instance = Right(this)

  // region startup tasks

  private val startPlugin =
    for {
      _ <- IO(getLogger.info("ApexLikePing in enabled."))
    } yield ()

  // endregion

  // region shutdown tasks

  private val shutdownPlugin =
    for {
      _ <- IO(getLogger.info("ApexLikePing is disabled."))
    } yield ()

  // endregion

  override def onEnable(): Unit =
    startPlugin.unsafeRunSync()

  override def onDisable(): Unit =
    shutdownPlugin.unsafeRunSync()

}
