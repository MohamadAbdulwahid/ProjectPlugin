package io.github.m1gw.projectplugin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class Battle implements CommandExecutor {
    public static Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
    public static boolean battleStarted = false;
    public static Player player1Player;
    public static Player player2Player;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args != null && args.length == 0) {
                sender.sendMessage("Starting the battle...");
                Team team = scoreboard.getTeam(ProjectPlugin.currentPlayers);
                Set<OfflinePlayer> members = team.getPlayers();
                if (members.size() >= 2) {
                    List<OfflinePlayer> membersList = new ArrayList<>(members);
                    int index1, index2;
                    Random random = new Random();

                    do {
                        index1 = random.nextInt(members.size());
                        index2 = random.nextInt(members.size());
                    } while (index1 == index2);

                    OfflinePlayer player1 = membersList.get(index1);
                    OfflinePlayer player2 = membersList.get(index2);
                    sender.sendMessage("found players: " + player1.getName() + " and " + player2.getName());

                    // teleport both players to the battle area
                    player1Player = Bukkit.getPlayer(player1.getName());
                    player2Player = Bukkit.getPlayer(player2.getName());

                    player1Player.teleport(new org.bukkit.Location(Bukkit.getWorld("world"), -23, -4, 0, -90, 0));
                    player2Player.teleport(new org.bukkit.Location(Bukkit.getWorld("world"), 23, -4, -1, 90, 0));
                    player1Player.setGameMode(GameMode.ADVENTURE);
                    player2Player.setGameMode(GameMode.ADVENTURE);

                    battleStarted = true;

                    // give them tools
                    ProjectPlugin.giveTools(player1Player, 2);
                    ProjectPlugin.giveTools(player2Player, 2);

                    // remove them from the currentPlayers team
                    team.removePlayer(player1);
                    team.removePlayer(player2);

                    // fill blocks form block x to block y with glass
                    ProjectPlugin.fillBlocks(Bukkit.getWorld("world"), 21, -2,-2, 21, -4, 0, Material.GLASS);
                    ProjectPlugin.fillBlocks(Bukkit.getWorld("world"), -21, -2, 1, -21, -4, -1, Material.GLASS);
                }
                else {
                    sender.sendMessage("Not enough players to start the battle");
                }
            }
            //  /battle start
            else if (args.length == 1 && "start".equals(args[0].toString())){
                if (!battleStarted){
                    sender.sendMessage("No battle has been started yet");
                    return true;
                }
                // activate timer and remove glass walls
                Bukkit.getWorld("world").getBlockAt(12,-10, 5).setType(Material.REDSTONE_BLOCK);

                // wait for one of the players in the arena to die and add them to winner/loser teams
                battleStarted = false;

            }
            else {
                sender.sendMessage("Usage: /battle");
            }
        }
        return true;
    }
}
