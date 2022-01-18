package net.pistonmaster.pistonentityfixer;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.pistonmaster.pistonentityfixer.subcommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@RequiredArgsConstructor
public class MainCommand implements CommandExecutor {
    private final PistonEntityFixer plugin;
    private final Map<String, SubCommand> subCommands = new ImmutableBiMap.Builder<String, SubCommand>()
            .put("fixentities", new FixCommand())
            .put("getentitybyid", new EntityByUUID())
            .put("tameinfo", new TameInfo())
            .put("topentities", new TopEntities())
            .put("topplace", new TopPlace())
            .build();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("pistonentityfixer.fix")) {
            return true;
        }

        for (Map.Entry<String, SubCommand> entry : subCommands.entrySet()) {
            if (args.length >= 1 && args[0].equalsIgnoreCase(entry.getKey())) {
                return entry.getValue().onCommand(sender, args);
            }
        }

        ComponentBuilder builder = new ComponentBuilder("--- PistonEntityFixer ---").color(ChatColor.GOLD);

        subCommands.forEach((key, value) -> builder.append("\n/pef " + key)
                .color(ChatColor.GOLD)
                .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/pef " + key + " "))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click me!")
                                .color(ChatColor.GOLD)
                                .create()
                ))
                .append(" - ")
                .color(ChatColor.GOLD)
                .append(value.getDescription()));

        sender.spigot().sendMessage(builder.create());

        return false;
    }
}
