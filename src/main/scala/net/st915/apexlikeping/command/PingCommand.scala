package net.st915.apexlikeping.command

import net.st915.contextualexecutor.{CommandContext, ContextualExecutor}
import org.bukkit.command.TabExecutor

object PingCommand {

  import cats.effect.unsafe.implicits.global

  val executor: TabExecutor = new ContextualExecutor {

    override def executionWith(context: CommandContext): IO[Unit] = IO {
    }

    override def tabCandidatesFor(context: CommandContext): IO[List[String]] = IO {
      Nil
    }

  }.asBukkitExecutor()

}
