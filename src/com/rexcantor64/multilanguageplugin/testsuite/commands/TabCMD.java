package com.rexcantor64.multilanguageplugin.testsuite.commands;

import com.rexcantor64.multilanguageplugin.testsuite.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TabCMD implements CommandExecutor {

    private String header;
    private String footer;

    TabCMD() {
        header = "[lang]tab.test.header[/lang]";
        footer = "[lang]tab.test.footer[/lang]";
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        try {
            Object headerObj = ReflectionUtils.getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", String.class).invoke(null, "{\"text\":\"" + header + "\"}");
            Object footerObj = ReflectionUtils.getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", String.class).invoke(null, "{\"text\":\"" + footer + "\"}");
            Object packet = ReflectionUtils.getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor().newInstance();
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, headerObj);
            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footerObj);
            for (Player p : Bukkit.getOnlinePlayers())
                ReflectionUtils.sendPacket(p, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bukkit.broadcastMessage("Updated player list header and footer for everyone!");
        return true;
    }

}
