package de.moritzmcc.gui;

import de.moritzmcc.Utlis.ItemBuilder;
import de.moritzmcc.mobspawn.MobspawnGui;
import de.moritzmcc.pvp.PVPGui;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ManageMenuInventory implements Listener {
    Inventory inventory = Bukkit.createInventory(null, 6 * 9);


    public void openManageInventory(Player player) {
        player.openInventory(getManageInventory(player));
    }

    public Inventory getManageInventory(Player player) {

        World world = player.getWorld();

        inventory.setItem(11, new ItemBuilder(getDifficultyMaterial(world)).withName("Difficulty " + Bukkit.getWorld(world.getUID()).getDifficulty()).build());
        inventory.setItem(15, new ItemBuilder(getGamemodeMaterial()).withName("GameMode " + Bukkit.getDefaultGameMode()).build());
        inventory.setItem(19, new ItemBuilder(Material.ZOMBIE_HEAD).withName("Mobspawn").build());
        inventory.setItem(20, new ItemBuilder(Material.NETHERRACK).withName("Nether").build());
        inventory.setItem(21, new ItemBuilder(Material.END_PORTAL_FRAME).withName("End").build());
        inventory.setItem(22, new ItemBuilder(Material.BOOKSHELF).withName("Structures").build());
        inventory.setItem(23, new ItemBuilder(Material.PLAYER_HEAD).withName("PVP").build());
        inventory.setItem(24, new ItemBuilder(Material.GOLDEN_APPLE).withName("Health").build());
        inventory.setItem(25, new ItemBuilder(Material.CLOCK).withName("Time").build());
        inventory.setItem(28, new ItemBuilder(Material.WHITE_WOOL).withName("Weather").build());
        inventory.setItem(29, new ItemBuilder(Material.REDSTONE).withName("Damage").build());
        inventory.setItem(30, new ItemBuilder(Material.COMMAND_BLOCK).withName("Admin").build());
        inventory.setItem(31, new ItemBuilder(Material.BEDROCK).withName("WorldBorder").build());
        inventory.setItem(32, new ItemBuilder(Material.BARRIER).withName("Ban").build());
        inventory.setItem(33, new ItemBuilder(Material.PAPER).withName("PlayerList").build());
        inventory.setItem(34, new ItemBuilder(Material.FIREWORK_ROCKET).withName("Events").build());


        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).withName(" ").build());
            }
        }

        return inventory;
    }





    private Material getDifficultyMaterial(World world){
        if ( Bukkit.getWorld(world.getUID()).getDifficulty() == Difficulty.EASY )return Material.STONE_SWORD;
        if ( Bukkit.getWorld(world.getUID()).getDifficulty() == Difficulty.NORMAL )return Material.IRON_SWORD;
        if ( Bukkit.getWorld(world.getUID()).getDifficulty() == Difficulty.HARD )return Material.DIAMOND_SWORD;
        return Material.WOODEN_SWORD;
    }

    private Material getGamemodeMaterial(){
        if ( Bukkit.getDefaultGameMode() == GameMode.SPECTATOR)return Material.GLASS;
        if (  Bukkit.getDefaultGameMode() == GameMode.ADVENTURE )return Material.COMPASS;
        if ( Bukkit.getDefaultGameMode() == GameMode.CREATIVE )return Material.COMMAND_BLOCK;
        return Material.IRON_PICKAXE;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){

        if (Arrays.equals(event.getInventory().getContents(), getManageInventory((Player) event.getWhoClicked()).getContents())) {

            Player player = ((Player) event.getWhoClicked());
            World world = player.getWorld();

            if (event.getCurrentItem().equals(new ItemBuilder(Material.PLAYER_HEAD).withName("PVP").build())) player.openInventory(new PVPGui().getPVPInventory());
            if (event.getCurrentItem().equals(new ItemBuilder(getDifficultyMaterial(world)).withName("Difficulty " + Bukkit.getWorld(world.getUID()).getDifficulty()).build())){
                manageDifficulty(world);
                openManageInventory(player);
            }
            if (event.getCurrentItem().equals(new ItemBuilder(getGamemodeMaterial()).withName("GameMode " + Bukkit.getDefaultGameMode()).build())) {
                manageDefaultGameMode();
                openManageInventory(player);
            }
            if (event.getCurrentItem().equals(new ItemBuilder(Material.ZOMBIE_HEAD).withName("Mobspawn").build())){
                player.openInventory(new MobspawnGui().getInventory());
            }
            event.setCancelled(true);

        }
    }


    private void manageDifficulty(World world){
        List<Difficulty> list = new ArrayList<>();
        list.add(Difficulty.EASY);
        list.add(Difficulty.NORMAL);
        list.add(Difficulty.HARD);
        list.add(Difficulty.PEACEFUL);
        int difficultyIndex = list.indexOf(world.getDifficulty());
        if (difficultyIndex == list.size() - 1) {
            world.setDifficulty(list.get(0));
        }else  world.setDifficulty(list.get(difficultyIndex + 1));

    }
    private void manageDefaultGameMode(){
        List<GameMode> list = new ArrayList<>();
        list.add(GameMode.SURVIVAL);
        list.add(GameMode.CREATIVE);
        list.add(GameMode.SPECTATOR);
        list.add(GameMode.ADVENTURE);
        Bukkit.broadcastMessage(list.toString());
        int gmIndex = list.indexOf(Bukkit.getDefaultGameMode());
        if (gmIndex == list.size() - 1) {
            Bukkit.setDefaultGameMode(list.get(0));
        }else Bukkit.setDefaultGameMode(list.get(gmIndex +1));


    }



}
