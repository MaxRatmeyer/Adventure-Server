package me.imaxmc.adventureserver.listeners;

import me.imaxmc.adventureserver.AdventureServer;
import me.imaxmc.adventureserver.utils.CommunicationAPI;
import me.imaxmc.adventureserver.utils.State;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

/**
 * Created by maximilianratmeyer on 12/24/16.
 */
public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        player.setHealth(20);
        player.setMaxHealth(20);
        player.setFoodLevel(20);
        player.setTotalExperience(0);
        player.getActivePotionEffects().clear();

        String header = "\u00A74\u00A7lAdventure Server Project";
        String footer = "\u00A76adv.maxratmeyer.com";
        CommunicationAPI.sendTabHF(player, header, footer);

        if (AdventureServer.getMapManager().getCurrentMap() == null) {
            player.teleport(new Location(Bukkit.getWorld("Lobby"), 0.5, 71, 0.5));
        } else {
            player.teleport(Bukkit.getWorld(AdventureServer.getMapManager().getCurrentMap().getFolderName()).getSpawnLocation());
        }

    }

}
