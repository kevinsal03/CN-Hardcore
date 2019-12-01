package me.kevsal.minecraft.cnhardcore;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class ListenerPlayerJoin implements Listener {

    private final CNHC plugin;

    public ListenerPlayerJoin(CNHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        //handle player joins
        Player p = e.getPlayer();

        if (p.hasPermission("cnhc.bypass")) {
            p.sendMessage(plugin.getPrefix() + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("config.messages.bypass"))));
        } else {
            if (p.hasPermission("cnhc.dead")) {
                //player is dead
                p.sendMessage(plugin.getPrefix() + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("config.messages.death"))));
                p.setGameMode(GameMode.SPECTATOR);
                p.setFlying(true);
                p.setMaxHealth(1);
                p.setHealth(1);
            } else {
                p.setGameMode(GameMode.SURVIVAL);
            }
        }
    }
}
