package de.moritzmcc.pvp;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageNerf implements Listener {
    private final String[] nerfedItems;
    private final double swordNerf;
    private final double otherNerf;
    public DamageNerf(double swordNerf, double otherNerf) {
        this.swordNerf = swordNerf;
        this.otherNerf = otherNerf;
        this.nerfedItems = new String[]{"_AXE", "_SHOVEL", "_PICKAXE"};
    }

    @EventHandler
    public void onDamageing(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            String itemName = player.getInventory().getItemInMainHand().getType().name();
            if (itemName.endsWith("_SWORD")) {
                event.setDamage(event.getDamage() * swordNerf);
                return;
            }
            for (String nerfedItem : nerfedItems) {
                if (itemName.endsWith(nerfedItem)) {
                    event.setDamage(event.getDamage() * otherNerf);
                }
            }
        }
    }
}
