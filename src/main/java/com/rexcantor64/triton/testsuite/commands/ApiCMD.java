package com.rexcantor64.triton.testsuite.commands;

import com.rexcantor64.triton.api.TritonAPI;
import com.rexcantor64.triton.api.language.Language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ApiCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (!(s instanceof Player))
            return true;
        Language l = TritonAPI
                .getInstance()
                .getPlayerManager()
                .get(((Player) s).getUniqueId())
                .getLang();
        s.sendMessage(l.getDisplayName());
        return true;
    }

}
