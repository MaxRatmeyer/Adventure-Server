package me.imaxmc.adventureserver.maps;

import me.imaxmc.adventureserver.AdventureServer;
import me.imaxmc.adventureserver.utils.WorldDownloader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by maximilianratmeyer on 12/24/16.
 */
public class MapManager {

    public MapManager() {
        this.currentMap = null;
        maps = new ArrayList<>();
    }

    private Map currentMap;

    private ArrayList<Map> maps;

    public Map getCurrentMap() {
        return currentMap;
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public void startMap(final Map map) {
        Bukkit.broadcastMessage(ChatColor.GREEN + "Now playing " + map.getName() + " by " + map.getAuthor() + ". Teleporting to map in 10 seconds.");

        setCurrentMap(map);
        WorldDownloader.downloadMap(map.getDownloadLink(), map.getFileName(), map.getFolderName());

        if (map.getResourcePack() != null) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.setResourcePack(map.getResourcePack());
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.teleport(Bukkit.getWorld(map.getFolderName()).getSpawnLocation());
                }
            }
        }.runTaskLater(AdventureServer.getPlugin(), 200);
    }

    public void stopMap() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            players.teleport(new Location(Bukkit.getWorld("Lobby"), 0, 71, 0));
            players.setHealth(20);
            players.setMaxHealth(20);
            players.setFoodLevel(20);
            players.setTotalExperience(0);
            players.getActivePotionEffects().clear();
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getServer().unloadWorld(Bukkit.getWorld(getCurrentMap().getFolderName()), true);
                try {
                    WorldDownloader.dirDelete(new File(WorldDownloader.getRoot() + getCurrentMap().getFolderName() + "/"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskLater(AdventureServer.getPlugin(), 40);

        new BukkitRunnable() {
            @Override
            public void run() {
                setCurrentMap(null);
            }
        }.runTaskLater(AdventureServer.getPlugin(), 80);
    }

    public Map getMap(int id) {
        for (Map map : getMaps()) {
            if (map.getId() == id) {
                return map;
            }
        }
        return null;
    }

    public void addMap(Map map) {
        maps.add(map);
    }

    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
    }
}
