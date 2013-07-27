/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.commands;

import dev.nationcraft.org.NCE.utils.NCEChat;
import me.confuserr.banmanager.BmAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author aa_om_000
 */
public class commandWarn implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("warn")) {
            warnPlayer(sender, args);
            
        }
        return true;
    }
    
    private void warnPlayer(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                NCEChat.sendMessage(sender, "&cCorrect use of this command is /warn <player> [Reason]");
            } else if (args.length == 1) {
                NCEChat.sendMessage(sender, "&cCorrect use of this command is /warn <player> [Reason]");
                
            } else if (args.length >= 2) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    NCEChat.sendMessage(sender, "&cPlayer is NOT online");
                } else if (target == sender) {
                    NCEChat.sendMessage(sender, "&cYou may not warn yourself");
                    
                } else if (target.hasPermission("ncessentials.warn.exempt")) {
                    NCEChat.sendMessage(sender, "&cThis player is exempt for receiving warnings");
                } else {
                    String reason = "";
                    for (int i = 1; i < args.length; i++) {
                        reason += args[i] + " ";
                    }
                    BmAPI.warn(target.getName(), sender.getName(), reason);
                    NCEChat.sendMessage(target, "&cYou have been warned for &b" + reason + " &cby&b " + sender.getName());
                    NCEChat.broadcastMessage("&b" + target.getName() + " was warned for &c" + reason + " &bby &c" + sender.getName());
                }
                
            }
        } else {
            NCEChat.LogInfo("This command is player only");
        }
    }
    
}
