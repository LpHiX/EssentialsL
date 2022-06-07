package me.lphix.essentialsl;

import me.lphix.essentialsl.commands.AddPoints;
import me.lphix.essentialsl.listeners.ChatListener;
import me.lphix.essentialsl.listeners.PlayerJoinListener;
import me.lphix.essentialsl.sql.MySQL;
import me.lphix.essentialsl.sql.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class EssentialsL extends JavaPlugin {

    public MySQL SQL;
    public SQLGetter data;
    @Override
    public void onEnable() {
        //Registering events/commands
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        this.getCommand("addpoints").setExecutor(new AddPoints(this));
        //SQL stuff
        this.SQL = new MySQL();
        this.data = new SQLGetter(this);
        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getLogger().info("Database is not connected");
        }
        if (SQL.isConnected()){
            Bukkit.getLogger().info("Database connected.");
            data.createTable();
        }
        Bukkit.getLogger().info("[EssentialsL] loaded!");
    }

    @Override
    public void onDisable() {
        SQL.disconnect();
        Bukkit.getLogger().info("[EssentialsL] disabled!");
    }
}
