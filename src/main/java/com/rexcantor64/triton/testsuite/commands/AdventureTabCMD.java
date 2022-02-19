package com.rexcantor64.triton.testsuite.commands;

import com.rexcantor64.triton.testsuite.Main;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AdventureTabCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        Component header = Component.text("[lang]tab.test.header[/lang]");
        Component footer = Component.text("[lang]tab.test.footer[/lang]");

        BukkitAudiences.create(Main.get()).all().sendPlayerListHeaderAndFooter(header, footer);

        Bukkit.broadcastMessage("Updated player list header and footer for everyone using adventure!");
        return true;
    }


}
