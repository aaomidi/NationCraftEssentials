/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.events;

import dev.nationcraft.org.NCE.NCE;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 *
 * @author aa_om_000
 */
public class Movement implements Listener {

    private NCE _plugin;

    public Movement(NCE plugin) {
        _plugin = plugin;
        _plugin.getServer().getPluginManager().registerEvents(this, _plugin);
    }

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        Location loc = e.getPlayer().getLocation();
        if (Bukkit.getServerName().equalsIgnoreCase("NationCraft Main")) {
            if (loc.getBlockY() <= -10) {
                Location spawn = Bukkit.getWorld("world").getSpawnLocation();
                player.teleport(spawn);
            }
        }
    }
}
