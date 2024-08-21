package me.s4wi.prisonplugin.commands;

import com.avaje.ebean.RawSql;
import me.s4wi.prisonplugin.Main;
import me.s4wi.prisonplugin.models.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class BalanceCommand implements CommandExecutor {

    private final Main plugin;

    public BalanceCommand(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {




        if (sender instanceof Player) {

            Player player = (Player) sender;
            String uuid = player.getUniqueId().toString();

            try {

                PlayerStats stats = getPlayerStatsFromDatabase(player);
                double balance = stats.getBalance();

                player.sendMessage(ChatColor.GOLD + "Your balance is $" + stats.getBalance());


            } catch (Exception e) {

                e.printStackTrace();
            }

            return true;
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
