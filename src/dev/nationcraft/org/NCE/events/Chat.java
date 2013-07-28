/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.events;

import dev.nationcraft.org.NCE.NCE;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

/**
 *
 * @author aa_om_000
 */
public class Chat implements Listener{   
    private NCE _plugin;
    public Chat(NCE plugin){
        _plugin=plugin;
        _plugin.getServer().getPluginManager().registerEvents(this, _plugin);
    }
    @EventHandler
    public void onPlayerChat(ASyncPlayerChatEvent e){
        
    }
    
}

