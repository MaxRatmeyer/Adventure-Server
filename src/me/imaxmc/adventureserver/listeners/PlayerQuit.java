package me.imaxmc.adventureserver.listeners;

import me.imaxmc.adventureserver.AdventureServer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by maximilianratmeyer on 12/24/16.
 */
public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        if (Bukkit.getOnlinePlayers().size() == 0) {
            AdventureServer.getMapManager().stopMap();
        }
    }

}
