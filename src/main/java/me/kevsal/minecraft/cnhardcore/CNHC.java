package me.kevsal.minecraft.cnhardcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CNHC extends JavaPlugin {

    @Override
    public void onEnable() {

        //save default config
        this.saveDefaultConfig();

        //register commands
        this.getCommand("hc-version").setExecutor(new CommandVersion(this));
        this.getCommand("hc-reload").setExecutor(new CommandReload(this));
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

}
