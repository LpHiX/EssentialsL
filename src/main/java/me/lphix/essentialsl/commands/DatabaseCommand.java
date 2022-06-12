package me.lphix.essentialsl.commands;

import me.lphix.essentialsl.EssentialsL;
import me.lphix.essentialsl.sql.MySQL;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.Objects;

public class DatabaseCommand implements CommandExecutor {
    EssentialsL plugin;
    MySQL sql;

    public DatabaseCommand(EssentialsL plugin) {
        this.plugin = plugin;
        this.sql = plugin.getSQL();
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("essentialsl.admin")){
            sender.sendMessage(Component.text("You do not have permission to use this command", NamedTextColor.RED));
            return true;
        }
        if(args.length != 1){
            sendUsage(sender);
            return  true;
        }
        switch (args[0]){
            case "connect":
                if(sql.isConnected()){
                    sender.sendMessage(Component.text("Database is already connected", NamedTextColor.RED));
                } else{
                    try {
                        sql.connect();
                        sender.sendMessage(Component.text("Database has been connected", NamedTextColor.GREEN));
                    } catch (ClassNotFoundException | SQLException e) {
                        sender.sendMessage(Component.text("Database could not be connected", NamedTextColor.RED));
                        e.printStackTrace();
                    }
                }
                return true;
            case "disconnect":
                if(sql.isConnected()){
                    if(sql.disconnect()){
                        sender.sendMessage(Component.text("Database has been disconnected", NamedTextColor.GREEN));
                    } else{
                        sender.sendMessage(Component.text("Database failed to disconnected", NamedTextColor.RED));
                    }
                } else{
                    sender.sendMessage(Component.text("Database is not connected", NamedTextColor.RED));
                }
                return true;
            case "status":
                Component message = sql.isConnected() ? Component.text("Database is connected", NamedTextColor.GREEN) : Component.text("No connection", NamedTextColor.RED);
                sender.sendMessage(message);
                return true;
            default:
                sendUsage(sender);
                return true;
        }
    }

    private void sendUsage(CommandSender sender){
        sender.sendMessage(Component.text("Usage: \n" +
                "/database connect\n" +
                "/database disconnect", NamedTextColor.RED));
    }
}
