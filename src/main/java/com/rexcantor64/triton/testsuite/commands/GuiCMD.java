package com.rexcantor64.triton.testsuite.commands;

import com.rexcantor64.triton.testsuite.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Random;

public class GuiCMD implements CommandExecutor {

    private String title;

    GuiCMD() {
        title = ChatColor.GREEN + "testgui[lang]gui.test[/lang]";
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        try {
            Random rand = new Random();
            Inventory inv = Bukkit.createInventory(null, 9, title);
            inv.addItem(
                    createItem(64, Material.STONE, "[lang]item.test.0[/lang]"),
                    createItem(64,
                            Material.DIAMOND_BLOCK, "[lang]item.test.1[/lang]", "[lang]item.test.1.lore.0[/lang]",
                            "[lang]item.test.1.lore.1[args][arg]placeholder[/arg][/args][/lang]"),
                    createItem(32,
                            Material.GOLD_BLOCK, "&bThis is an item", "&8This is an item lore " + rand.nextInt(999),
                            ChatColor.AQUA + "Not translated"),
                    createItem(16, Material.DIAMOND, "&aYou can't see me[lang]disabled.line[/lang]",
                            color("&aYou can see me"), color("&3But not me[lang]disabled.line[/lang]"), color("&7You " +
                                    "can also see me"))
            );
            for (Player p : Bukkit.getOnlinePlayers())
                p.openInventory(inv);
            if (args.length == 0)
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (Player p : Bukkit.getOnlinePlayers())
                            p.closeInventory();
                    }
                }.runTaskLater(Main.get(), 40);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    private ItemStack createItem(int amount, Material type, String name, String... lore) {
        ItemStack is = new ItemStack(type, amount);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(color(name));
        meta.setLore(Arrays.asList(lore));
        is.setItemMeta(meta);
        return is;
    }

}
