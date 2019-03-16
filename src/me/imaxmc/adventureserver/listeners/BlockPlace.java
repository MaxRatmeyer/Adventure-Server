package me.imaxmc.adventureserver.listeners;

import me.imaxmc.adventureserver.AdventureServer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by maximilianratmeyer on 12/24/16.
 */
public class BlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (AdventureServer.getMapManager().getCurrentMap() == null) {
            e.setCancelled(true);
        }
    }

}
