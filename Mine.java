package me.s4wi.prisonplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Mine implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (args.length == 0) {

                player.sendMessage(ChatColor.RED + "Please provide atleast an argument");
            }

            if (args.length == 1) {

                if (args[0].equalsIgnoreCase("a")) {

                    World world = Bukkit.getWorld("a");
                    player.teleport(new Location(world, 242, 52, 1348));
                    player.sendMessage(ChatColor.GOLD + "Warping to " + ChatColor.RED + "mine a");
                    return true;
                }

                if (args[0].equalsIgnoreCase("b")) {

                    World world = Bukkit.getWorld("b");
                    player.teleport(new Location(world, 242, 52, 1348));
                    player.sendMessage(ChatColor.GOLD + "Warping to " + ChatColor.RED + "mine b");
                    return true;
                }

            }



        }


        return false;
    }
}
