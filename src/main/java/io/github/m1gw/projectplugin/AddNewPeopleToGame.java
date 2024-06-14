package io.github.m1gw.projectplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddNewPeopleToGame implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if(args != null && args.length == 1) {
                if(args[0].equalsIgnoreCase("true")) {
                    ProjectPlugin.newPlayersCanJoin = true;
                    sender.sendMessage("New players can now join the game");
                    return true;
                }
                else if(args[0].equalsIgnoreCase("false")) {
                    ProjectPlugin.newPlayersCanJoin = false;
                    sender.sendMessage("New players can no longer join the game");
                    return true;
                }
                else{
                    sender.sendMessage("Current mode is: " + ProjectPlugin.newPlayersCanJoin);
                    return true;
                }
            }
            sender.sendMessage("Usage: /addnewpeopletogame <true/false>");
            return true;
        }
        return false;
    }
}
