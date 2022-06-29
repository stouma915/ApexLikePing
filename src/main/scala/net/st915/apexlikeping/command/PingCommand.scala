package net.st915.apexlikeping.command

import org.bukkit.command.{Command, CommandSender, TabExecutor}

final class PingCommand extends TabExecutor {

  import scala.jdk.CollectionConverters._

  override def onCommand(
    sender: CommandSender,
    command: Command,
    alias: String,
    args: Array[String]
  ): Boolean = true

  override def onTabComplete(
    sender: CommandSender,
    command: Command,
    alias: String,
    args: Array[String]
  ): java.util.List[String] = List().asJava

}
