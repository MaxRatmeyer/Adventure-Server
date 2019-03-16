package me.imaxmc.adventureserver.listeners;

import me.imaxmc.adventureserver.AdventureServer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import javax.persistence.Entity;

/**
 * Created by maximilianratmeyer on 12/24/16.
 */
public class EntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (AdventureServer.getMapManager().getCurrentMap() == null) {
            e.setCancelled(true);
        }
    }

}
