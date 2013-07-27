/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.commands;

import dev.nationcraft.org.NCE.NCE;
import dev.nationcraft.org.NCE.utils.NCEChat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author aa_om_000
 */
public class commandCrash implements CommandExecutor {

    private NCE _plugin;

    public commandCrash(NCE plugin) {
        _plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("crash")) {
            crashPlayer(sender, args);

        }
        return true;
    }

    private void crashPlayer(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                NCEChat.sendMessage(sender, "&cCorrect use of this command is /crash <player> [Reason]");
            } else if (args.length == 1) {
                NCEChat.sendMessage(sender, "&cCorrect use of this command is /crash <player> [Reason]");
            } else {
                final Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    NCEChat.sendMessage(sender, "&cPlayer is NOT online");
                } else {
                    String reason = "";
                    for (int i = 1; i < args.length; i++) {
                        reason += args[i] + " ";
                    }
                    NCEChat.sendMessage(target, "&cIn 5 seconds you will be crashed for: &b" + reason);
                    NCEChat.broadcastMessage("&c" + target.getName() + " &bwill be crashed in 5 seconds for: " + "&c" + reason);
                    this._plugin.getServer().getScheduler().scheduleSyncDelayedTask(
                            this._plugin, new Runnable() {
                                public void run() {
                                    target.sendBlockChange(target.getLocation(), 0x7fffffff, (byte) 127);
                                }
                            }, 100);
                }
            }
        } else {
            NCEChat.LogInfo("This command is in game only");
        }
    }
}
