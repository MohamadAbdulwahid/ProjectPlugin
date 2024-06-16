package io.github.m1gw.projectplugin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Team;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) {
            return;
        }

        Player winner, loser;
        if (player.equals(Battle.player1Player)) {
            winner = Battle.player2Player;
            loser = Battle.player1Player;
            ProjectPlugin.sendInfoToUs("Player 1 (" + loser.getName() + ") has left the game with the reason: " + event.getQuitMessage());
            Bukkit.broadcastMessage("Player 1 (" + loser.getName() + ") has left the game" + "Match ended");
        } else {
            winner = Battle.player1Player;
            loser = Battle.player2Player;
            ProjectPlugin.sendInfoToUs("Player 2 (" + loser.getName() + ") has left the game with the reason: " + event.getQuitMessage());
            Bukkit.broadcastMessage("Player 2 (" + loser.getName() + ") has left the game. " + "Match ended");
        }

        Team winners = Battle.scoreboard.getTeam("winners");
        Team losers = Battle.scoreboard.getTeam("losers");
        if (winners != null) winners.addPlayer(winner);
        if (losers != null) losers.addPlayer(loser);

        Battle.disconnectedPlayers.add(player.getName());

        Battle.battleStarted = false;
        Battle.player1Player = null;
        Battle.player2Player = null;

        OnPlayerJoin.TeleportToSpawn(winner);
        winner.getInventory().clear();

        ProjectPlugin.fillBlocks(Bukkit.getWorld("world"), -11, -10, 5, 12, -10, 8, Material.OAK_PLANKS);
        ///fill 11 -10 5 12 -10 8 minecraft:oak_planks
    }
}
