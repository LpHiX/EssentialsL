package me.lphix.essentialsl;

import me.lphix.essentialsl.commands.AddPoints;
import me.lphix.essentialsl.commands.DatabaseCommand;
import me.lphix.essentialsl.listeners.ChatListener;
import me.lphix.essentialsl.listeners.PlayerJoinListener;
import me.lphix.essentialsl.sql.MySQL;
import me.lphix.essentialsl.sql.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class EssentialsL extends JavaPlugin {

    private static MySQL SQL;
    private static SQLGetter data;
    @Override
    public void onEnable() {
        //SQL stuff
        SQL = new MySQL();
        data = new SQLGetter(this);

        //Registering events/commands
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        this.getCommand("addpoints").setExecutor(new AddPoints(this));
        this.getCommand("database").setExecutor(new DatabaseCommand(this));

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

    public static MySQL getSQL() {return SQL;}
    public static SQLGetter getData() {return data;}
}
