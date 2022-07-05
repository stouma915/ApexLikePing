package net.st915.contextualexecutor.builder

import cats.effect.IO
import net.st915.contextualexecutor.{CommandContext, ContextualExecutor}

object ContextualExecutorBuilder {

  val defaultCommandLogic: CommandContext => Unit = _ => ()
  val defaultTabCompleteLogic: CommandContext => List[String] = _ => Nil

  def begin(): ContextualExecutorBuilder =
    ContextualExecutorBuilder(defaultCommandLogic, defaultTabCompleteLogic)

}

case class ContextualExecutorBuilder(
  commandLogic: CommandContext => Unit,
  tabCompleteLogic: CommandContext => List[String]
) {

  def execution(func: CommandContext => Unit): ContextualExecutorBuilder =
    ContextualExecutorBuilder(func, tabCompleteLogic)

  def tabComplete(func: CommandContext => List[String]): ContextualExecutorBuilder =
    ContextualExecutorBuilder(commandLogic, func)

  def build(): ContextualExecutor =
    new ContextualExecutor {

      override def executionWith(context: CommandContext): IO[Unit] =
        IO.pure(commandLogic(context))

      override def tabCompleteWith(context: CommandContext): IO[List[String]] =
        IO.pure(tabCompleteLogic(context))

    }

}
