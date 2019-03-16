package me.imaxmc.adventureserver.listeners;

import me.imaxmc.adventureserver.AdventureServer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by maximilianratmeyer on 12/24/16.
 */
public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (AdventureServer.getMapManager().getCurrentMap() == null) {
            e.setCancelled(true);
        }
    }

}
