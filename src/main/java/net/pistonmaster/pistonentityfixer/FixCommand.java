package net.pistonmaster.pistonentityfixer;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class FixCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("pistonentityfixer.fix")) {
            if (args.length > 1) {
                for (World world : Bukkit.getWorlds()) {
                    for (Entity entity : world.getEntities()) {
                        if (entity.getType() == EntityType.valueOf(args[0])) {
                            if (args[1].equalsIgnoreCase("list")) {
                                sender.sendMessage(entity.getLocation().getBlockX() + " " + entity.getLocation().getBlockY() + " " + entity.getLocation().getBlockZ());
                            } else if (args[1].equalsIgnoreCase("kill")) {
                                Bukkit.getScheduler().runTask(PistonEntityFixer.getPlugin(PistonEntityFixer.class), entity::remove);
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage("/fixentities entitytype [list|kill]");
                return false;
            }
        }

        return true;
    }
}
