package io.github.m1gw.projectplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public final class ProjectPlugin extends JavaPlugin {

    public static boolean newPlayersCanJoin = true;
    public static String winners = "winners";
    public static String losers = "losers";
    public static String currentPlayers = "currentPlayers";

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new OnPlayerJoin(), this);
        Bukkit.getConsoleSender().sendMessage("Stupid PvP plugin enabled :)");

        // Add Teams
        createTeam(winners);
        createTeam(losers);
        createTeam(currentPlayers);

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

    public static void giveTools(Player player, int setIndex){
        ItemStack[][] equipmentSets = new ItemStack[][] {
                { // Diamond set (0)
                        new ItemStack(Material.DIAMOND_CHESTPLATE),
                        new ItemStack(Material.DIAMOND_LEGGINGS),
                        new ItemStack(Material.DIAMOND_BOOTS),
                        new ItemStack(Material.DIAMOND_HELMET),
                        new ItemStack(Material.DIAMOND_SWORD),
                        new ItemStack(Material.BOW),
                        new ItemStack(Material.ARROW, 10),
                        new ItemStack(Material.GOLDEN_APPLE, 1)
                },
                { // Leather/Wood set (1)
                        new ItemStack(Material.LEATHER_CHESTPLATE),
                        new ItemStack(Material.LEATHER_LEGGINGS),
                        new ItemStack(Material.LEATHER_BOOTS),
                        new ItemStack(Material.LEATHER_HELMET),
                        new ItemStack(Material.WOODEN_SWORD),
                        new ItemStack(Material.BOW),
                        new ItemStack(Material.ARROW, 10),
                        new ItemStack(Material.GOLDEN_APPLE, 1)
                },
                { // Iron set (2)
                        new ItemStack(Material.IRON_CHESTPLATE),
                        new ItemStack(Material.IRON_LEGGINGS),
                        new ItemStack(Material.IRON_BOOTS),
                        new ItemStack(Material.IRON_HELMET),
                        new ItemStack(Material.IRON_SWORD),
                        new ItemStack(Material.BOW),
                        new ItemStack(Material.ARROW, 10),
                        new ItemStack(Material.GOLDEN_APPLE, 1)

                }
        };
        ItemStack[] equipmentSet = equipmentSets[setIndex];
        player.getInventory().setChestplate(equipmentSet[0]);
        player.getInventory().setLeggings(equipmentSet[1]);
        player.getInventory().setBoots(equipmentSet[2]);
        player.getInventory().setHelmet(equipmentSet[3]);
        player.getInventory().addItem(equipmentSet[4]);
        player.getInventory().addItem(equipmentSet[5]);
        player.getInventory().addItem(equipmentSet[6]);
        player.getInventory().addItem(equipmentSet[7]);
    }
}


