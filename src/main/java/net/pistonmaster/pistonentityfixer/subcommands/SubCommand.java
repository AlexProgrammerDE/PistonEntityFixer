package net.pistonmaster.pistonentityfixer.subcommands;

import org.bukkit.command.CommandSender;

public interface SubCommand {
    boolean onCommand(CommandSender sender, String[] args);

    String getDescription();
}
