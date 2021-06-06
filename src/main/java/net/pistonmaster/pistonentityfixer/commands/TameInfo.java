package net.pistonmaster.pistonentityfixer.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.jetbrains.annotations.NotNull;

public class TameInfo implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (sender.hasPermission("pistonentityfixer.fix")) {
                player.getLocation().getNearbyLivingEntities(25).forEach(livingEntity -> {
                    if (livingEntity instanceof Tameable) {
                        Tameable tameable = (Tameable) livingEntity;

                        if (tameable.isTamed()) {
                            sender.sendMessage("Entity: " + livingEntity.getType().name());
                            sender.sendMessage("Coords: " + coolFormat(livingEntity.getLocation()));
                            sender.sendMessage("Username: " + tameable.getOwner().getName());
                            sender.sendMessage("UUID: " + tameable.getOwner().getUniqueId());
                        }
                    }
                });
            }
        } else {
            sender.sendMessage("Player only!");
        }

        return true;
    }

    private String coolFormat(Location loc) {
        return loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ();
    }
}
