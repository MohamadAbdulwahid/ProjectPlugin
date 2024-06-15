package io.github.m1gw.projectplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveTools implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player;
        if (sender instanceof Player) {
            if (args != null && args.length > 0) {
                player = Bukkit.getPlayer(args[0]);
                if (player == null) {
                    sender.sendMessage("Player not found");
                    return true;
                }
                player.sendMessage("Giving tools to " + player.getName() + "...");
            }
            player = (Player) sender;
            assert args != null;
            player.sendMessage("Giving toolset " + args[1] + " to you...");
            player.getInventory().clear();
            ProjectPlugin.giveTools(player, Integer.parseInt(args[1]));
        }
        return true;
    }
}
