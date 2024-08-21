package me.s4wi.prisonplugin.models;

public class PlayerStats {

    private String uuid;
    private double balance;

    public PlayerStats(String uuid, double balance, long broken_blocks) {
        this.uuid = uuid;
        this.balance = balance;
        this.broken_blocks = broken_blocks;
    }

    private long broken_blocks;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getBroken_blocks() {
        return broken_blocks;
    }

    public void setBroken_blocks(long broken_blocks) {
        this.broken_blocks = broken_blocks;
    }
}
