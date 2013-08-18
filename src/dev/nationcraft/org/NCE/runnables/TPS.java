/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.nationcraft.org.NCE.runnables;

import dev.nationcraft.org.NCE.NCE;
import java.util.LinkedList;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author aa_om_000
 */
public class TPS extends BukkitRunnable {

    private final NCE _plugin;
    long sec;
    long currentSec;
    int ticks;
    int delay;
    public static double tps;

    public TPS(NCE plugin) {
        _plugin = plugin;
    }

    @Override
    public void run() {
        sec = (System.currentTimeMillis() / 1000);

        if (currentSec == sec) {
            ticks++;
        } else {
            currentSec = sec;
            tps = (tps == 0 ? ticks : ((tps + ticks) / 2));
            ticks = 0;
        }
    }
}


