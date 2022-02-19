package com.rexcantor64.triton.testsuite.commands;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainCMD implements CommandExecutor, TabCompleter {

    private HashMap<String, CommandExecutor> subCommands = Maps.newHashMap();
    private HashMap<String, TabCompleter> subCompleters = Maps.newHashMap();

    public MainCMD() {
        setCommandAndCompleter("chat", new ChatCMD());
        setCommandAndCompleter("actionbar", new ActionbarCMD());
        setCommandAndCompleter("title", new TitleCMD());
        subCommands.put("adventure-tab", new AdventureTabCMD());
        subCommands.put("tab", new TabCMD());
        subCommands.put("gui", new GuiCMD());
        subCommands.put("kick", new KickCMD());
        subCommands.put("scoreboard", new ScoreboardCMD());
        subCommands.put("api", new ApiCMD());
        try {
            subCommands.put("bossbar", new BossbarCMD());
        } catch (NoClassDefFoundError ignore) {
        }
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (args.length > 0)
            for (Map.Entry<String, CommandExecutor> entry : subCommands.entrySet())
                if (entry.getKey().equalsIgnoreCase(args[0]))
                    return entry.getValue().onCommand(s, cmd, label, Arrays.copyOfRange(args, 1, args.length));
        s.sendMessage(ChatColor.GOLD + "Available sub commands:");
        s.sendMessage(ChatColor.GREEN + String.join(", ", subCommands.keySet()));
        return true;
    }

    private void setCommandAndCompleter(String name, Object commandAndCompleter) {
        subCommands.put(name, (CommandExecutor) commandAndCompleter);
        subCompleters.put(name, (TabCompleter) commandAndCompleter);
    }

    @Override
    public List<String> onTabComplete(CommandSender s, Command cmd, String label, String[] args) {
        if (args.length == 0)
            return new ArrayList<String>(subCommands.keySet());
        for (Map.Entry<String, TabCompleter> entry : subCompleters.entrySet())
            if (entry.getKey().equalsIgnoreCase(args[0]))
                return entry.getValue().onTabComplete(s, cmd, label, args);
        List<String> tab = Lists.newArrayList();
        if (args.length == 1)
            for (String c : subCommands.keySet())
                if (c.toLowerCase().startsWith(args[0].toLowerCase()))
                    tab.add(c);
        return tab;
    }
}
