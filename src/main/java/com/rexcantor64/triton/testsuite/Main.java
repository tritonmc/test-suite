package com.rexcantor64.triton.testsuite;

import com.rexcantor64.triton.api.events.PlayerChangeLanguageSpigotEvent;
import com.rexcantor64.triton.testsuite.commands.MainCMD;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerChatPreviewEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements Listener {

    private static Main instance;

    public static Main get() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        MainCMD cmd = new MainCMD();
        getCommand("testsuite").setExecutor(cmd);
        getCommand("testsuite").setTabCompleter(cmd);
        getServer().getPluginManager().registerEvents(this, this);

        // Start signs
        List<String> list = getConfig().getStringList("signs");
        List<Location> loc = new ArrayList<>();
        for (String s : list) {
            String[] ss = s.split(",");
            loc.add(new Location(Bukkit.getWorld(ss[0]), Integer.parseInt(ss[1]), Integer.parseInt(ss[2]), Integer
                    .parseInt(ss[3])));
        }
        new SignManager(loc);
    }

    @EventHandler
    public void onLanguageChange(PlayerChangeLanguageSpigotEvent e) {
        if (e.getNewLanguage() != e.getOldLanguage())
            System.out.println("[Test Suite] Player " + e.getLanguagePlayer()
                    .getUUID() + " has changed their language from " + e.getOldLanguage().getName() + " to " + e
                    .getNewLanguage().getName());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage("[lang]chat.test.0[/lang]");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
    }

    @EventHandler
    public void onChatPreview(AsyncPlayerChatPreviewEvent e) {
        e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
    }

}
