package de.moritzmcc.gui;

import de.moritzmcc.Utlis.ItemBuilder;
import de.moritzmcc.pvp.PVPGui;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
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
            if (Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta()).getDisplayName().equals("PVP")) event.getWhoClicked().openInventory(new PVPGui().getPVPInventory());
            event.setCancelled(true);
        }
    }



}
