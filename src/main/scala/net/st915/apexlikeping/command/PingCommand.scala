package net.st915.apexlikeping.command

import cats.effect.IO
import net.st915.contextualexecutor.{CommandContext, ContextualExecutor}
import net.st915.contextualexecutor.builder.ContextualExecutorBuilder
import org.bukkit.command.TabExecutor

object PingCommand {

  import cats.effect.unsafe.implicits.global

  val executor: TabExecutor =
    ContextualExecutorBuilder
      .begin()
      .execution { context =>
        println("TEST")
      }
      .build()
      .asBukkitTabExecutor()

}
