package com.rexcantor64.triton.testsuite.commands;

import com.google.common.collect.Lists;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class TitleCMD implements CommandExecutor, TabCompleter {

    private List<String[]> messages = Lists.newArrayList();

    TitleCMD() {
        messages.add(new String[]{"[lang]title.test.0[/lang]", "[lang]title.sub.test.0[/lang]"});
        messages.add(new String[]{"[lang]title.test.1[/lang]", "[lang]title.sub.test.1[/lang]"});
        messages.add(new String[]{ChatColor.YELLOW + "This text [lang]title.test.2[/lang]", ""});
        messages.add(new String[]{"[lang]title.test.3[/lang] [lang]title.test.3a[/lang]", "[lang]title.sub.test.3[/lang]"});
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            s.sendMessage("/" + label + " title <id>");
            return true;
        }
        try {
            int id = Integer.parseInt(args[0]);
            if (id < messages.size()) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title @a title " + ComponentSerializer.toString(TextComponent.fromLegacyText(messages.get(id)[0])));
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title @a subtitle " + ComponentSerializer.toString(TextComponent.fromLegacyText(messages.get(id)[1])));

            } else
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
}
