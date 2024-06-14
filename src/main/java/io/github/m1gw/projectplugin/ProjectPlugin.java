package io.github.m1gw.projectplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public final class ProjectPlugin extends JavaPlugin {

    public static boolean newPlayersCanJoin = true;
    public static String winners = "winners";
    public String losers = "losers";
    public String currentPlayers = "currentPlayers";

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new OnPlayerJoin(), this);
        Bukkit.getConsoleSender().sendMessage("Stupid PvP plugin enabled :)");

        // Add Teams
        createTeam("winners");
        createTeam("losers");
        createTeam("currentPlayers");

        // Register the command
        this.getCommand("gotospawn").setExecutor(new GoToSpawn());
        this.getCommand("addnewpeopletogame").setExecutor(new AddNewPeopleToGame());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static void createTeam(String teamName) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

        if (scoreboard.getTeam(teamName) != null) {
            Bukkit.getLogger().info("Team " + teamName + " already exists.");
            return;
        }

        Team team = scoreboard.registerNewTeam(teamName);
    }
}


