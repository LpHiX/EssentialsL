package me.lphix.essentialsl.commands;

import me.lphix.essentialsl.EssentialsL;
import me.lphix.essentialsl.sql.SQLGetter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AddPoints implements CommandExecutor {
    EssentialsL plugin;
    SQLGetter data;
    public AddPoints(EssentialsL plugin){
        this.plugin = plugin;
        this.data = plugin.getData();
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(args.length != 1) return false;
        if(!data.exists(player.getUniqueId())) data.createPlayer(player);
        data.addPoints(player.getUniqueId(), Integer.parseInt(args[0]));
        player.sendMessage(Component.text("You have added " + args[0] + " to total " + data.getPoints(player.getUniqueId()) + " points.", NamedTextColor.GREEN));
        return true;
    }
}
