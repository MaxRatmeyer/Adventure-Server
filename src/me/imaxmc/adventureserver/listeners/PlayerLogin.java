package me.imaxmc.adventureserver.listeners;

import me.imaxmc.adventureserver.AdventureServer;
import me.imaxmc.adventureserver.utils.State;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Created by maximilianratmeyer on 12/24/16.
 */
public class PlayerLogin implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        if (AdventureServer.getState().equals(State.NONJOINABLE)) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Server setting up. Please join back in a few seconds.");
        }
    }

}
