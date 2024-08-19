package de.moritzmcc.pvp;

import de.moritzmcc.Config.PVPConfig;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class SoupHealing implements Listener {
    @EventHandler
    public boolean onRightClickSoup(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() != Action.LEFT_CLICK_AIR) {
            ItemStack itemStack = event.getItem();
            if (itemStack != null) {
                if (!new PVPConfig().getSoupHealing())return false;
                if (event.hasItem() && event.getMaterial().equals(Material.MUSHROOM_STEW)) {
                    if (event.getHand() == EquipmentSlot.OFF_HAND) {
                        return false;
                    }

                    int amountToHeal = 7;

                    if (player.getHealth() < Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
                        player.setHealth(Math.min(player.getHealth() + (double) amountToHeal, Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()));
                        player.getInventory().setItemInMainHand(new ItemStack(Material.BOWL));
                        return true;
                    } else if (player.getFoodLevel() < 20) {
                        player.setFoodLevel(player.getFoodLevel() + 6);
                        player.setSaturation(player.getSaturation() + 7.0F);
                        player.getInventory().setItemInMainHand(new ItemStack(Material.BOWL));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
