package io.github.m1gw.projectplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.World;
import org.bukkit.block.Block;

public final class ProjectPlugin extends JavaPlugin {

    public static boolean newPlayersCanJoin = true;
    public static String winners = "winners";
    public static String losers = "losers";
    public static String currentPlayers = "currentPlayers";
    public static String spectators = "spectators";

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new OnPlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new BattleDeathListener(this), this);        Bukkit.getConsoleSender().sendMessage("Stupid PvP plugin enabled :)");

        // Add Teams
        createTeam(winners);
        createTeam(losers);
        createTeam(currentPlayers);
        createTeam(spectators);

        // Register the command
        this.getCommand("gotospawn").setExecutor(new GoToSpawn());
        this.getCommand("addnewpeopletogame").setExecutor(new AddNewPeopleToGame());
        this.getCommand("givetools").setExecutor(new GiveTools());
        this.getCommand("battle").setExecutor(new Battle());
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

    public static void fillBlocks(World world, int x1, int y1, int z1, int x2, int y2, int z2, Material material) {
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                for (int z = Math.min(z1, z2); z <= Math.max(z1, z2); z++) {
                    Block block = world.getBlockAt(x, y, z);
                    block.setType(material);
                }
            }
        }
    }
}


