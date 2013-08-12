/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.commands;

import dev.nationcraft.org.NCE.NCE;
import dev.nationcraft.org.NCE.utils.NCEChat;
import java.util.ArrayList;
import java.util.Random;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author aa_om_000
 */
public class command8Ball implements CommandExecutor {

    private NCE _plugin;
    public static ArrayList<String> msgs = new ArrayList<>();

    public command8Ball(NCE plugin) {
        _plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("8ball")) {
            ball(sender, args);
        }
        return true;
    }

    private void ball(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player) sender;
            if (args.length == 0) {
                NCEChat.sendMessage(sender, "&cThe correct usage of this command is &a/8ball <statement>");
            } else {
                String ballstring = "";
                for (int i = 0; i < args.length; i++) {
                    ballstring += args[i] + " ";
                }

                NCEChat.broadcastMessage(p.getName() + " &3Asked: &6" + ballstring);
                NCEChat.broadcastMessage("&b&lNationCraft &r&ashakes the magical 8ball...");
                this._plugin.getServer().getScheduler().scheduleSyncDelayedTask(
                        this._plugin, new Runnable() {
                            @Override
                            public void run() {
                                randomAnswerPicker();
                            }
                        }, 100);

            }
        }
    }

    private void randomAnswerPicker() {

        Random rand = new Random();
        int poss = rand.nextInt(3);
        if (poss == 0) {
            sendTrueMessage();
        } else if (poss == 1) {
            sendNutMessage();
        } else if (poss == 2) {
            sendFalseMessage();
        }
    }

    private void sendTrueMessage() {
        Random rand = new Random();
        msgs.add("&2It is decidedly so");
        msgs.add("&2You may rely on it");
        msgs.add("&2One would be wise to think so");
        msgs.add("&2Signs point to yes");
        msgs.add("&2Yes, definitely");
        int size = msgs.size();
        int got = rand.nextInt(size);
        NCEChat.broadcastMessage(msgs.get(got));
    }

    private void sendNutMessage() {
        Random rand = new Random();
        msgs.add("&7You know the answer better than I");
        msgs.add("&7Ask again later");
        msgs.add("&7Concentrate and ask again");
        msgs.add("&7Maybe...");
        int size = msgs.size();
        int got = rand.nextInt(size);
        NCEChat.broadcastMessage(msgs.get(got));
    }

    private void sendFalseMessage() {
        Random rand = new Random();
        msgs.add("&4My sources say no");
        msgs.add("&4In your dreams");
        msgs.add("&4Outlook not so good");
        msgs.add("&4My reply is no");
        msgs.add("&4Don't count on it");
        int size = msgs.size();
        int got = rand.nextInt(size);
        NCEChat.broadcastMessage(msgs.get(got));
    }

}
