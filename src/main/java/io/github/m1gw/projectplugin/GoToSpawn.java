package io.github.m1gw.projectplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GoToSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player;
        if (sender instanceof Player) {
            if(args != null && args.length > 0) {
                player = org.bukkit.Bukkit.getPlayer(args[0]);
                if(player == null) {
                    sender.sendMessage("Player not found");
                    return true;
                }
                player.sendMessage("Teleporting " + player.getName() + " to spawn...");
            }
            player = (Player) sender;
            player.sendMessage("Teleporting to spawn...");
            OnPlayerJoin.TeleportToSpawn(player);
        }
        return true;
    }
}
