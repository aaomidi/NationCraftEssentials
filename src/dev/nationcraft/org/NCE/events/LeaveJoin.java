/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.events;

import dev.nationcraft.org.NCE.NCE;
import dev.nationcraft.org.NCE.utils.MySQL;
import dev.nationcraft.org.NCE.utils.NCEChat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

/**
 *
 * @author aa_om_000
 */
public class LeaveJoin implements Listener {

    private NCE _plugin;

    public LeaveJoin(NCE plugin) {
        _plugin = plugin;
        _plugin.getServer().getPluginManager().registerEvents(this, _plugin);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        e.setJoinMessage(null);
        //Leaving before crashing fix!
        if (NCE.CrashList.contains(player.getName())) {
            this._plugin.getServer().getScheduler().scheduleSyncDelayedTask(
                    this._plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.sendBlockChange(player.getLocation(), 0x7fffffff, (byte) 127);
                            NCE.CrashList.remove(player.getName());

                        }
                    }, 50);

        } else {
            this._plugin.getServer().getScheduler().scheduleSyncDelayedTask(
                    this._plugin, new Runnable() {
                        @Override
                        public void run() {
                            NCEChat.motd(player);

                        }
                    }, 40);
        }
        if (player.hasPermission("ncessentials.mod")) {
            MySQL.sendTicketsAdmin(player.getName(), 0);
        }
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

    public void healthbars() {
        ScoreboardManager manager = _plugin.getServer().getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("showhealth", "health");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective.setDisplayName("/ 20");

        for (Player online : _plugin.getServer().getOnlinePlayers()) {
            online.setScoreboard(board);
            online.setHealth(online.getHealth()); //Update their health
        }
    }
}
