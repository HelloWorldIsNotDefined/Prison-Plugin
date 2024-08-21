package me.s4wi.prisonplugin.listeners;

import me.s4wi.prisonplugin.Main;
import me.s4wi.prisonplugin.models.PlayerStats;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInventoryEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class Listeners implements Listener {

    private final Main plugin;

    public Listeners(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {

        Player player = event.getPlayer();
        String world_name = "";
        int dest_x = 0;
        int dest_y = 0;
        int dest_z = 0;

        // do the teleport
        World dest_world = Bukkit.getWorld(world_name);
        player.teleport(new Location(dest_world, dest_x, dest_y, dest_z));

    }

    @EventHandler
    public void onInventory(InventoryClickEvent event) {


        if (event.getInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "Warps!")) {

            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();

            switch (event.getCurrentItem().getType()) {

                case COBBLESTONE:
                    World world = Bukkit.getWorld("a");
                    player.teleport(new Location(world, 242, 52, 1348));
                    player.sendMessage(ChatColor.GOLD + "Warping to " + ChatColor.RED + "mine a");
                    break;

                case STONE:
                    World world2 = Bukkit.getWorld("b");
                    player.teleport(new Location(world2, 242, 52, 1348));
                    player.sendMessage(ChatColor.GOLD + "Warping to " + ChatColor.RED + "mine b");
                    break;
            }

        }

    }



    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();

        try {

            PlayerStats stats = playerStatsFromDatabase(player);
            stats.setBroken_blocks(stats.getBroken_blocks() + 1);

            if (event.getBlock().getType() == Material.DIAMOND_ORE) {

                stats.setBalance(stats.getBalance() + 50);
                player.sendMessage(ChatColor.GREEN + "You have recived $50");

            } else if (event.getBlock().getType() == Material.GOLD_ORE) {

                stats.setBalance(stats.getBalance() + 25);
                player.sendMessage(ChatColor.GREEN + "You have recived $25");

            } else if (event.getBlock().getType() == Material.IRON_ORE) {

                stats.setBalance(stats.getBalance() + 10);
                player.sendMessage(ChatColor.GREEN + "You have recived $10");
            }

            plugin.getMyDatabase().updatePlayerStats(stats);


        }
        catch (SQLException e) {

            e.printStackTrace();
        }



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
