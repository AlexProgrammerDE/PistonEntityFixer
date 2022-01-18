package net.pistonmaster.pistonentityfixer.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class EntityByUUID implements SubCommand {
    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
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

        return true;
    }

    @Override
    public String getDescription() {
        return "Finds an entity by its UUID";
    }

    private String coolFormat(Location loc) {
        return loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + " " + loc.getWorld().getEnvironment();
    }
}
