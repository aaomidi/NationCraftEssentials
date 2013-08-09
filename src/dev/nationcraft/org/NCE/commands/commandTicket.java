/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.commands;

import dev.nationcraft.org.NCE.NCE;
import dev.nationcraft.org.NCE.utils.MySQL;
import dev.nationcraft.org.NCE.utils.NCEChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author aa_om_000
 */
public class commandTicket implements CommandExecutor {

    private NCE _plugin;

    public commandTicket(NCE plugin) {
        _plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("ticket")) {
            ticketHandler(sender, args);
        }
        return true;
    }

    private void ticketHandler(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (MySQL.checkTickets(player.getName())) {

                if ((args.length > 1)) {

                    String support = "";
                    for (int i = 1; i < args.length; i++) {
                        support += args[i] + " ";
                    }

                    if (MySQL.ticketPlayer(player.getName(), support) == true) {
                        NCEChat.sendMessage(sender, "&bYour ticket has been submitted! Please wait for a moderator to respond!");
                    } else {
                        NCEChat.sendMessage(sender, "&cSQL error, contact Admin!");
                    }

                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("show")) {
                        //show the tickets.
                    } else {
                        NCEChat.sendMessage(sender, "&cThe avalible options are /ticket show");
                    }
                } else {
                    NCEChat.sendMessage(sender, "&cThe correct usage of this command is /ticket <message>");
                }

            } else {
                NCEChat.sendMessage(sender, "&bYou already have a ticket open! Please wait for your current ticket to be answered!");
            }
        } else {
            NCEChat.LogInfo("This command is in game only!");
        }
    }
}
