package me.kevsal.minecraft.cnhardcore;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
/*
NOTE: Unused because API not working
import net.luckperms.api.context.DefaultContextKeys;
import net.luckperms.api.event.LuckPermsEvent;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.group.GroupManager;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.InheritanceNode;
 */
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class ListenerPlayerDeath implements Listener {

    private final CNHC plugin;
    private final LuckPerms api;

    public ListenerPlayerDeath(CNHC plugin) {
        this.plugin = plugin;
        this.api = LuckPermsProvider.get();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();
        //send a message to the player to say they died
        assert p != null;
        p.sendMessage(plugin.getPrefix() + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("config.messages.death"))));

        /*
        TODO: Figure out why the API doesn't save changes after a sync operation.

        //group permission node
        Group deadGroup = api.getGroupManager().getGroup(Objects.requireNonNull(plugin.getConfig().getString("config.death-options.dead-group")));
        InheritanceNode deadGroupNode = InheritanceNode.builder(deadGroup).value(true).withContext(DefaultContextKeys.SERVER_KEY, Objects.requireNonNull(plugin.getConfig().getString("config.death-options.server-context"))).build();

        //get user in context of LP api
        User u = api.getUserManager().getUser(p.getUniqueId());
        //edit the user data and assign the group
        assert u != null;
        u.data().add(deadGroupNode);
         */

        // TODO: Replace with API operation rather than using command dispatch
        //set the user's group
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " parent set " + Objects.requireNonNull(plugin.getConfig().getString("config.death-options.dead-group")) + " true server=" +  Objects.requireNonNull(plugin.getConfig().getString("config.death-options.server-context")));
        // set to spectator
        p.setGameMode(GameMode.SPECTATOR);
    }

}
