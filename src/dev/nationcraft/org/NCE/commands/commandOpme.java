/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.commands;

import dev.nationcraft.org.NCE.NCE;
import dev.nationcraft.org.NCE.utils.NCEChat;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author aa_om_000
 */
public class commandOpme implements CommandExecutor {

    private NCE _plugin;
    private ArrayList<String> operator = new ArrayList<>();

    public commandOpme(NCE plugin) {
        _plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("opme")) {

            opme(sender, args);

        }
        return true;
    }

    private void opme(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            final Player player = Bukkit.getPlayer(sender.getName());
            {
                if (args.length == 0) {
                    if (!(this.operator.contains(player.getName()))) {
                        player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "[CONSOLE: Opped " + player.getName() + "]");
                        player.sendMessage(ChatColor.YELLOW + "You Are Now Op!");
                        this.operator.add(player.getName());
                        this._plugin.getServer().getScheduler().scheduleSyncDelayedTask(
                                this._plugin, new Runnable() {
                            public void run() {
                                player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&1H&2e&3r&4p&5A&6D&ae&br&cp! \n &c You can't have OP :D \n &6Maybe read our rules next time?"));
                                NCEChat.broadcastMessage(" &7[ &6Nation &7] &b&l" + player.getName() + "&b just used the /opme command :)");
                            }
                        }, 20 * 10);
                    } else {
                        NCEChat.sendMessage(sender, "&cYou already are OP!");
                    }
                } else {
                    NCEChat.sendMessage(sender, "&cThe correct usage of the command is: /opme");
                }
            }
        }

    }
}