package io.github.m1gw.projectplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.entity.Player;

public class GiveWinnersPlaying implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Scoreboard scoreboard = Battle.scoreboard;
        Team winners = scoreboard.getTeam(ProjectPlugin.winners);
        Team currentPlayers = scoreboard.getTeam(ProjectPlugin.currentPlayers);
        if(currentPlayers == null) {
            ProjectPlugin.sendInfoToUs("currentPlayers is null!");
            return false;
        }

        for (String playerName : winners.getEntries()) {
            Player player = Bukkit.getPlayer(playerName);
            if (player != null && player.isOnline()) {
                ProjectPlugin.sendInfoToUs("Adding " + player.getName() + " to current players....");
                if(currentPlayers.getPlayers().contains(player))
                    ProjectPlugin.sendInfoToUs(player.getName() + " is already in current players! (might be a bug)");
                else
                    currentPlayers.addPlayer(player);
            }

        }
        return true;
    }
}
