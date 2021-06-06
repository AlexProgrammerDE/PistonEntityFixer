package net.pistonmaster.pistonentityfixer.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class EntityByUUID implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender.hasPermission("pistonentityfixer.fix")) {
            if (args.length > 0) {
                for (World world : Bukkit.getWorlds()) {
                    for (Entity entity : world.getEntities()) {
                        if (entity.getUniqueId().equals(UUID.fromString(args[0]))) {
                            sender.sendMessage(coolFormat(entity.getLocation()));

                            for (Player player : entity.getLocation().getNearbyPlayers(50)) {
                                sender.sendMessage("Nearby player: " + player.getName());
                            }
                            return true;
                        }
                    }
                }

                sender.sendMessage("Couldn't find entity with that uuid!");
            } else {
                sender.sendMessage("/getentitybyid [id]");
                return false;
            }
        }

        return true;
    }

    private String coolFormat(Location loc) {
        return loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + " " + loc.getWorld().getEnvironment();
    }
}
