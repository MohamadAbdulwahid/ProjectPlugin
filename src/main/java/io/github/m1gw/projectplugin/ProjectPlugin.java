package io.github.m1gw.projectplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ProjectPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new OnPlayerJoin(), this);
        Bukkit.getConsoleSender().sendMessage("Stupid PvP plugin enabled :)");

        this.getCommand("gotospawn").setExecutor(new GoToSpawn());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
