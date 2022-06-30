package net.st915.contextualexecutor

import org.bukkit.command.{Command, CommandSender}

case class CommandContext(
  sender: CommandSender,
  command: Command,
  aliasUsed: String,
  args: List[String]
)
