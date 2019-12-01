package me.kevsal.minecraft.cnhardcore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class CommandRevive implements CommandExecutor {

    private final CNHC plugin;

    public CommandRevive(CNHC plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("cnhc.revive")) {
                //check if enough args
                if(args.length == 1) {
                    //enough args
                    if(plugin.getOnlinePlayerUsernames().contains(args[0])) {
                        //player is online
                        if(revivePlayer(Bukkit.getPlayer(args[0]))) {
                            p.sendMessage(plugin.getPrefix() + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("config.messages.revive-success"))));
                        } else {
                            p.sendMessage(plugin.getPrefix() + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("config.messages.revive-fail"))));
                        }
                    } else {
                        //player is offline
                        p.sendMessage(plugin.getPrefix() + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("config.messages.not-online"))));
                    }
                } else {
                    //not enough args
                    return false;
                }

            } else {
                p.sendMessage(plugin.getADM());
            }
        } else {
            //check if enough args
            if(args.length == 1) {
                //enough args
                if(plugin.getOnlinePlayerUsernames().contains(args[0])) {
                    //player is online
                    if(revivePlayer(Bukkit.getPlayer(args[0]))) {
                        sender.sendMessage(plugin.getPrefix() + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("config.messages.revive-success"))));
                    } else {
                        sender.sendMessage(plugin.getPrefix() + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("config.messages.revive-fail"))));
                    }
                } else {
                    //player is offline
                    sender.sendMessage(plugin.getPrefix() + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("config.messages.not-online"))));
                }
            } else {
                //not enough args
                return false;
            }
        }

        return true;

    }

    private boolean revivePlayer(Player p) {
        boolean allowRevive = plugin.getConfig().getBoolean("config.features.allow-revive");
        //actually revive the player
        if (allowRevive) {
            p.teleport(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation());
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("config.messages.revived"))));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " clear server=" + Objects.requireNonNull(plugin.getConfig().getString("config.death-options.server-context")));
            return true;
        } else {
            return false;
        }

    }
}
