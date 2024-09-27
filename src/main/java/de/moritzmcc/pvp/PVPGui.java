package de.moritzmcc.pvp;

import de.moritzmcc.Config.PVPConfig;
import de.moritzmcc.Utlis.ItemBuilder;
import de.moritzmcc.worldmanage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PVPGui implements Listener {

    private final Inventory inventory;
    private final PVPConfig pvpConfig;

    public PVPGui() {
        this.pvpConfig = new PVPConfig();
        this.inventory = Bukkit.createInventory(null, 3 * 9, "PVP Settings");
        initializeInventory();
    }

    private void initializeInventory() {
        inventory.setItem(11, new ItemBuilder(Material.CLOCK)
                .withName("Hit cooldown: " + (pvpConfig.getHitCoolDownBoolean() ? ChatColor.DARK_GREEN + "enabled" : ChatColor.RED + "disabled"))
                .build());
        inventory.setItem(12, new ItemBuilder(Material.ANVIL).withName("Old Knockback").build());
        inventory.setItem(13, new ItemBuilder(Material.NETHERITE_SWORD)
                .withName("PVP: " + (pvpConfig.getPVPBoolean() ? ChatColor.DARK_GREEN + "enabled" : ChatColor.RED + "disabled"))
                .build());
        inventory.setItem(14, new ItemBuilder(Material.MUSHROOM_STEW).withName("Soup healing: " + (pvpConfig.getSoupHealing() ? ChatColor.DARK_GREEN + "enabled" : ChatColor.RED + "disabled")).build());
        inventory.setItem(15, new ItemBuilder(Material.RED_MUSHROOM).withName("Soup crafting: "  + (pvpConfig.getSoupCrafting() ? ChatColor.DARK_GREEN + "enabled" : ChatColor.RED + "disabled")).build());
    }

    public Inventory getPVPInventory() {
        return inventory;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;

        if (event.getView().getTitle().equals("PVP Settings")) {
            ItemStack currentItem = event.getCurrentItem();
            Player player = (Player) event.getWhoClicked();
            if (currentItem == null) return;

            if (currentItem.isSimilar(new ItemBuilder(Material.NETHERITE_SWORD).withName("PVP: " + (pvpConfig.getPVPBoolean() ? ChatColor.DARK_GREEN + "enabled" : ChatColor.RED + "disabled")).build())) {
                togglePVP();
                updateInventory();
            } else if (currentItem.isSimilar(new ItemBuilder(Material.CLOCK).withName("Hit cooldown: " + (pvpConfig.getHitCoolDownBoolean() ? "enabled" : "disabled")).build())) {
                handleNoHitCoolDown();
                updateInventory();
            }else if (currentItem.isSimilar(new ItemBuilder(Material.MUSHROOM_STEW).withName("Soup healing: " + (pvpConfig.getSoupHealing() ? ChatColor.DARK_GREEN + "enabled" : ChatColor.RED + "disabled")).build())){
                boolean currentStatus = pvpConfig.getSoupHealing();
                pvpConfig.setSoupHealing(!currentStatus);
                updateInventory();

            } else if (currentItem.isSimilar(new ItemBuilder(Material.RED_MUSHROOM).withName("Soup crafting: "  + (pvpConfig.getSoupCrafting() ? ChatColor.DARK_GREEN + "enabled" : ChatColor.RED + "disabled")).build())) {
                toggleRecipe();
                updateInventory();
            }
            player.openInventory(getPVPInventory());
            event.setCancelled(true);
        }
    }

    private void updateInventory() {
        inventory.setItem(11, new ItemBuilder(Material.CLOCK)
                .withName("Hit cooldown: " + (pvpConfig.getHitCoolDownBoolean() ? ChatColor.DARK_GREEN + "enabled" : ChatColor.RED + "disabled"))
                .build());
        inventory.setItem(13, new ItemBuilder(Material.NETHERITE_SWORD)
                .withName("PVP: " + (pvpConfig.getPVPBoolean() ? ChatColor.DARK_GREEN + "enabled" : ChatColor.RED + "disabled"))
                .build());
        inventory.setItem(14, new ItemBuilder(Material.MUSHROOM_STEW)
                .withName("Soup healing: " + (pvpConfig.getSoupHealing() ? ChatColor.DARK_GREEN + "enabled" : ChatColor.RED + "disabled"))
                .build());
        inventory.setItem(15, new ItemBuilder(Material.RED_MUSHROOM)
                .withName("Soup crafting: "  + (pvpConfig.getSoupCrafting() ? ChatColor.DARK_GREEN + "enabled" : ChatColor.RED + "disabled"))
                .build());
    }

    private void togglePVP() {
        boolean currentStatus = pvpConfig.getPVPBoolean();
        pvpConfig.setPVP(!currentStatus);
        Bukkit.getWorlds().forEach(world -> world.setPVP(!currentStatus));
    }

    private void handleNoHitCoolDown() {
        NoHitCooldown noHitCooldown = new NoHitCooldown();
        boolean currentStatus = pvpConfig.getHitCoolDownBoolean();
        pvpConfig.setNoHitCoolDown(!currentStatus);

        if (currentStatus) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                noHitCooldown.disableCooldown(player);
            }
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                noHitCooldown.enableCooldown(player);
            }
        }
    }
    public void toggleRecipe(){
        if (pvpConfig.getSoupCrafting()){
            Main.getInstance().getServer().resetRecipes();
        }else{
            Main.getInstance().getServer().addRecipe(SoupCraftingRecipe.addCactusRecraft());
            Main.getInstance().getServer().addRecipe(SoupCraftingRecipe.addCocoRecraft());
        }
        boolean currentStatus = pvpConfig.getSoupCrafting();
        pvpConfig.setSoupCrafting(!currentStatus);
    }
}
