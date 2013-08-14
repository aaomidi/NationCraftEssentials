/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.commands;

import dev.nationcraft.org.NCE.NCE;
import dev.nationcraft.org.NCE.utils.Enabler;
import dev.nationcraft.org.NCE.utils.NCEChat;
import java.util.ArrayList;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author aa_om_000
 */
public class commandFlirt implements CommandExecutor {

    private NCE _plugin;
    private static ArrayList<String> players = new ArrayList<>();
    private Random rand = new Random();

    public commandFlirt(NCE plugin) {
        _plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("flirt")) {
            flirt(sender, args);
        }
        return true;
    }

    private void flirt(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 1) {
                if (!(players.contains("enabled"))) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        final Player target = Bukkit.getPlayer(args[0]);
                        players.add("enabled");
                        NCEChat.broadcastPluginMessage("&6" + p.getName() + " &2flirts with: &6" + target.getName());
                        this._plugin.getServer().getScheduler().scheduleSyncDelayedTask(
                                this._plugin, new Runnable() {
                                    @Override
                                    public void run() {
                                        sendFlirtMessage(target.getName());
                                        players.remove("enabled");
                                    }
                                }, 40);

                    } else {
                        NCEChat.sendMessage(sender, "&cPlayer is offline!");
                    }
                } else {
                    NCEChat.sendMessage(sender, "&cThere is already a flirt comencing!");
                }
            } else {
                NCEChat.sendMessage(sender, "&cThe correct usage of this command is &a/flirt <player>");
            }
        }
    }

    private void sendFlirtMessage(String name) {
        int size = Enabler.flirtMsgs.size();
        int got = rand.nextInt(size);
        NCEChat.broadcastPluginMessage("&e" + Enabler.flirtMsgs.get(got));
    }
}
