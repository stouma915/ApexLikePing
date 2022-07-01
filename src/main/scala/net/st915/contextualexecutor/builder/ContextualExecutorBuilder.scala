package net.st915.contextualexecutor.builder

import cats.effect.IO
import net.st915.contextualexecutor.{CommandContext, ContextualExecutor}

case class ContextualExecutorBuilder(
  commandLogic: Option[CommandContext => Unit],
  tabCompleteLogic: Option[CommandContext => List[String]]
) {

  def execution(func: CommandContext => Unit): ContextualExecutorBuilder =
    ContextualExecutorBuilder(Some(func), tabCompleteLogic)

  def tabComplete(func: CommandContext => List[String]): ContextualExecutorBuilder =
    ContextualExecutorBuilder(commandLogic, Some(func))

  def build(): ContextualExecutor = {
    val executeLogic = commandLogic match {
      Some(f) => f
      None    => _ => ()
    }
    val tabLogic = tabCompleteLogic match {
      Some(f) => f
      None    => _ => Nil
    }

    new ContextualExecutor {

      override def executionWith(context: CommandContext): IO[Unit] =
        IO(executeLogic(context))

      override def tabCandidatesFor(context: CommandContext): IO[List[String]] =
        IO(tabLogic(context))

    }
  }

}
