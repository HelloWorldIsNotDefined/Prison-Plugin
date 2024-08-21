package me.s4wi.prisonplugin;

import me.s4wi.prisonplugin.commands.*;
import me.s4wi.prisonplugin.db.MyDatabase;
import me.s4wi.prisonplugin.listeners.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class Main extends JavaPlugin {

    private MyDatabase database;

    public MyDatabase getMyDatabase() {
        return database;
    }

    @Override
    public void onEnable() {

        String GREEN = "\u001B[32m";
        String WHITE = "\u001B[37m";
        String RED = "\u001B[31m";

        System.out.print(GREEN + "The Prison Plugin activated" + WHITE);

        getCommand("kill").setExecutor(new HealthsCommand());
        getCommand("feed").setExecutor(new HealthsCommand());
        getCommand("spawn").setExecutor(new Warps());
        getCommand("mine").setExecutor(new Mine());
        getCommand("warps").setExecutor(new Warps());
        getCommand("warp").setExecutor(new Warps());


        getServer().createWorld(new WorldCreator("a"));
        getServer().createWorld(new WorldCreator("b"));
        getServer().createWorld(new WorldCreator("arena pvp"));


        getCommand("sell").setExecutor(new SellCommand(this));
        getCommand("balance").setExecutor(new BalanceCommand(this));
        getServer().getPluginManager().registerEvents(new Listeners(this), this);

        try {
            database = new MyDatabase();
            database.getConnection();
            database.initializeDatabase();

        } catch (SQLException e) {

            System.out.println(RED + "Unable to create database or table" + WHITE);
            e.printStackTrace();
        }

    }
}
