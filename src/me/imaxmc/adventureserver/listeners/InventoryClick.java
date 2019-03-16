package me.imaxmc.adventureserver.listeners;

import me.imaxmc.adventureserver.AdventureServer;
import me.imaxmc.adventureserver.maps.Map;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * Created by maximilianratmeyer on 12/24/16.
 */
public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Inventory inventory = e.getInventory();
        Player player = (Player) e.getWhoClicked();

        if (inventory.getName().equalsIgnoreCase(ChatColor.GREEN + "Choose a Map")) {
            e.setCancelled(true);
            player.closeInventory();

            int slot = e.getSlot();

            Map map = AdventureServer.getMapManager().getMap(slot);

            if (map == null) {
                player.sendMessage(ChatColor.RED + "This map doesn't exist!");
                return;
            }

            AdventureServer.getMapManager().startMap(map);
        }
    }

}
