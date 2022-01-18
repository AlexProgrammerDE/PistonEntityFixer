package net.pistonmaster.pistonentityfixer.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.*;

public class TopPlace implements SubCommand {
    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        if (args.length > 0) {
            Map<Chunk, Integer> chunks = new HashMap<>();

            for (World world : Bukkit.getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (entity.getType() == EntityType.valueOf(args[0])) {
                        if (chunks.containsKey(entity.getChunk())) {
                            chunks.put(entity.getChunk(), chunks.get(entity.getChunk()) + 1);
                        } else {
                            chunks.put(entity.getChunk(), 1);
                        }
                    }

                }
            }

            for (Map.Entry<Chunk, Integer> entry : sortByValue(chunks).entrySet()) {
                sender.sendMessage(coolFormat(entry.getKey()) + " - " + entry.getValue());
            }
        } else {
            sender.sendMessage("/topplace entitytype");
            return false;
        }

        return true;
    }

    @Override
    public String getDescription() {
        return "Shows the top chunks with the most entities of a certain type";
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    private String coolFormat(Chunk chunk) {
        return chunk.getX() * 16 + " " + chunk.getZ() * 16 + " " + chunk.getWorld().getEnvironment();
    }
}
