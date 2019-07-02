package com.rexcantor64.triton.testsuite;

import com.rexcantor64.triton.testsuite.commands.MainCMD;
import me.tigerhix.lib.scoreboard.ScoreboardLib;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        MainCMD cmd = new MainCMD();
        getCommand("testsuite").setExecutor(cmd);
        getCommand("testsuite").setTabCompleter(cmd);
    }

    public static Main get() {
        return instance;
    }

}
