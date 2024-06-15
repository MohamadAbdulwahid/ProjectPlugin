package io.github.m1gw.projectplugin;

import org.bukkit.GameMode;
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
        } else {
            winner = Battle.player1Player;
            loser = Battle.player2Player;
        }

        Team winners = Battle.scoreboard.getTeam("winners");
        Team losers = Battle.scoreboard.getTeam("losers");
        if (winners != null) winners.addPlayer(winner);
        if (losers != null) losers.addPlayer(loser);

        Battle.battleStarted = false;
        Battle.player1Player = null;
        Battle.player2Player = null;
    }
}
