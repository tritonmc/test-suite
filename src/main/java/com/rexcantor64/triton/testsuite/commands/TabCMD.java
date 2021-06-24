package com.rexcantor64.triton.testsuite.commands;

import com.rexcantor64.triton.testsuite.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class TabCMD implements CommandExecutor {

    private String header;
    private String footer;

    private final Class<?> chatSerializerClass;
    private final Class<?> chatComponentClass;
    private final Class<?> packetClass;

    TabCMD() throws ClassNotFoundException {
        header = "[lang]tab.test.header[/lang]";
        footer = "[lang]tab.test.footer[/lang]";

        if (ReflectionUtils.getMajorMcVersion() >= 17) {
            chatSerializerClass = ReflectionUtils.getClass("net.minecraft.network.chat.IChatBaseComponent$ChatSerializer");
            chatComponentClass = ReflectionUtils.getClass("net.minecraft.network.chat.IChatBaseComponent");
            packetClass = ReflectionUtils.getClass("net.minecraft.network.protocol.game.PacketPlayOutPlayerListHeaderFooter");
        } else {
            chatSerializerClass = ReflectionUtils.getNmsClass("IChatBaseComponent$ChatSerializer");
            chatComponentClass = null;
            packetClass = ReflectionUtils.getNmsClass("PacketPlayOutPlayerListHeaderFooter");
        }
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        try {
            Object headerObj = chatSerializerClass.getMethod("a", String.class).invoke(null, "{\"text\":\"" + header + "\"}");
            Object footerObj = chatSerializerClass.getMethod("a", String.class).invoke(null, "{\"text\":\"" + footer + "\"}");
            Object packet;
            if (ReflectionUtils.getMajorMcVersion() >= 17) {
                packet = packetClass.getConstructor(chatComponentClass, chatComponentClass).newInstance(headerObj, footerObj);
            } else {
                packet = packetClass.getConstructor().newInstance();
                Field headerField;
                try {
                    headerField = packet.getClass().getDeclaredField("a");
                } catch (NoSuchFieldException e) {
                    headerField = packet.getClass().getDeclaredField("header");
                }
                headerField.setAccessible(true);
                headerField.set(packet, headerObj);
                Field footerField;
                try {
                    footerField = packet.getClass().getDeclaredField("b");
                } catch (NoSuchFieldException e) {
                    footerField = packet.getClass().getDeclaredField("footer");
                }
                footerField.setAccessible(true);
                footerField.set(packet, footerObj);
            }
            for (Player p : Bukkit.getOnlinePlayers())
                ReflectionUtils.sendPacket(p, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bukkit.broadcastMessage("Updated player list header and footer for everyone!");
        return true;
    }

}
