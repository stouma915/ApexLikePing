package net.st915.contextualexecutor

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import org.bukkit.command.{Command, CommandSender, TabExecutor}

trait ContextualExecutor {

  def executionWith(context: CommandContext): IO[Unit]

  def tabCandidatesFor(context: CommandContext): IO[List[String]] = IO.pure(Nil)

  def asBukkitTabExecutor(): TabExecutor = new TabExecutor {

    import scala.jdk.CollectionConverters.*

    override def onCommand(
      sender: CommandSender,
      command: Command,
      label: String,
      args: Array[String]
    ): Boolean = {
      val context = CommandContext(sender, command, label, args.toList)

      executionWith(context).unsafeRunSync()

      true
    }

    override def onTabComplete(
      sender: CommandSender,
      command: Command,
      label: String,
      args: Array[String]
    ): java.util.List[String] = {
      val context = CommandContext(sender, command, label, args.toList)

      tabCandidatesFor(context).unsafeRunSync()
    }.asJava

  }

}
