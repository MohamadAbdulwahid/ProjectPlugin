package io.github.m1gw.projectplugin;

import io.github.m1gw.projectplugin.Battle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scoreboard.Team;

public class BattleDeathListener implements Listener {
    private final Battle battle;

    public BattleDeathListener(Battle battle) {
        this.battle = battle;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.equals(battle.player1Player) || player.equals(battle.player2Player)) {
            // The player who died is in the arena
            Player winner, loser;
            if (player.equals(battle.player1Player)) {
                winner = battle.player2Player;
                loser = battle.player1Player;
            } else {
                winner = battle.player1Player;
                loser = battle.player2Player;
            }

            // Add the players to the winner/loser teams
            Team winners = battle.scoreboard.getTeam("winners");
            Team losers = battle.scoreboard.getTeam("losers");
            if (winners != null) winners.addPlayer(winner);
            if (losers != null) losers.addPlayer(loser);

            // Reset the battle
            battle.battleStarted = false;
            battle.player1Player = null;
            battle.player2Player = null;
        }
    }
}
