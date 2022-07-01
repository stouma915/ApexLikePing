package net.st915.contextualexecutor.builder

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
    if (commandLogic.isEmpty)
      throw new IllegalStateException("Command Logic is not yet set.")

    new ContextualExecutor {

      override def executionWith(context: CommandContext): IO[Unit] =
        IO.pure(commandLogic.get(context))

      override def tabCandidatesFor(context: CommandContext): IO[List[String]] =
        IO.pure {
          if (tabCompleteLogic.isEmpty)
            Nil
          else
            tabCompleteLogic.get(context)
        }

    }
  }

}
