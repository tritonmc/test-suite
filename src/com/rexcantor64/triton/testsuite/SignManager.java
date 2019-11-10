package com.rexcantor64.triton.testsuite;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;

import java.util.List;
import java.util.Random;

public class SignManager {

    private final Random random = new Random();
    private List<Location> signs;

    public SignManager(List<Location> signs) {
        this.signs = signs;
        startSignUpdater();
    }

    private void startSignUpdater() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.get(), () -> {
            for (Location l : signs) {
                if (!l.getBlock().getType().name().toUpperCase().contains("SIGN")) continue;
                Sign sign = (Sign) l.getBlock().getState();
                sign.setLine(0, "Triton test");
                sign.setLine(1, "sign :D");
                sign.setLine(2, random.nextBoolean() ? ChatColor.RED + "OFFLINE" : ChatColor.GREEN + "ONLINE");
                sign.setLine(3, random.nextInt(20) + "/20 players");
                sign.update(true);
            }
        }, 0L, 100L);
    }
}
