package net.pistonmaster.pistonentityfixer.commands;

import net.pistonmaster.pistonentityfixer.PistonEntityFixer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class FixCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender.hasPermission("pistonentityfixer.fix")) {
            if (args.length > 1) {
                for (World world : Bukkit.getWorlds()) {
                    for (Entity entity : world.getEntities()) {
                        if (entity.getType() == getType(args[0])) {
                            if (args[1].equalsIgnoreCase("list")) {
                                sender.sendMessage(coolFormat(entity.getLocation()) + " " + entity.getUniqueId());
                            } else if (args[1].equalsIgnoreCase("kill")) {
                                Bukkit.getScheduler().runTask(PistonEntityFixer.getPlugin(PistonEntityFixer.class), entity::remove);
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage("/fixentities entitytype [list|kill] [verbose]");
                return false;
            }
        }

        return true;
    }

    private EntityType getType(String str) {
        try {
            return EntityType.valueOf(str);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private String coolFormat(Location loc) {
        return loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + " " + loc.getWorld().getEnvironment();
    }
}
