package me.lphix.essentialsl.commands;

import me.lphix.essentialsl.EssentialsL;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AddPoints implements CommandExecutor {
    EssentialsL plugin;
    public AddPoints(EssentialsL plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(args.length != 1) return false;
        if(!plugin.data.exists(player.getUniqueId())) plugin.data.createPlayer(player);
        plugin.data.addPoints(player.getUniqueId(), Integer.parseInt(args[0]));
        player.sendMessage(Component.text("You have added " + args[0] + " to total " + plugin.data.getPoints(player.getUniqueId()) + " points.", NamedTextColor.GREEN));
        return true;
    }
}
