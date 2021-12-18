package com.rexcantor64.triton.testsuite.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TabCMD implements CommandExecutor {

    private String header;
    private String footer;

    TabCMD() {
        header = "[lang]tab.test.header[/lang]";
        footer = "[lang]tab.test.footer[/lang]";
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        for (Player p : Bukkit.getOnlinePlayers())
            p.setPlayerListHeaderFooter(header, footer);
        Bukkit.broadcastMessage("Updated player list header and footer for everyone!");
        return true;
    }

}
