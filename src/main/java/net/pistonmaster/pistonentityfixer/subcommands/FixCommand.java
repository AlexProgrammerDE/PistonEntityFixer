package net.pistonmaster.pistonentityfixer.subcommands;

import net.pistonmaster.pistonentityfixer.PistonEntityFixer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class FixCommand implements SubCommand {
    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
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

        return true;
    }

    @Override
    public String getDescription() {
        return "Kill and list entities by type.";
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
