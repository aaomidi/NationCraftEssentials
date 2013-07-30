/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.events;

import dev.nationcraft.org.NCE.NCE;
import dev.nationcraft.org.NCE.utils.NCEChat;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

/**
 *
 * @author aa_om_000
 */
public class Chat implements Listener {

    private NCE _plugin;
    private Map<Player, Boolean> chat = new HashMap<Player, Boolean>();

    public Chat(NCE plugin) {
        _plugin = plugin;
        _plugin.getServer().getPluginManager().registerEvents(this, _plugin);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        //Anti Spam
        if (chat.containsKey(e.getPlayer())) {
            if (!e.getPlayer().hasPermission("ncessentials.chat")) {
                NCEChat.sendMessage(e.getPlayer(), "&cSlow down a little bit, k?");
                e.setCancelled(true);
            }
        }
        //Anti Caps
        if (!e.getPlayer().hasPermission("ncessentials.caps")) {
            message = e.getMessage();
            String first = message.substring(0, 1);
            String msg = message.substring(1, message.length());
            message = first.toUpperCase() + msg.toLowerCase();
        }
        e.setMessage(message);
        if (!chat.containsKey(e.getPlayer())) {
            checkChat(e.getPlayer());
        }

    }

    private void checkChat(final Player p) {
        chat.put(p, true);
        _plugin.getServer().getScheduler().scheduleSyncDelayedTask(_plugin, new Runnable() {
            public void run() {
                chat.remove(p);
            }
        }, 20);
    }

}
