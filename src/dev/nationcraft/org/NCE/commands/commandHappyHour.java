/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.commands;

import dev.nationcraft.org.NCE.NCE;
import dev.nationcraft.org.NCE.utils.Enabler;
import dev.nationcraft.org.NCE.utils.NCEChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author aa_om_000
 */
public class commandHappyHour implements CommandExecutor {

    private NCE _plugin;
    public int time;

    public commandHappyHour(NCE plugin) {
        _plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmnd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("happyhour")) {

            HappyHourEx(sender, args);

        }
        return true;
    }

    private void HappyHourEx(CommandSender sender, String[] args) {
        //if (!(Enabler.perms == null)) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("fix")) {
                    //Enabler.perms.groupRemove("world", "Default", "serversigns.use.*");
                    //Enabler.perms.groupRemove("world", "Default", "kitmaster.kit.*");
                    //Temporary Fix
                    _plugin.getServer().dispatchCommand(_plugin.getServer().getConsoleSender(), "manload");
                    _plugin.getServer().dispatchCommand(_plugin.getServer().getConsoleSender(), "mangdelp default serversigns.use.*");
                    _plugin.getServer().dispatchCommand(_plugin.getServer().getConsoleSender(), "mangdelp default kitmaster.kit.*");
                    _plugin.getServer().dispatchCommand(_plugin.getServer().getConsoleSender(), "mansave");
                    NCEChat.broadcastMessage("&5Happy Hour has now Ended!");
                } else {
                    String timeString = args[0];
                    if (timeString.endsWith("s")) {
                        String timeStringSeconds;
                        timeStringSeconds = timeString.replace("s", "");
                        time = (Integer.parseInt(timeStringSeconds));
                        time = time * 20;
                        GivePermissions(time);
                    } else if (timeString.endsWith("m")) {
                        String timeStringMinutes;
                        timeStringMinutes = timeString.replace("m", "");
                        time = (Integer.parseInt(timeStringMinutes));
                        time = time * 1200;
                        GivePermissions(time);
                    } else if (timeString.endsWith("h")) {
                        String timeStringHours;
                        timeStringHours = timeString.replace("h", "");
                        time = (Integer.parseInt(timeStringHours));
                        time = time * 72000;
                        GivePermissions(time);
                    } else {
                        NCEChat.sendMessage(sender, "Use seconds, minutes or hours only!");
                    }
                }

            } else {
                NCEChat.sendMessage(sender, "&cThe correct usage of this command is /happyhour <time>");
            }
        }
        /* } else {
         //NCEChat.LogSevere("Vault not found!");
         //NCEChat.sendMessage(sender, "&cVault Not Found!");
         }
         */ }

    private void GivePermissions(int time) {
        //Enabler.perms.groupAdd("world", "Default", "serversigns.use.*");
        //Enabler.perms.groupAdd("world", "Default", "kitmaster.kit.*");
        //Temporary Fix
        _plugin.getServer().dispatchCommand(_plugin.getServer().getConsoleSender(), "manload");
        _plugin.getServer().dispatchCommand(_plugin.getServer().getConsoleSender(), "mangaddp default serversigns.use.*");
        _plugin.getServer().dispatchCommand(_plugin.getServer().getConsoleSender(), "mangaddp default kitmaster.kit.*");
        _plugin.getServer().dispatchCommand(_plugin.getServer().getConsoleSender(), "mansave");

        NCEChat.broadcastMessage("&bHappyHour Has Been Enabled! You can use ALL the kits for " + time / 20 + " seconds!");
        this._plugin.getServer().getScheduler().scheduleSyncDelayedTask(
                this._plugin, new Runnable() {
                    @Override
                    public void run() {
                        //Enabler.perms.groupRemove("world", "Default", "serversigns.use.*");
                        //Enabler.perms.groupRemove("world", "Default", "kitmaster.kit.*");
                        //Temporary Fix
                        _plugin.getServer().dispatchCommand(_plugin.getServer().getConsoleSender(), "manload");
                        _plugin.getServer().dispatchCommand(_plugin.getServer().getConsoleSender(), "mangdelp default serversigns.use.*");
                        _plugin.getServer().dispatchCommand(_plugin.getServer().getConsoleSender(), "mangdelp default kitmaster.kit.*");
                        _plugin.getServer().dispatchCommand(_plugin.getServer().getConsoleSender(), "mansave");
                    }
                }, time);
    }
}
