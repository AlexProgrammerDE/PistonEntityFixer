package net.pistonmaster.pistonentityfixer;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class PistonEntityFixer extends JavaPlugin {
    @Override
    public void onEnable() {
        Logger log = getLogger();

        log.info(ChatColor.AQUA + "Registering command");
        getServer().getPluginCommand("fixentities").setExecutor(new FixCommand());

        log.info(ChatColor.AQUA + "Done! :D");
    }
}
