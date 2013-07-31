/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE;

import dev.nationcraft.org.NCE.utils.Enabler;
import dev.nationcraft.org.NCE.utils.NCEChat;
import dev.nationcraft.org.NCE.utils.YAMLManager;
import java.util.ArrayList;
import java.util.HashMap;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author aa_om_000
 */
public class NCE extends JavaPlugin {

    public static ArrayList<Player> CrashList = new ArrayList<>();
    public static NCE plugin;
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;

    //public static YAMLManager Config;
    @Override
    public void onDisable() {
        NCEChat.disableMessage();

    }

    @Override
    public void onEnable() {
        NCEChat.enableMessage();
        new Enabler(this);

    }

}
