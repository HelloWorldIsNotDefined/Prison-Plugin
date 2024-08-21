package me.s4wi.prisonplugin.db;

import me.s4wi.prisonplugin.models.PlayerStats;

import java.sql.*;

public class MyDatabase {

    private Connection connection;

    public Connection getConnection() throws SQLException {

        if (connection != null) {
            return this.connection;
        }

        String url = "jdbc:mysql://localhost:3306/prison_test?characterEncoding=utf8";
        String user = "root";
        String password = "";
        String GREEN = "\u001B[32m";
        String WHITE = "\u001B[37m";

        this.connection = DriverManager.getConnection(url, user, password);

        System.out.println(GREEN + "Connected to the database successfully " + WHITE);
        return this.connection;

    }

    public void initializeDatabase() throws SQLException {

        Statement statement = connection.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS player_stats(uuid varchar(36) primary key, balance double, broken_blocks long)";
        String GREEN = "\u001B[32m";
        String WHITE = "\u001B[37m";

        statement.execute(sql);
        statement.close();

        System.out.println(GREEN + "The player_table created successfully " + WHITE);

    }

    public void createPlayerStats(PlayerStats stats) throws SQLException {

        String sql = "INSERT INTO player_stats(uuid, balance, broken_blocks) VALUES(?, ?, ?)";
        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setString(1, stats.getUuid());
        statement.setDouble(2, stats.getBalance());
        statement.setLong(3, stats.getBroken_blocks());

        statement.executeUpdate();
        statement.close();

    }

    public void updatePlayerStats(PlayerStats stats) throws SQLException {

        String sql = "UPDATE player_stats SET balance = ?, broken_blocks = ? WHERE uuid = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setDouble(1, stats.getBalance());
        statement.setLong(2, stats.getBroken_blocks());
        statement.setString(3, stats.getUuid());

        statement.executeUpdate();
        statement.close();

    }

    public PlayerStats readPlayerStats(String uuid) throws SQLException {

        String sql = "SELECT * FROM player_stats WHERE uuid = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setString(1, uuid);
        ResultSet results = statement.executeQuery();

        if (results.next()) {
            double balance = results.getDouble("balance");
            long brokenBlocks = results.getLong("broken_blocks");

            PlayerStats playerStats = new PlayerStats(uuid, balance, brokenBlocks);

            statement.close();

            return playerStats;
        }

        statement.close();
        return null;

    }



}
