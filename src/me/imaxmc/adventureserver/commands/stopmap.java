package me.imaxmc.adventureserver.commands;

import me.imaxmc.adventureserver.AdventureServer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by maximilianratmeyer on 12/24/16.
 */
public class stopmap implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to do this!");
            return true;
        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("stopmap")) {

            if (AdventureServer.getMapManager().getCurrentMap() == null) {
                player.sendMessage(ChatColor.RED + "There is no map right now!");
                return true;
            }

            AdventureServer.getMapManager().stopMap();

        }

        return true;
    }
}
