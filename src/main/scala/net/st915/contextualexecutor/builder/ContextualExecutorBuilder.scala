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

  def build(): ContextualExecutor =
    new ContextualExecutor {

      override def executionWith(context: CommandContext): IO[Unit] =
        IO.unit

      override def tabCandidatesFor(context: CommandContext): IO[List[String]] =
        IO.pure(Nil)

    }

}
