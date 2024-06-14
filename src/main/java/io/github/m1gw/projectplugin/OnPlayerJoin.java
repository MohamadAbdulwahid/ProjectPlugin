package io.github.m1gw.projectplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerJoin implements Listener {
    // Using the spigot API try looking for all players if they're in the allowed area, if not, send an error in console
    // The allowed area is from 0 to 100, if the player is outside this area, send an error
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getBlockX() < 0 || player.getLocation().getBlockX() > 100) {
            player.getServer().broadcastMessage("Player " + player.getName() + " is outside allowed area!");
        }
    }
}
