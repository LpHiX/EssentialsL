package me.lphix.essentialsl.listeners;

import me.lphix.essentialsl.EssentialsL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    EssentialsL plugin;
    public PlayerJoinListener(EssentialsL plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        plugin.data.createPlayer(p);
    }
}
