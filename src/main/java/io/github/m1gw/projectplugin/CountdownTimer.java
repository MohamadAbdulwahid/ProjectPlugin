package io.github.m1gw.projectplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CountdownTimer extends BukkitRunnable {

    private static JavaPlugin plugin = null;
    private int countdownTime;

    public CountdownTimer(int countdownTime) {
        this.countdownTime = countdownTime;
    }

    @Override
    public void run() {
        if (countdownTime <= 0) {
            // countdown done cancel and send go
            this.cancel();

            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle(ChatColor.DARK_GREEN + "GO!", "", 0, 20, 1);
            }

            ProjectPlugin.fillBlocks(Bukkit.getWorld("world"), -21, -4, -1, -21, -2, 1, Material.AIR);

            ProjectPlugin.fillBlocks(Bukkit.getWorld("world"), 21, -2, -2, 21, -4, 0, Material.AIR);

            return;
        }

        ChatColor CurrentColor;

        if (countdownTime <= 5 && countdownTime > 3) {
            CurrentColor = ChatColor.YELLOW;
        } else {
            CurrentColor = ChatColor.RED;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(CurrentColor + String.valueOf(countdownTime), "", 0, 20, 1);
        }

        countdownTime--;
    }

    public void start() {
        //20 ticks > 1s
        this.runTaskTimerAsynchronously(plugin, 0L, 20L);
    }

    public static void startCountdown(JavaPlugin plugin, int countdownTime) {
        CountdownTimer.plugin = plugin;
        CountdownTimer countdownTimer = new CountdownTimer(countdownTime);
        countdownTimer.start();
    }
}