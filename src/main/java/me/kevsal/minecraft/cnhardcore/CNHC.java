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
    }

    @Override
    public void onDisable() {

    }
}
