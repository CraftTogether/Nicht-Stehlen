package xyz.crafttogether.nichtstehlen;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.crafttogether.nichtstehlen.configuration.ChestOwnershipHandler;
import xyz.crafttogether.nichtstehlen.configuration.ConfigHandler;
import xyz.crafttogether.nichtstehlen.listeners.BlockPlaceListener;
import xyz.crafttogether.nichtstehlen.listeners.PlayerInteractListener;

import java.io.IOException;

public class NichtStehlen extends JavaPlugin {
    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getConfig().options().copyDefaults();
        saveConfig();
        try {
            ChestOwnershipHandler.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConfigHandler.loadConfig();
        final PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new PlayerInteractListener(), this);
        manager.registerEvents(new BlockPlaceListener(), this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "NichtStehlen enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "NichtStehlen disabled");
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
