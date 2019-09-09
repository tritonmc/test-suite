package com.rexcantor64.triton.testsuite.commands;

import com.google.common.collect.Lists;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class ChatCMD implements CommandExecutor, TabCompleter {

    private List<BaseComponent[]> messages = Lists.newArrayList();

    ChatCMD() {
        addFromLegacy("[lang]chat.test.0[/lang]");
        addFromLegacy("[lang]chat.test.1[args][arg]&0P&1L&2A&3C&4E&5H&6O&7L&8D&9E&aR&b![/arg][/args][/lang]");
        addFromLegacy(ChatColor.translateAlternateColorCodes('&', "[lang]chat.test.1[args][arg]&0P&1L&2A&3C&4E&5H&6O" +
                "&7L&8D&9E&aR&b![/arg][/args][/lang]"));
        addFromLegacy("[lang]chat.test.3[args][arg]&0P&1L&2A&3C&4E&5H&6O&7L&8D&9E&aR&b![/arg][arg]&bA&cN&dO&eT&fH&lE" +
                "&aR[/arg][/args][/lang]");
        addFromLegacy(ChatColor.translateAlternateColorCodes('&', "[lang]chat.test.3[args][arg]&0P&1L&2A&3C&4E&5H&6O" +
                "&7L&8D&9E&aR&b![/arg][arg]&bA&cN&dO&eT&fH&lE&aR[/arg][/args][/lang]"));
        addFromLegacy(ChatColor.translateAlternateColorCodes('&', "some text[lang]chat.test" +
                ".3[args][arg]&0P&1L&2A&3C&4E&5H&6O&7L&8D&9E&aR&b![/arg][arg]&bA&cN&dO&eT&fH&lE&aR[/arg][/args][/lang" +
                "]more text"));
        addFromJson("{\"extra\":[{\"text\":\"\\u003cTestBot\\u003e [lang]\"}," +
                "{\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://chat.test.0[/lang]\"},\"text\":\"chat" +
                ".test.0[/lang]\"}],\"text\":\"\"}");
        addFromJson("[{\"text\":\"<TestBot> \"},{\"text\":\"Green Color: \",\"color\":\"green\"," +
                "\"extra\":[{\"text\":\"[lang]chat.test.7[/lang]\"}]}]");
        addFromJson("[\"\",{\"text\":\"<TestBot> \",\"hoverEvent\":{\"action\":\"show_text\"," +
                "\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"This is some blue text! [lang]chat.test.0[/lang]\"," +
                "\"color\":\"aqua\"}]}}},{\"text\":\"[lang]chat.test.0[/lang]\"}]");
        addFromLegacy("[lang]chat.test.0[/lang] some white text in the middle [lang]chat.test.0[/lang] and even more " +
                "text! [lang]chat.test.0[/lang]");
        addFromJson("{\"extra\":[{\"extra\":[{\"extra\":[{\"extra\":[{\"extra\":[{\"extra\":[{\"text\":\" \"}," +
                "{\"color\":\"gold\",\"text\":\"▎\"},{\"color\":\"yellow\",\"text\":\" Chat \"},{\"color\":\"gold\"," +
                "\"text\":\"► \"},{\"color\":\"gray\",\"text\":\"[\"},{\"color\":\"white\",\"italic\":true," +
                "\"text\":\"[lang]chc.me[/lang]\"},{\"color\":\"white\",\"text\":\" ► \"},{\"color\":\"white\"," +
                "\"italic\":true,\"text\":\"teszteer\"},{\"color\":\"gray\",\"text\":\"] \"},{\"color\":\"white\"," +
                "\"text\":\"as\"}],\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/msg teszteer \"}," +
                "\"hoverEvent\":{\"action\":\"show_text\",\"value\":[{\"extra\":[{\"extra\":[{\"text\":\"\"}," +
                "{\"text\":\"\"},{\"text\":\"\"},{\"text\":\"\"},{\"color\":\"yellow\",\"text\":\"Time\"}," +
                "{\"color\":\"yellow\",\"text\":\"\"},{\"color\":\"yellow\",\"text\":\"\"},{\"color\":\"yellow\"," +
                "\"text\":\": §f15.03.2018 18:40:24\\n\"}],\"text\":\"\"},{\"color\":\"yellow\"," +
                "\"extra\":[{\"color\":\"yellow\",\"text\":\"Receiver\"}],\"text\":\"\"},{\"color\":\"yellow\"," +
                "\"extra\":[{\"extra\":[{\"color\":\"yellow\",\"text\":\": §fteszteer\"}],\"text\":\"\"}]," +
                "\"text\":\"\"}],\"text\":\"\"}]},\"text\":\"\"}],\"text\":\"\"}],\"text\":\"\"}],\"text\":\"\"}]," +
                "\"text\":\"\"}],\"text\":\"\"}");
        addFromLegacy("[lang]chat.test.11[args][arg]&4[lang]chat.test.11.1[/lang]&a[/arg][/args][/lang]");
        addFromLegacy(ChatColor.translateAlternateColorCodes('&', "&7This won't be translated! &3But this will ;)"));
        addFromLegacy(ChatColor.translateAlternateColorCodes('&', "&3This also won't be translated! &5Get a freaking " +
                "&0P&1L&2A&3C&4E&5H&6O&7L&8D&9E&aR&b! &2This " +
                "also won't."));
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            s.sendMessage("/" + label + " chat <id>");
            return true;
        }
        if (args[0].equalsIgnoreCase("all")) {
            for (BaseComponent[] comp : messages)
                Bukkit.spigot().broadcast(comp);
            return true;
        }
        try {
            int id = Integer.parseInt(args[0]);
            if (id < messages.size())
                Bukkit.spigot().broadcast(messages.get(id));
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

    private void addFromLegacy(String s) {
        messages.add(TextComponent.fromLegacyText(s));
    }

    private void addFromJson(String s) {
        messages.add(ComponentSerializer.parse(s));
    }
}
