package me.s4wi.prisonplugin.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Warps implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("warp")) {

                if (args.length == 0) {

                    player.sendMessage(ChatColor.RED + "Please atleast provide an argument");
                }

                if (args.length == 1) {

                    if (args[0].equalsIgnoreCase("pvp")) {

                        World world = Bukkit.getWorld("arena pvp");
                        player.teleport(new Location(world, 171, 21 ,-1276));
                        player.sendMessage(ChatColor.AQUA + "You telported to pvp area");

                    }


                }

            }


            if (command.getName().equalsIgnoreCase("warps")) {

                Inventory inventory = Bukkit.createInventory(player, 9, ChatColor.RED + "Warps!");

                ItemStack cobbleStone = new ItemStack(Material.COBBLESTONE);
                ItemStack stone = new ItemStack(Material.STONE);

                ItemMeta cobbleStoneMeta = cobbleStone.getItemMeta();
                ItemMeta stoneMeta = stone.getItemMeta();

                cobbleStoneMeta.setDisplayName(ChatColor.WHITE + "warp a");
                stoneMeta.setDisplayName(ChatColor.WHITE + "warp b");

                List<String> cobbleStoneLore = new ArrayList<>();
                List<String> stoneLore = new ArrayList<>();

                cobbleStoneLore.add(ChatColor.RED +"Click " + ChatColor.DARK_GRAY + "or /mine a");
                stoneLore.add(ChatColor.RED +"Click " + ChatColor.DARK_GRAY + "or /mine b");



                cobbleStoneMeta.setLore(cobbleStoneLore);
                stoneMeta.setLore(stoneLore);

                cobbleStone.setItemMeta(cobbleStoneMeta);
                stone.setItemMeta(stoneMeta);


                inventory.setItem(0, cobbleStone);
                inventory.setItem(1, stone);

                player.openInventory(inventory);

                return true;
            }

            if (command.getName().equalsIgnoreCase("spawn")) {

                World world = Bukkit.getWorld("world");
                player.teleport(new Location(world, 14, 63, 286));
                player.sendMessage(ChatColor.GOLD + "Warping to " + ChatColor.RED + "spawn");

                return true;
            }


        }

        return false;
    }
}
