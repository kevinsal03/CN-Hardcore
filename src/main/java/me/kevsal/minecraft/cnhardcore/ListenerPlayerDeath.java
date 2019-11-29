package me.kevsal.minecraft.cnhardcore;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.context.DefaultContextKeys;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class ListenerPlayerDeath implements Listener {

    public final CNHC plugin;
    public final LuckPerms api;

    public ListenerPlayerDeath(CNHC plugin) {
        this.plugin = plugin;
        this.api = LuckPermsProvider.get();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();
        //send a message to the player to say they died
        p.sendMessage(plugin.getPrefix() + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config.messages.death")));

        //group permission node
        Node deadGroupNode = Node.builder("group." + plugin.getConfig().getString("config.death-options.death-group"))
                .value(true)
                .withContext(DefaultContextKeys.SERVER_KEY, plugin.getConfig().getString("config.death-options.server-context"))
                .build();

        //get user in context of LP api
        User u = api.getUserManager().getUser(p.getUniqueId());
        //edit the user data and assign the group
        u.data().add(deadGroupNode);

    }

}
