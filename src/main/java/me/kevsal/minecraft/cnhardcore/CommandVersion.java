package me.kevsal.minecraft.cnhardcore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandVersion implements CommandExecutor {

    private final CNHC plugin;

    public CommandVersion (CNHC plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("cnhc.version")) {
                p.sendMessage("Version: " + plugin.getDescription().getVersion());
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config.messages.prefix")) + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config.messages.access-denied")));
            }
        } else {
            sender.sendMessage("Version: " + plugin.getDescription().getVersion());
        }

        return false;
    }
}
