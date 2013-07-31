/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.utils;

import dev.nationcraft.org.NCE.NCE;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author aa_om_000
 */
public class NCEChat {

    private static NCE _plugin;
    private static String pluginName;
    private static String logName;
    private static String prefix;
    private static String version;
    private static ChatColor defaultChatColor = ChatColor.AQUA;
    private static final Logger log = Logger.getLogger("Minecraft");

    public NCEChat(NCE plugin) {
        _plugin = plugin;
        pluginName = _plugin.getName();
        logName = "[" + pluginName + "] ";
        prefix = ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + pluginName + ChatColor.GRAY + "] ";
        version = _plugin.getDescription().getVersion();
    }

    public static void LogInfo(String message) {
        log.info(logName + message);
    }

    public static void LogSevere(String message) {
        log.severe(message);
    }

    public static void LogWarning(String message) {
        log.warning(message);
    }

    public static void enableMessage() {
        LogInfo("=============================================");
        LogInfo("=           NationCraftEssentials           =");
        LogInfo("=                                           =");
        LogInfo("= Loaded configs successfully               =");
        LogInfo("= Loaded commands sucessfully               =");
        LogInfo("= Loaded utils sucessfully                  =");
        LogInfo("=                                           =");
        LogInfo("=============================================");
        LogInfo("NCE version " + version + " has been enabled");
    }

    public static void disableMessage() {
        LogInfo("NCE " + version + " has been disabled");
    }

    public static void sendMessage(CommandSender player, String message) {
        player.sendMessage(prefix + defaultChatColor + ChatColor.translateAlternateColorCodes('&', message));
        
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(prefix + defaultChatColor + ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void broadcastPluginMessage(String message) {
        Bukkit.broadcastMessage(prefix + defaultChatColor + ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(defaultChatColor + ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void missingPermission(CommandSender player, String permission) {
        player.sendMessage(ChatColor.RED + "Influent permissions to execute this command.");
        player.sendMessage(ChatColor.RED + "You're missing the permission node " + ChatColor.ITALIC + permission);
    }

    public static void missingPermission(Player player, String permission) {
        player.sendMessage(ChatColor.RED + "Influent permissions to execute this command.");
        player.sendMessage(ChatColor.RED + "You're missing the permission node " + ChatColor.ITALIC + permission);
    }

    public static void errorMessage(Exception e) {
        LogWarning("An error occurred " + e);
    }

    public static void motd(Player player) {
        String split = "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-" + ChatColor.DARK_GRAY + "-" + ChatColor.RESET + "-";
        player.sendMessage(split);
        player.sendMessage(ChatColor.DARK_GRAY + "                               [" + ChatColor.YELLOW + "NationCraft" + ChatColor.DARK_GRAY + "]");
        player.sendMessage(ChatColor.AQUA+"                                by "+ChatColor.GOLD+"aaomidi");
        player.sendMessage(split);
        player.sendMessage("                          Visit Our Website at");
        player.sendMessage(ChatColor.DARK_AQUA + "                             NationCraft.org");
        player.sendMessage(split);
        player.sendMessage("                  Want a Special Rank? Donate at");
        player.sendMessage(ChatColor.DARK_AQUA + "                       Donate.NationCraft.org");
        player.sendMessage(split);
    }
}
