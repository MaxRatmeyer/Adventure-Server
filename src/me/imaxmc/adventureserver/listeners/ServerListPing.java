package me.imaxmc.adventureserver.listeners;

import me.imaxmc.adventureserver.AdventureServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

/**
 * Created by maximilianratmeyer on 12/24/16.
 */
public class ServerListPing implements Listener {

    @EventHandler
    public void onServerListPing(ServerListPingEvent e) {
        String status = "";

        if (AdventureServer.getMapManager().getCurrentMap() == null) {
            status = "Nothing";
        } else {
            status = AdventureServer.getMapManager().getCurrentMap().getName() + " by " + AdventureServer.getMapManager().getCurrentMap().getAuthor();
        }

        e.setMaxPlayers(Bukkit.getOnlinePlayers().size() + 1);
        e.setMotd(ChatColor.RED + "" + ChatColor.BOLD + "Adventure Server Project" + "\n" + ChatColor.RESET + "" + ChatColor.GOLD + "Now Playing: " + status);
    }

}
