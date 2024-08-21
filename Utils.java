package me.s4wi.prisonplugin;

import me.s4wi.prisonplugin.models.PlayerStats;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Utils {

    private final Main plugin;

    public Utils(Main plugin) {
        this.plugin = plugin;
    }

    public Main getPlugin() {
        return plugin;
    }

    private PlayerStats playerStatsFromDatabase(Player player) throws SQLException {

        String uuid = player.getUniqueId().toString();
        PlayerStats stats = plugin.getMyDatabase().readPlayerStats(uuid);

        if (stats == null) {

            stats = new PlayerStats(uuid, 500, 0);
            plugin.getMyDatabase().createPlayerStats(stats);

            return stats;
        }
        return stats;


    }


}
