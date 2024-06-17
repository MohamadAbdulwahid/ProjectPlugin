package io.github.m1gw.projectplugin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Team;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (event.getPlayer().isOp() || (event.getPlayer() != Battle.player2Player && event.getPlayer() != Battle.player1Player)
                || Battle.player1Player == null || Battle.player2Player == null) {
            return;
        }

        OfflinePlayer player = event.getPlayer();

        OfflinePlayer winner, loser;
        if (player.getName().equalsIgnoreCase(Battle.player1Player.getName())) {
            winner = Battle.player2Player;
            loser = Battle.player1Player;
            ProjectPlugin.sendInfoToUs("Player 1 (" + loser.getName() + ") has left the game with the reason: " + event.getQuitMessage());
            Bukkit.broadcastMessage("Player 1 (" + loser.getName() + ") has left the game" + "Match ended");
        } else if (player.getName().equalsIgnoreCase(Battle.player2Player.getName())){
            winner = Battle.player1Player;
            loser = Battle.player2Player;
            ProjectPlugin.sendInfoToUs("Player 2 (" + loser.getName() + ") has left the game with the reason: " + event.getQuitMessage());
            Bukkit.broadcastMessage("Player 2 (" + loser.getName() + ") has left the game. " + "Match ended");
        }
        else {
            Bukkit.broadcastMessage("_How did we get here?_ the world is broken");
            return;
        }

        Team winners = Battle.scoreboard.getTeam("winners");
        Team losers = Battle.scoreboard.getTeam("losers");
        if (winners != null) winners.addPlayer(winner);
        if (losers != null) losers.addPlayer(loser);

        Battle.disconnectedPlayers.add(player.getName());

        Battle.battleStarted = false;
        Battle.player1Player = null;
        Battle.player2Player = null;

        OnPlayerJoin.TeleportToSpawn(winner.getPlayer());
        if (winner.isOnline()) {
            winner.getPlayer().getInventory().clear();
        }

        ProjectPlugin.fillBlocks(Bukkit.getWorld("world"), -11, -10, 5, 12, -10, 8, Material.OAK_PLANKS);

    }
}
