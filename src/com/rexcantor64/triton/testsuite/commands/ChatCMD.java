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
import org.bukkit.entity.Player;

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
        addFromJson("{\"text\":\"\",\"extra\":[{\"text\":\"\\u003e \",\"color\":\"dark_aqua\"," +
                "\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/lp export \\u003cfile\\u003e\"}," +
                "\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Command: " +
                "\",\"color\":\"aqua\"},{\"text\":\"Export\\n\",\"color\":\"dark_green\"},{\"text\":\"Description: " +
                "\",\"color\":\"aqua\"},{\"text\":\"Exports all permissions data to an \\u0027export\\u0027 file. Can" +
                " be re-imported at a later time.\\n\",\"color\":\"dark_green\"},{\"text\":\"Usage: \"," +
                "\"color\":\"aqua\"},{\"text\":\"/lp export \\u003cfile\\u003e\\n\",\"color\":\"dark_green\"}," +
                "{\"text\":\"Permission: \",\"color\":\"aqua\"},{\"text\":\"luckperms.export\\n \\n\"," +
                "\"color\":\"dark_green\"},{\"text\":\"Click to auto-complete.\",\"color\":\"gray\"}]}}}," +
                "{\"text\":\"/lp export \\u003cfile\\u003e\",\"color\":\"green\"," +
                "\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/lp export \\u003cfile\\u003e\"}," +
                "\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Command: " +
                "\",\"color\":\"aqua\"},{\"text\":\"Export\\n\",\"color\":\"dark_green\"},{\"text\":\"Description: " +
                "\",\"color\":\"aqua\"},{\"text\":\"Exports all permissions data to an \\u0027export\\u0027 file. Can" +
                " be re-imported at a later time.\\n\",\"color\":\"dark_green\"},{\"text\":\"Usage: \"," +
                "\"color\":\"aqua\"},{\"text\":\"/lp export \\u003cfile\\u003e\\n\",\"color\":\"dark_green\"}," +
                "{\"text\":\"Permission: \",\"color\":\"aqua\"},{\"text\":\"luckperms.export\\n \\n\"," +
                "\"color\":\"dark_green\"},{\"text\":\"Click to auto-complete.\",\"color\":\"gray\"}]}}}]," +
                "\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/lp export \\u003cfile\\u003e\"}," +
                "\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Command: " +
                "\",\"color\":\"aqua\"},{\"text\":\"Export\\n\",\"color\":\"dark_green\"},{\"text\":\"Description: " +
                "\",\"color\":\"aqua\"},{\"text\":\"Exports all permissions data to an \\u0027export\\u0027 file. Can" +
                " be re-imported at a later time.\\n\",\"color\":\"dark_green\"},{\"text\":\"Usage: \"," +
                "\"color\":\"aqua\"},{\"text\":\"/lp export \\u003cfile\\u003e\\n\",\"color\":\"dark_green\"}," +
                "{\"text\":\"Permission: \",\"color\":\"aqua\"},{\"text\":\"luckperms.export\\n \\n\"," +
                "\"color\":\"dark_green\"},{\"text\":\"Click to auto-complete.\",\"color\":\"gray\"}]}}}");
        addFromLegacy(ChatColor.translateAlternateColorCodes('&', "&aTest &ltest &mtest"));
        addFromJson("{\"extra\":[{\"extra\":[{\"color\":\"gray\",\"text\":\"\"}]," +
                "\"hoverEvent\":{\"action\":\"show_text\",\"value\":[{\"extra\":[{\"color\":\"dark_gray\"," +
                "\"text\":\"*-----* \"},{\"color\":\"white\",\"text\":\"FabricSoftener \"},{\"color\":\"dark_gray\"," +
                "\"text\":\"*-----*\\n\"},{\"color\":\"gray\",\"text\":\"Prefix\"},{\"color\":\"dark_gray\"," +
                "\"text\":\": \"},{\"color\":\"gray\",\"text\":\"\\n\"},{\"color\":\"gray\",\"text\":\"Group\"}," +
                "{\"color\":\"dark_gray\",\"text\":\": \"},{\"color\":\"aqua\",\"text\":\"owner\"}],\"text\":\"\"}]}," +
                "\"text\":\"\"},{\"extra\":[{\"color\":\"gray\",\"text\":\"FabricSoftener\"},{\"color\":\"gray\"," +
                "\"text\":\": \"},{\"color\":\"white\",\"text\":\"\"}]," +
                "\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/msg FabricSoftener \"}," +
                "\"hoverEvent\":{\"action\":\"show_text\",\"value\":[{\"extra\":[{\"color\":\"gray\"," +
                "\"text\":\"Message issued: \"},{\"color\":\"aqua\",\"text\":\"14.03.2020 23:55:27\\n\"}," +
                "{\"color\":\"gray\",\"text\":\"Click to send a message\"}],\"text\":\"\"}]},\"text\":\"\"}," +
                "{\"extra\":[{\"color\":\"white\",\"text\":\"a\"}],\"text\":\"\"}],\"text\":\"\"}");
        addFromJson("{\"extra\":[{\"color\":\"aqua\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/msg " +
                "Over_Brave \"},\"hoverEvent\":{\"action\":\"show_text\"," +
                "\"value\":{\"extra\":[{\"text\":\"\u00A7c\u25A3 \u00A7dOver_Brave \u00A79\u00F6zel mesaj atmak " +
                "i\u00E7in t\u0131kla!\"}],\"text\":\"\"}},\"text\":\"\u00A7f\u00A74\u00A7lYETKILI\u00A74\u00A7l " +
                "\u00A74\u00A7l\u00A7r\u00A7cOver_Brave\u00A7c \u00A7c\u00A78\u00A7l\u00BB\u00A78\u00A7l " +
                "\u00A78\u00A7l\u00A7b\"},{\"color\":\"aqua\",\"text\":\"\u00A7ba\"}],\"text\":\"\"}");
        addFromLegacy(ChatColor.translateAlternateColorCodes('&', "&cSome red text [lang]json.test.0[/lang]"));
        addFromLegacy("[lang]json.test.1[args][arg]Special Mode[/arg][/args][/lang]");
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
            if (id < messages.size()) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    try {
                        p.spigot().sendMessage(messages.get(id));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
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

    private void addFromLegacy(String s) {
        messages.add(TextComponent.fromLegacyText(s));
    }

    private void addFromJson(String s) {
        messages.add(ComponentSerializer.parse(s));
    }
}
