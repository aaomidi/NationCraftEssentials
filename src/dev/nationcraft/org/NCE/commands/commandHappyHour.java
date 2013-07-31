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
        if (sender instanceof Player) {
            if (args.length == 1) {
                if (args[1].equalsIgnoreCase("fix")) {
                    Enabler.perms.groupRemove("world", "Default", "serversigns.use.*");
                    Enabler.perms.groupRemove("world", "Default", "kitmaster.kit.*");
                } else {
                    String timeString = args[1];
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
                        timeStringHours = timeString.replace("m", "");
                        time = (Integer.parseInt(timeStringHours));
                        time = time * 1200;
                        GivePermissions(time);
                    } else {
                        NCEChat.sendMessage(sender, "Use seconds, minutes or hours only!");
                    }
                }

            } else {
                NCEChat.sendMessage(sender, "&cThe correct usage of this command is /happyhour <time>");
            }
        }
    }

    private void GivePermissions(int time) {
        Enabler.perms.groupAdd("world", "Default", "serversigns.use.*");
        Enabler.perms.groupAdd("world", "Default", "kitmaster.kit.*");
        this._plugin.getServer().getScheduler().scheduleSyncDelayedTask(
                this._plugin, new Runnable() {
                    @Override
                    public void run() {
                        Enabler.perms.groupRemove("world", "Default", "serversigns.use.*");
                        Enabler.perms.groupRemove("world", "Default", "kitmaster.kit.*");
                    }
                }, time);
    }
}