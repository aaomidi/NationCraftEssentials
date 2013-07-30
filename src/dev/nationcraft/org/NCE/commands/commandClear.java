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
public class commandClear implements CommandExecutor {

    private NCE _plugin;

    public commandClear(NCE plugin) {
        _plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("clear")) {
            clearChat(sender, args);
        }
        return true;
    }

    private void clearChat(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                for (int i = 1; i <= 300; i++) {
                    Bukkit.broadcastMessage("");
                }
                NCEChat.broadcastMessage("&cChat cleared by " + sender.getName());
            } else {
                NCEChat.sendMessage(sender, "&cThe correct usage of this command is /clear");
            }
        } else {
            NCEChat.LogInfo("This command is in game based only");
        }
    }
}
