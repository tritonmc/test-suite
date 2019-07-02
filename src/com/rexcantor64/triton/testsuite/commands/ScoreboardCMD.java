package com.rexcantor64.triton.testsuite.commands;

import me.tigerhix.lib.scoreboard.ScoreboardLib;
import me.tigerhix.lib.scoreboard.common.EntryBuilder;
import me.tigerhix.lib.scoreboard.common.Strings;
import me.tigerhix.lib.scoreboard.common.animate.HighlightedString;
import me.tigerhix.lib.scoreboard.common.animate.ScrollableString;
import me.tigerhix.lib.scoreboard.type.Entry;
import me.tigerhix.lib.scoreboard.type.Scoreboard;
import me.tigerhix.lib.scoreboard.type.ScoreboardHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ScoreboardCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Scoreboard scoreboard = ScoreboardLib.createScoreboard(player)
                    .setHandler(new ScoreboardHandler() {

                        private final ScrollableString scroll = new ScrollableString(Strings.format("&aThis string is scrollable!"), 40, 0);
                        private final HighlightedString highlighted = new HighlightedString("This string is highlighted!", "&6", "&e");

                        @Override
                        public String getTitle(Player player) {
                            return null;
                        }

                        @Override
                        public List<Entry> getEntries(Player player) {
                            return new EntryBuilder()
                                    .next("    " + scroll.next())
                                    .next("    " + highlighted.next())
                                    .blank()
                                    .next("    &b&lCURRENT TIME MILLIS")
                                    .next("    " + System.currentTimeMillis())
                                    .blank()
                                    .next("    &c&lCURRENT NANO TIME")
                                    .next("    " + System.nanoTime())
                                    .blank()
                                    .next("    &7This line is equivalent to another line")
                                    .next("    &7This line is equivalent to another line")
                                    .blank()
                                    .build();
                        }

                    })
                    .setUpdateInterval(20L);
            scoreboard.activate();
        }
        return true;
    }

}