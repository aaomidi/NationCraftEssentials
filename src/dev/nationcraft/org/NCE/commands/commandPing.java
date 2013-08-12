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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_6_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 *
 * @author aa_om_000
 */
public class commandPing implements CommandExecutor {

    private NCE _plugin;

    public commandPing(NCE plugin) {
        _plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("ping")) {

            myPing(sender, args);

        }
        return true;
    }

    private void myPing(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                if (sender.hasPermission("ncessentials.ping")) {
                    int ping = ((CraftPlayer) p).getHandle().ping;
                    if (ping <= 100) {
                        NCEChat.sendMessage(sender, "&7Your ping is:&a " + ping);
                    } else if (ping <= 300) {
                        NCEChat.sendMessage(sender, "&7Your ping is:&e " + ping);
                    } else if (300 < ping) {
                        NCEChat.sendMessage(sender, "&7Your ping is:&4 " + ping);
                    }
                } else {
                    NCEChat.sendMessage(sender, "&cNo permissions!");
                }
            } else if (args.length == 1) {
                if (sender.hasPermission("ncessentials.ping.others")) {

                    Player target = Bukkit.getPlayer(args[0]);
                    if (!(target == null)) {
                        int pingtarget = ((CraftPlayer) target).getHandle().ping;
                        if (pingtarget <= 100) {
                            NCEChat.sendMessage(sender, "&7" + target.getName() + "'s ping is:&a " + pingtarget);
                        } else if (pingtarget <= 300) {
                            NCEChat.sendMessage(sender, "&7" + target.getName() + "'s ping is:&e " + pingtarget);
                        } else if (300 < pingtarget) {
                            NCEChat.sendMessage(sender, "&7" + target.getName() + "'s ping is:&4 " + pingtarget);
                        } else {
                            NCEChat.sendMessage(sender, "&4No Permissions!");
                        }
                    } else {
                        NCEChat.sendMessage(sender, "&cPlayer is offline!");
                    }
                } else {
                    NCEChat.sendMessage(sender, "&4Incorrect Command Usage");
                }
            }

        }
    }
}
