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
        if (isFolia()) {
            startSignUpdaterFolia();
        } else {
            startSignUpdaterBukkit();
        }
    }

    private void startSignUpdaterBukkit() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.get(), () -> {
            for (Location location : signs) {
                updateSign(location);
            }
        }, 1L, 100L);
    }

    private void startSignUpdaterFolia() {
        for (Location location : signs) {
            Bukkit.getRegionScheduler().runAtFixedRate(Main.get(), location, (task) -> updateSign(location), 1L, 100L);
        }
    }

    private void updateSign(Location location) {
        if (!location.getBlock().getType().name().toUpperCase().contains("SIGN")) return;
        Sign sign = (Sign) location.getBlock().getState();
        sign.setLine(0, "Triton test");
        sign.setLine(1, "sign :D");
        sign.setLine(2, random.nextBoolean() ? ChatColor.RED + "OFFLINE" : ChatColor.GREEN + "ONLINE");
        sign.setLine(3, random.nextInt(20) + "/20 players");
        sign.update(true);
    }

    private boolean isFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
