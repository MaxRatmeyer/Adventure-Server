package me.imaxmc.adventureserver.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by maximilianratmeyer on 12/24/16.
 */
public class AsyncPlayerChat implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        e.setFormat(ChatColor.DARK_GRAY + "%s:" + ChatColor.GRAY + " %s");
    }
}
