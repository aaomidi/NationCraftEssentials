/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.events;

import dev.nationcraft.org.NCE.NCE;
import dev.nationcraft.org.NCE.utils.NCEChat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author aa_om_000
 */
public class Connection implements Listener {

    private NCE _plugin;

    public Connection(NCE plugin) {
        _plugin = plugin;
        _plugin.getServer().getPluginManager().registerEvents(this, _plugin);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        this._plugin.getServer().getScheduler().scheduleSyncDelayedTask(
                this._plugin, new Runnable() {
                    @Override
                    public void run() {
                        NCEChat.motd(player);
                    }
                }, 40);
        e.setJoinMessage(null);
        /*Leaving before crashing fix!
         if (NCE.CrashList.get(0) == player) {
         this._plugin.getServer().getScheduler().scheduleSyncDelayedTask(
         this._plugin, new Runnable() {
         @Override
         public void run() {
         player.sendBlockChange(player.getLocation(), 0x7fffffff, (byte) 127);
         NCE.CrashList.remove(player);
         }
         }, 50);

         }
         */ }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }
}
