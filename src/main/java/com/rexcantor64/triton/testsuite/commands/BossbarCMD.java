package com.rexcantor64.triton.testsuite.commands;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BossbarCMD implements CommandExecutor {

    List<BossBar> bars = new ArrayList<>();

    BossbarCMD() {
        addBar("[lang]bossbars.test.0[/lang]", BarColor.BLUE, BarStyle.SOLID);
        addBar("[lang]bossbars.test.1[/lang]", BarColor.GREEN, BarStyle.SEGMENTED_6);
        addBar("[lang]bossbars.test.2[/lang]", BarColor.PINK, BarStyle.SEGMENTED_10);
        addBar("[lang]bossbars.test.3[/lang][lang]disabled.line[/lang]", BarColor.WHITE, BarStyle.SEGMENTED_12);
        addBar("Bossbar 123456", BarColor.YELLOW, BarStyle.SEGMENTED_20);
        addBar("Bossbar with placeholder hehehe", BarColor.RED, BarStyle.SOLID);
    }

    private void addBar(String title, BarColor color, BarStyle style) {
        BossBar bar = Bukkit.getServer().createBossBar(title, color, style);
        bar.setProgress(0.5);
        bar.setVisible(true);
        bars.add(bar);
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        for (Player p : Bukkit.getOnlinePlayers())
            for (BossBar bar : bars)
                bar.addPlayer(p);
        return true;
    }

}
