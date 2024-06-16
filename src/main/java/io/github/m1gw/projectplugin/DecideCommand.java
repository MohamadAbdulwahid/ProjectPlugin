package io.github.m1gw.projectplugin;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DecideCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args != null && args[0] != null) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
            String name = player.getName();

            if (args.length == 1) {
                boolean isBanned = Battle.bannedPlayers.contains(name);
                boolean hasDisconnected = Battle.disconnectedPlayers.contains(name);
                sender.sendMessage("The player " + player.getName() + " is banned / has disconnected: " + isBanned + " / " + hasDisconnected);
            }
            if (args.length == 2) {
                if (args[1].toString().equals("ban")) {
                    Battle.bannedPlayers.add(name);
                    Battle.disconnectedPlayers.remove(name);
                    sender.sendMessage("The player " + name + " has been banned");
                    if (player.isOnline()) {
                        player.getPlayer().kickPlayer("You have been banned from playing again but you can still watch. If you think this was an error please ask an admin. Please rejoin.");
                    }

                }
                if (args[1].toString().equals("allow")) {
                    if(Battle.disconnectedPlayers.contains(name)) Battle.disconnectedPlayers.remove(name);
                    if(Battle.bannedPlayers.contains(name)) Battle.bannedPlayers.remove(name);
                    sender.sendMessage("The player " + name + " has been removed from disconnectedPlayers");
                    if (player.isOnline()) {
                        player.getPlayer().kickPlayer("We have decided to allow you to continue playing! Please rejoin.");
                    }
                }
                if (args[1].toString().equals("unban")) {
                    Battle.disconnectedPlayers.add(name);
                    sender.sendMessage("The player " + name + " has been unbanned");
                    if (player.isOnline()) {
                        player.getPlayer().kickPlayer("You have been unbanned! Please rejoin.");
                    }
                }
                sender.sendMessage("args2");
            }


        } else {
            sender.sendMessage("Usage: /decide <player> [ban/allow/unban]");
        }

        return true;
    }
}
