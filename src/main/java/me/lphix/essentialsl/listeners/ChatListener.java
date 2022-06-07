package me.lphix.essentialsl.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.regex.Pattern;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncChatEvent e){
        Pattern itemExpression = Pattern.compile("\\[item\\]", Pattern.CASE_INSENSITIVE);
        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();

        if(item.getType() != Material.AIR) {
            HoverEvent<HoverEvent.ShowItem> itemHoverEvent = item.asHoverEvent();
            Component component = item.displayName().hoverEvent(itemHoverEvent);
            TextReplacementConfig replaceItem = TextReplacementConfig.builder().match(itemExpression).replacement(component).build();
            e.message(e.message().replaceText(replaceItem));
        } else{
            Component component = Component.text("[" + PlainTextComponentSerializer.plainText().serialize(e.getPlayer().displayName()) + "'s baby hands]");
            TextReplacementConfig handReplace = TextReplacementConfig.builder().match(itemExpression).replacement(component).build();
            e.message(e.message().replaceText(handReplace));
        }
    }
}
