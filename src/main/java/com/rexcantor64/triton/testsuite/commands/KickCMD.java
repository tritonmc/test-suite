package com.rexcantor64.triton.testsuite.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            s.sendMessage("/" + label + " kick <message>");
            return true;
        }
        String msg = String.join(" ", args);
        for (Player p : Bukkit.getOnlinePlayers())
            p.kickPlayer(msg);
        return true;
    }

}
