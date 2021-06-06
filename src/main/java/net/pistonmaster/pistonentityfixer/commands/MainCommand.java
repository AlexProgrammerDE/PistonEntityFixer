package net.pistonmaster.pistonentityfixer.commands;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.pistonmaster.pistonentityfixer.PistonEntityFixer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class MainCommand implements CommandExecutor {
    private final PistonEntityFixer plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("pistonentityfixer.fix")) {
            ComponentBuilder builder = new ComponentBuilder("--- PistonEntityFixer ---").color(ChatColor.GOLD);

            plugin.getDescription().getCommands().forEach((name, info) ->
                    builder.append("\n/" + name)
                            .color(ChatColor.GOLD)
                            .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + name + " "))
                            .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                    new ComponentBuilder("Click me!")
                                            .color(ChatColor.GOLD)
                                            .create()
                            ))
                            .append(" - ")
                            .color(ChatColor.GOLD)
                            .append(info.get("description").toString()));

            sender.spigot().sendMessage(builder.create());
        }

        return false;
    }
}
