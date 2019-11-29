package me.kevsal.minecraft.cnhardcore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandReload implements CommandExecutor {

    private final CNHC plugin;

    public CommandReload(CNHC plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("cnhc.reload")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config.messages.prefix")) + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config.messages.reload-warn")));
                plugin.reloadPlugin();
                p.sendMessage("Reload Complete");
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config.messages.prefix")) + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config.messages.access-denied")));
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config.messages.prefix")) + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config.messages.reload-warn")));
            plugin.reloadPlugin();
            sender.sendMessage("Reload Complete");
        }


        return true;
    }
}
