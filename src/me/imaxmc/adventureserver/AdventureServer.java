package me.imaxmc.adventureserver;

import me.imaxmc.adventureserver.commands.startmap;
import me.imaxmc.adventureserver.commands.stopmap;
import me.imaxmc.adventureserver.listeners.*;
import me.imaxmc.adventureserver.maps.Map;
import me.imaxmc.adventureserver.maps.MapManager;
import me.imaxmc.adventureserver.utils.State;
import me.imaxmc.adventureserver.utils.WorldDownloader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Created by maximilianratmeyer on 12/24/16.
 */
public class AdventureServer extends JavaPlugin {

    private static Plugin plugin;
    private static MapManager mapManager;

    private static State state;

    @Override
    public void onEnable() {

        plugin = this;
        mapManager = new MapManager();
        state = State.JOINABLE;

        saveDefaultConfig();
        registerMaps();

        registerListeners();
        registerCommands();

        WorldDownloader.downloadMap("https://dl.dropbox.com/s/mdac9kkhetgnlpf/Lobby.zip", "Lobby.zip", "Lobby");

    }

    @Override
    public void onDisable() {

        Bukkit.getServer().unloadWorld(Bukkit.getWorld("Lobby"), true);
        try {
            WorldDownloader.dirDelete(new File(WorldDownloader.getRoot() + "Lobby" + "/"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bukkit.getServer().unloadWorld(Bukkit.getWorld("world"), true);
        try {
            WorldDownloader.dirDelete(new File(WorldDownloader.getRoot() + "world" + "/"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        plugin = null;

    }

    public void registerCommands() {
        getCommand("startmap").setExecutor(new startmap());
        getCommand("stopmap").setExecutor(new stopmap());
    }

    public void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new AsyncPlayerChat(), this);
        pm.registerEvents(new BlockBreak(), this);
        pm.registerEvents(new BlockPlace(), this);
        pm.registerEvents(new EntityDamage(), this);
        pm.registerEvents(new FoodLevelChange(), this);
        pm.registerEvents(new InventoryClick(), this);
        pm.registerEvents(new PlayerDropItem(), this);
        pm.registerEvents(new PlayerInteract(), this);
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerLogin(), this);
        pm.registerEvents(new PlayerQuit(), this);
        pm.registerEvents(new ServerListPing(), this);
        pm.registerEvents(new WeatherChange(), this);
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static MapManager getMapManager() {
        return mapManager;
    }

    public static State getState() {
        return state;
    }

    public static void setState(State state) {
        AdventureServer.state = state;
    }

    public void registerMaps() {
        if (getConfig().getConfigurationSection("Maps") != null) {
            for (String maps : getConfig().getConfigurationSection("Maps").getKeys(false)) {

                String name = getConfig().getString("Maps." + maps + ".Name");
                String author = getConfig().getString("Maps." + maps + ".Author");
                String downloadLink = getConfig().getString("Maps." + maps + ".DownloadLink");
                String fileName = getConfig().getString("Maps." + maps + ".FileName");
                String folderName = getConfig().getString("Maps." + maps + ".FolderName");
                int id = getConfig().getInt("Maps." + maps + ".ID");
                String resourcePack = getConfig().getString("Maps." + maps + ".ResourcePack");

                if (resourcePack == null) {
                    getMapManager().addMap(new Map(name, author, downloadLink, fileName, folderName, id));
                }
                if (resourcePack != null) {
                    getMapManager().addMap(new Map(name, author, downloadLink, fileName, folderName, id, resourcePack));
                }

            }
        }
    }

}
