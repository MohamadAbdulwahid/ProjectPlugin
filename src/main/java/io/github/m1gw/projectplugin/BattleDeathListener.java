package io.github.m1gw.projectplugin;

import io.github.m1gw.projectplugin.Battle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Bat;
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
        Player player = event.getPlayer();

        if (!Battle.battleStarted) { // it should be false, when we do /battle start
            return;
        }


        if (player.getName().toString().equalsIgnoreCase(Battle.player1Player.getName().toString())
                || player.getName().toString().equalsIgnoreCase(Battle.player2Player.getName().toString())) {


            Battle.player1Player.getInventory().clear();
            Battle.player2Player.getInventory().clear();

            OnPlayerJoin.TeleportToSpawn(Battle.player1Player);
            OnPlayerJoin.TeleportToSpawn(Battle.player2Player);

            Player winner, loser;
            if (player.equals(Battle.player1Player)) {
                winner = Battle.player2Player;
                loser = Battle.player1Player;
            } else {
                winner = Battle.player1Player;
                loser = Battle.player2Player;
            }

            // Add the players to the winner/loser teams
            Team winners = Battle.scoreboard.getTeam("winners");
            Team losers = Battle.scoreboard.getTeam("losers");
            if (winners != null) winners.addPlayer(winner);
            if (losers != null) losers.addPlayer(loser);

            // Reset the battle
            Battle.battleStarted = false;
            Battle.player1Player = null;
            Battle.player2Player = null;

            OnPlayerJoin.TeleportToSpawn(winner);
            OnPlayerJoin.TeleportToSpawn(loser);

            winner.getInventory().clear();
            loser.getInventory().clear();
            Battle.battleStarted = false;

            Bukkit.broadcastMessage("The player " + winner.getName() + " has won the battle against " + loser.getName() + " congrats!");
            for (Player player2 : Bukkit.getOnlinePlayers()) {
                player2.sendTitle(ChatColor.DARK_GREEN + "gg", "", 0, 20, 1);
            }
        }
    }
}
