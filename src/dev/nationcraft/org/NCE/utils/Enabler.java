/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.utils;

import dev.nationcraft.org.NCE.runnables.Sessions;
import dev.nationcraft.org.NCE.NCE;
import dev.nationcraft.org.NCE.commands.command8Ball;
import dev.nationcraft.org.NCE.commands.commandClear;
import dev.nationcraft.org.NCE.commands.commandCrash;
import dev.nationcraft.org.NCE.commands.commandFakeOp;
import dev.nationcraft.org.NCE.commands.commandFlirt;
import dev.nationcraft.org.NCE.commands.commandHappyHour;
import dev.nationcraft.org.NCE.commands.commandOpme;
import dev.nationcraft.org.NCE.commands.commandPing;
import dev.nationcraft.org.NCE.commands.commandTicket;
import dev.nationcraft.org.NCE.commands.commandWarn;
import dev.nationcraft.org.NCE.events.Chat;
import dev.nationcraft.org.NCE.events.LeaveJoin;
import dev.nationcraft.org.NCE.runnables.TPS;
import java.util.ArrayList;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scheduler.BukkitTask;
import dev.nationcraft.org.NCE.utils.Enabler;

/**
 *
 * @author aa_om_000
 */
public class Enabler {

    private final NCE _plugin;
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;
    public static String servername = Bukkit.getServerName();
    public MySQL con = null;
    public static ArrayList<String> flirtMsgs = new ArrayList<>();
    public static ArrayList<String> yesMsgs = new ArrayList<>();
    public static ArrayList<String> noMsgs = new ArrayList<>();
    public static ArrayList<String> nutMsgs = new ArrayList<>();

    public Enabler(NCE plugin) {
        _plugin = plugin;
        registerConfigs();
        registerCommands();
        registerListeners();
        registerUtils();
        setupEconomy();
        //setupChat();
        setupPermissions();
        setupSQL();
        registerMsgs();
        setupRunnables();

    }

    private void registerConfigs() {
        //  NCE.Config = new YAMLManager(_plugin, "config.yml");
        //  NCE.Config.getConfig().options().header(
        //     "===================================================\n"
        //     + "=                   NCE Config                    =\n"
        //     + "=                   Version 1.0                   =\n"
        //     + "===================================================\n");
        // NCE.Config.saveConfig();
        _plugin.saveDefaultConfig();
    }

    private void registerListeners() {
        new LeaveJoin(_plugin);
        new Chat(_plugin);

    }

    private void registerCommands() {

        _plugin.getCommand("crash").setExecutor(new commandCrash(_plugin));
        _plugin.getCommand("warn").setExecutor(new commandWarn(_plugin));
        _plugin.getCommand("opme").setExecutor(new commandOpme(_plugin));
        _plugin.getCommand("fakeop").setExecutor(new commandFakeOp(_plugin));
        _plugin.getCommand("clear").setExecutor(new commandClear(_plugin));
        _plugin.getCommand("happyhour").setExecutor(new commandHappyHour(_plugin));
        _plugin.getCommand("ticket").setExecutor(new commandTicket(_plugin));
        _plugin.getCommand("ping").setExecutor(new commandPing(_plugin));
        _plugin.getCommand("8ball").setExecutor(new command8Ball(_plugin));
        _plugin.getCommand("flirt").setExecutor(new commandFlirt(_plugin));
    }

    private void registerUtils() {

        new NCEChat(_plugin);

    }

    private boolean setupEconomy() {
        if (_plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = _plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = _plugin.getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = _plugin.getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    private void setupSQL() {
        String host = _plugin.getConfig().getString("MySQL.Host");
        //String port = _plugin.getConfig().getString("MySQL.port");
        String db = _plugin.getConfig().getString("MySQL.Database");
        String username = _plugin.getConfig().getString("MySQL.Username");
        String password = _plugin.getConfig().getString("MySQL.Password");
        con = new MySQL(_plugin);
        if (con.connect(host, db, username, password) == false) {
            _plugin.getServer().getPluginManager().disablePlugin(_plugin);
            NCEChat.LogSevere("Plugin HAS been disabled due to no MySQL connection!");
            return;

        } else {
            //be happy
        }
    }

    private void registerMsgs() {
        flirtMsgs.addAll(_plugin.getConfig().getStringList("Flirts"));
        yesMsgs.addAll(_plugin.getConfig().getStringList("YesMsgs"));
        noMsgs.addAll(_plugin.getConfig().getStringList("NoMsgs"));
        nutMsgs.addAll(_plugin.getConfig().getStringList("NeutralMsgs"));
    }

    private void setupRunnables() {
        BukkitTask sessionChecker = new Sessions(_plugin).runTaskTimerAsynchronously(_plugin, 100, 6000);
        BukkitTask tpsChecker = new TPS(_plugin).runTaskTimerAsynchronously(_plugin, 20, 1);
    }
}
