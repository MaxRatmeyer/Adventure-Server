package me.imaxmc.adventureserver.commands;

import me.imaxmc.adventureserver.AdventureServer;
import me.imaxmc.adventureserver.maps.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by maximilianratmeyer on 12/24/16.
 */
public class startmap implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to do this!");
            return true;
        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("startmap")) {

            if (AdventureServer.getMapManager().getCurrentMap() != null) {
                player.sendMessage(ChatColor.RED + "A game has already started!");
                return true;
            }

            Inventory maps = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Choose a Map");

            for (Map map : AdventureServer.getMapManager().getMaps()) {
                ItemStack item = new ItemStack(Material.PAPER);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.GOLD + map.getName() + " by " + map.getAuthor());
                item.setItemMeta(meta);
                maps.setItem(map.getId(), item);
            }

            player.openInventory(maps);

        }
        return true;
    }
}
