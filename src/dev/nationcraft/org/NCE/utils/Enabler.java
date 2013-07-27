/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.utils;

import dev.nationcraft.org.NCE.NCE;
import dev.nationcraft.org.NCE.commands.commandCrash;
import dev.nationcraft.org.NCE.events.onJoinEvent;
import org.bukkit.command.CommandExecutor;

/**
 *
 * @author aa_om_000
 */
public class Enabler {

    private final NCE _plugin;

    public Enabler(NCE plugin) {
        _plugin = plugin;
        registerConfigs();
        registerCommands();
        registerListeners();
        registerUtils();

    }

    private void registerConfigs() {
        NCE.Config = new YAMLManager(_plugin, "config.yml");
        NCE.Config.getConfig().options().header(
                "===================================================\n"
                + "=                   NCE Config                    =\n"
                + "=                   Version 1.0                   =\n"
                + "===================================================\n");
        NCE.Config.saveConfig();
    }

    private void registerListeners() {
        new onJoinEvent(_plugin);
    }

    private void registerCommands() {

        _plugin.getCommand("crash").setExecutor(new commandCrash(_plugin));
        _plugin.getCommand("warn").setExecutor(new commandCrash(_plugin));
        _plugin.getCommand("opme").setExecutor(new commandCrash(_plugin));
    }

    private void registerUtils() {
        new NCEChat(_plugin);
    }
}
