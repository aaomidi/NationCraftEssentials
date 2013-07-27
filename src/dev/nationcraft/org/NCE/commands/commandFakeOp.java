/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.commands;

import dev.nationcraft.org.NCE.NCE;
import dev.nationcraft.org.NCE.utils.NCEChat;
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
public class commandFakeOp implements CommandExecutor {

    private NCE _plugin;

    public commandFakeOp(NCE plugin) {
        _plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("fakeop")) {

            fakeOp(sender, args);

        }
        return true;
    }

    private void fakeOp(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            if (args.length != 1) {
                NCEChat.sendMessage(sender, "&cThe correct usage of this command is /fakeop <player>");
            } else {
                final Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    NCEChat.sendMessage(sender, "&cThat player is NOT online");

                } else {
                    target.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "[CONSOLE: Opped " + target.getName() + "]");
                    target.sendMessage(ChatColor.YELLOW + "You Are Now Op!");
                    this._plugin.getServer().getScheduler().scheduleSyncDelayedTask(
                            this._plugin, new Runnable() {
                                public void run() {
                                    target.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&1H&2e&3r&4p&5A&6D&ae&br&cp! \n &c You can't have OP :D \n &6Maybe read our rules next time?"));
                                    NCEChat.broadcastMessage(" &7[ &6Nation &7] &b&l" + target.getName() + "&b is asking for OP. Learn from his/her mistake :)");
                                }
                            }, 20 * 10);
                }
            }

        }
    }
}
