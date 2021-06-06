package net.pistonmaster.pistonentityfixer;

import net.pistonmaster.pistonentityfixer.commands.*;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class PistonEntityFixer extends JavaPlugin {
    @Override
    public void onEnable() {
        Logger log = getLogger();

        log.info(ChatColor.AQUA + "Registering command");
        getServer().getPluginCommand("fixentities").setExecutor(new FixCommand());
        getServer().getPluginCommand("getentitybyid").setExecutor(new EntityByUUID());
        getServer().getPluginCommand("tameinfo").setExecutor(new TameInfo());
        getServer().getPluginCommand("topentities").setExecutor(new TopEntities());
        getServer().getPluginCommand("topplace").setExecutor(new TopPlace());
        getServer().getPluginCommand("pistonentityfixer").setExecutor(new MainCommand(this));

        log.info(ChatColor.AQUA + "Done! :D");
    }
}
