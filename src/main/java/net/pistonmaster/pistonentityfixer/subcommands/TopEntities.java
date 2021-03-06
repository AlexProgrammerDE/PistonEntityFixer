package net.pistonmaster.pistonentityfixer.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.*;

public class TopEntities implements SubCommand {
    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        Map<EntityType, Integer> entities = new EnumMap<>(EntityType.class);

        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entities.containsKey(entity.getType())) {
                    entities.put(entity.getType(), entities.get(entity.getType()) + 1);
                } else {
                    entities.put(entity.getType(), 1);
                }
            }
        }

        for (Map.Entry<EntityType, Integer> entry : sortByValue(entities).entrySet()) {
            sender.sendMessage(entry.getKey().name() + " " + entry.getValue());
        }

        return true;
    }

    @Override
    public String getDescription() {
        return "Shows the top entities in the world";
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
}
