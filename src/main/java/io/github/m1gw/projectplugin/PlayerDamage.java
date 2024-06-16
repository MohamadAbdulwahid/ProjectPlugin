package io.github.m1gw.projectplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerDamage implements Listener {
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getEntity();
        if (event.getDamager() instanceof Player) {
            Player damager = (Player) event.getDamager();
            if (damager.isOp()) {
                return;
            }
            if (player != Battle.player1Player && player != Battle.player2Player) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) {
            return;
        }
        event.setCancelled(true);
    }
}
