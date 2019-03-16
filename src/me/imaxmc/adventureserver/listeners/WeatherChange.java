package me.imaxmc.adventureserver.listeners;

import me.imaxmc.adventureserver.AdventureServer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Created by maximilianratmeyer on 12/24/16.
 */
public class WeatherChange implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if (AdventureServer.getMapManager().getCurrentMap() == null) {
            e.setCancelled(true);
        }
    }

}
