package com.rexcantor64.triton.testsuite.commands;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.utility.MinecraftVersion;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.google.common.collect.Lists;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ActionbarCMD implements CommandExecutor, TabCompleter {

    private final List<String> messages = Lists.newArrayList();

    ActionbarCMD() {
        messages.add("[lang]actionbar.test.0[/lang]");
        messages.add("[lang]actionbar.test.1[/lang]");
        messages.add(ChatColor.GOLD + "Apply gold color: [lang]actionbar.test.2[/lang]");
        messages.add("[lang]actionbar.test.3[args][arg]&0P&1L&2A&3C&4E&5H&6O&7L&8D&9E&aR&b![/arg][/args][/lang]");
        messages.add("[lang]actionbar.test.4[args][arg]&0P&1L&2A&3C&4E&5H&6O&7L&8D&9E&aR&b![/arg][arg]&bA&cN&dO&eT&fH" +
                "&lE&aR[/arg][/args][/lang]");
        messages.add("&aThis is something that needs &bto &dbe &1 changed.");
        Random rand = new Random();
        messages.add("&7This is an actionbar with a random value &6" + rand.nextInt(100) + " &a woah!");
        messages.add("&4&mYOU SHOULDN'T SEE THIS! [lang]disabled.line[/lang]");
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            s.sendMessage("/" + label + " actionbar <id>");
            return true;
        }
        try {
            int id = Integer.parseInt(args[0]);
            if (id < messages.size())
                sendActionBarToEveryone(messages.get(id));
            else
                s.sendMessage("The maximum ID is " + (messages.size() - 1));
        } catch (NumberFormatException e) {
            s.sendMessage(args[0] + " is not a number!");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> ids = Lists.newArrayList();
        for (int i = 0; i < messages.size(); i++)
            ids.add(Integer.toString(i));
        return ids;
    }

    private void sendActionBarToEveryone(String msg) {
        if (MinecraftVersion.WILD_UPDATE.atOrAbove()) { // 1.19+
            Bukkit.getServer().getOnlinePlayers().forEach(p -> p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText(
                            ChatColor.translateAlternateColorCodes('&', msg))));
            return;
        }

        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.CHAT);
        if (MinecraftVersion.COLOR_UPDATE.atOrAbove()) // 1.12+
            packet.getChatTypes().writeSafely(0, EnumWrappers.ChatType.GAME_INFO);
        else
            packet.getBytes().writeSafely(0, (byte) 2);

        packet.getSpecificModifier(BaseComponent[].class).writeSafely(0, new BaseComponent[]{new TextComponent(ChatColor
                .translateAlternateColorCodes('§', msg))});
        packet.getUUIDs().writeSafely(0, new UUID(0L, 0L));

        try {
            for (Player p : Bukkit.getServer().getOnlinePlayers())
                ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
