package de.moritzmcc.mobspawn;

import de.moritzmcc.Config.MobConfig;
import de.moritzmcc.Utlis.ItemBuilder;
import de.moritzmcc.worldmanage.Main;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import me.arcaniax.hdb.object.head.Head;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MobspawnGui implements Listener {

    private final Inventory inventory;
    private final MobConfig mobConfig;


    public MobspawnGui() {
        this.mobConfig = new MobConfig();
        this.inventory =  Bukkit.createInventory(null, 5 * 9, "Mob spawning");
        initializeInventory();

    }
    private void initializeInventory() {
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        inventory.setItem(11 , api.getItemHead("7129")); //
        inventory.setItem(12 , api.getItemHead("34285")); //Slime
        inventory.setItem(13 , api.getItemHead("61285")); //Creeper
        inventory.setItem(14 , api.getItemHead("29171")); //enderman
        inventory.setItem(15 , api.getItemHead("27662")); //Ghast
        inventory.setItem(16 , api.getItemHead("27398")); //MagmaCube




    }

    public Inventory getInventory() {
        return inventory;
    }
}
