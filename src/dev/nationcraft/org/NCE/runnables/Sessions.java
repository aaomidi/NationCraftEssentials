/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.runnables;

import dev.nationcraft.org.NCE.NCE;
import dev.nationcraft.org.NCE.utils.NCEChat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author aa_om_000
 */
public class Sessions extends BukkitRunnable {

    private final NCE _plugin;

    public Sessions(NCE plugin) {
        _plugin = plugin;
    }

    @Override
    public void run() {
        try {
            URL url = new URL("http://status.mojang.com/check");

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String data = reader.readLine().replace("[", "").replace("]", "").replace("{", "").replace("}", "").replace("\"", "");
            String[] dataArray = data.split(",");

            for (int i = 0; i < dataArray.length; i++) {
                String[] ai = dataArray[i].split(":");
                if (ai[0].equalsIgnoreCase("minecraft.net")) {
                    checkStatus(ai);
                } else if (ai[0].equalsIgnoreCase("login.minecraft.net")) {
                    checkStatus(ai);
                } else if (ai[0].equalsIgnoreCase("session.minecraft.net")) {
                    checkStatus(ai);
                } else if (ai[0].equalsIgnoreCase("account.mojang.com")) {
                    checkStatus(ai);
                } else if (ai[0].equalsIgnoreCase("auth.mojang.com")) {
                    checkStatus(ai);
                } else if (ai[0].equalsIgnoreCase("skins.minecraft.net")) {
                    checkStatus(ai);
                } else if (ai[0].equalsIgnoreCase("authserver.mojang.com")) {
                    checkStatus(ai);
                }
            }

            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkStatus(String[] ai) {
        if (ai[1].equalsIgnoreCase("green")) {

            NCE.status = true;
        } else {
            NCE.status = false;

            informPlayers(ai[0]);
        }
    }

    private void informPlayers(String string) {
        String[] down=string.split(".");
        down[0].toLowerCase();
        NCEChat.broadcastPluginMessage("&6" + down + " is &4down&6! Remember! If you log out from the server you will not be able to log back in!!");

    }

}
