package me.s4wi.prisonplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealthsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("feed")) {

            player.setFoodLevel(20);
            player.sendMessage(ChatColor.GOLD + "Your hunger bar is full");
        }

        if (command.getName().equalsIgnoreCase("kill")) {

            player.setHealth(0);
            player.sendMessage(ChatColor.RED + "You died");
        }

        return false;
    }
}
