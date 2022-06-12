package me.lphix.essentialsl.listeners;

import me.lphix.essentialsl.EssentialsL;
import me.lphix.essentialsl.sql.SQLGetter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    EssentialsL plugin;
    SQLGetter data;
    public PlayerJoinListener(EssentialsL plugin){
        this.plugin = plugin;
        this.data = plugin.getData();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        data.createPlayer(p);
    }
}
