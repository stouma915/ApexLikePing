package net.st915.contextualexecutor.builder

import net.st915.contextualexecutor.CommandContext

case class ContextualExecutorBuilder(
  commandLogic: Option[CommandContext => Unit],
  tabCompleteLogic: Option[CommandContext => List[String]]
) {

  def execution(func: CommandContext => Unit): ContextualExecutorBuilder =
    ContextualExecutorBuilder(Some(func), tabCompleteLogic)

}
