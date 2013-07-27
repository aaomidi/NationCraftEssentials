/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.events;

import dev.nationcraft.org.NCE.NCE;
import dev.nationcraft.org.NCE.utils.NCEChat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author aa_om_000
 */
public class onJoinEvent implements Listener {

    private NCE _plugin;
public onJoinEvent(NCE plugin){
    _plugin=plugin;
    _plugin.getServer().getPluginManager().registerEvents(this, _plugin);
}
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        NCEChat.motd(player);
    }
}
