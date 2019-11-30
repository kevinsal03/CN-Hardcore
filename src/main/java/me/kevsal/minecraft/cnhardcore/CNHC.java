package me.kevsal.minecraft.cnhardcore;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;

public class CNHC extends JavaPlugin {

    @Override
    public void onEnable() {

        //save default config
        this.saveDefaultConfig();

        //register commands
        Objects.requireNonNull(this.getCommand("hc-version")).setExecutor(new CommandVersion(this));
        Objects.requireNonNull(this.getCommand("hc-reload")).setExecutor(new CommandReload(this));
        Objects.requireNonNull(this.getCommand("hc-revive")).setExecutor(new CommandRevive(this));

        //register event
        getServer().getPluginManager().registerEvents(new ListenerPlayerDeath(this), this );


        //LuckPerms api
        // ive never used an external api like this so lots of copy and pasted code
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);

    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }

    //handle plugin reloads
    public void reloadPlugin() {
        //add more stuff as it becomes needed
        this.reloadConfig(); //reload new config
        this.saveDefaultConfig(); //save default if its missing
    }

    /* Common gets from config file for messages */
    //get plugin prefix
    public String getPrefix() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(this.getConfig().getString("config.messages.prefix")));
    }
    //get access denied message
    public String getADM() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(this.getConfig().getString("config.messages.prefix"))) + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(this.getConfig().getString("config.messages.access-denied")));
    }

    public ArrayList<String> getOnlinePlayerUsernames() {
        ArrayList<String> internal = null;
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            internal.add(p.getName());
        }
        return internal;
    }
}
