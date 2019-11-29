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
                p.sendMessage(plugin.getPrefix() + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config.messages.reload-warn")));
                plugin.reloadPlugin();
                sender.sendMessage(plugin.getPrefix() + ChatColor.RESET + "Reload Complete");
            } else {
                p.sendMessage(plugin.getADM());
            }
        } else {
            sender.sendMessage(plugin.getPrefix() + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config.messages.reload-warn")));
            plugin.reloadPlugin();
            sender.sendMessage(plugin.getPrefix() + ChatColor.RESET + "Reload Complete");
        }


        return true;
    }
}
