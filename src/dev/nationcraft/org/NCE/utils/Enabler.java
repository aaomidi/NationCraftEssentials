/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.utils;

import dev.nationcraft.org.NCE.NCE;
import dev.nationcraft.org.NCE.commands.commandClear;
import dev.nationcraft.org.NCE.commands.commandCrash;
import dev.nationcraft.org.NCE.commands.commandFakeOp;
import dev.nationcraft.org.NCE.commands.commandHappyHour;
import dev.nationcraft.org.NCE.commands.commandOpme;
import dev.nationcraft.org.NCE.commands.commandWarn;
import dev.nationcraft.org.NCE.events.Chat;
import dev.nationcraft.org.NCE.events.Connection;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 *
 * @author aa_om_000
 */
public class Enabler {

    private final NCE _plugin;
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;

    public Enabler(NCE plugin) {
        _plugin = plugin;
        registerConfigs();
        registerCommands();
        registerListeners();
        registerUtils();
        setupEconomy();
        setupChat();
        setupPermissions();

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
        new Connection(_plugin);
        new Chat(_plugin);
    }

    private void registerCommands() {

        _plugin.getCommand("crash").setExecutor(new commandCrash(_plugin));
        _plugin.getCommand("warn").setExecutor(new commandWarn(_plugin));
        _plugin.getCommand("opme").setExecutor(new commandOpme(_plugin));
        _plugin.getCommand("fakeop").setExecutor(new commandFakeOp(_plugin));
        _plugin.getCommand("clear").setExecutor(new commandClear(_plugin));
        _plugin.getCommand("happyhour").setExecutor(new commandHappyHour(_plugin));
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
}
