package me.s4wi.prisonplugin.commands;

import me.s4wi.prisonplugin.Main;
import me.s4wi.prisonplugin.models.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class SellCommand implements CommandExecutor {

    private final Main plugin;

    public SellCommand(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            try {

                Player player = (Player) sender;
                PlayerStats stats = getPlayerStatsFromDatabase(player);

                if (args.length == 0) {

                    player.sendMessage(ChatColor.RED + "Please provide atleast an argument");
                }

                if (args.length == 1) {

                    if (args[0].equalsIgnoreCase("all")) {

                        int totall = 0;
                        double bal = 0;

                        for (int i = 0; i < 36; i++) {

                            if (player.getInventory().getItem(i) != null) {

                                ItemStack item = player.getInventory().getItem(i);
                                int amount = item.getAmount();

                                if (item != null) {

                                    if (item.getType() == Material.DIAMOND_ORE) {

                                        bal = bal + 10 * amount;
                                        totall = totall + amount;
                                        player.getInventory().setItem(i, null);
                                        stats.setBalance(stats.getBalance() + 10 * amount);

                                    } else if (item.getType() == Material.IRON_ORE) {

                                        bal = bal + 10 * amount;
                                        totall = totall + amount;
                                        player.getInventory().setItem(i, null);
                                        stats.setBalance(stats.getBalance() + 2 * amount);

                                    } else if (item.getType() == Material.GOLD_ORE) {

                                        bal = bal + 10 * amount;
                                        totall = totall + amount;
                                        player.getInventory().setItem(i, null);
                                        stats.setBalance(stats.getBalance() + 5 * amount);

                                    }


                                    this.plugin.getMyDatabase().updatePlayerStats(stats);

                                }

                            }

                        }

                        if (totall != 0) {

                            player.sendMessage(ChatColor.GREEN + "" + totall + " items sold and you earned $" + bal);
                        } else {

                            player.sendMessage(ChatColor.GOLD + "There is nothing for sale");
                        }
                        return true;

                    } else {

                        player.sendMessage(ChatColor.RED + "/sell all");
                    }

                }

            } catch (SQLException e) {

                e.printStackTrace();
            }

        }

        return true;
    }

    private PlayerStats getPlayerStatsFromDatabase(Player player) throws SQLException {

        String uuid = player.getUniqueId().toString();
        PlayerStats stats = this.plugin.getMyDatabase().readPlayerStats(uuid);

        if (stats == null) {

            stats = new PlayerStats(uuid, 500, 0);
            this.plugin.getMyDatabase().createPlayerStats(stats);

            return stats;
        }

        return stats;
    }


}
