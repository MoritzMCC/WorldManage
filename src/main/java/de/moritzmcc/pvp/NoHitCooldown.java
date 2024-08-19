package de.moritzmcc.pvp;

import de.moritzmcc.Config.PVPConfig;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class NoHitCooldown implements Listener {

    private PVPConfig pvpConfig = new PVPConfig();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        handleCooldown(event.getPlayer());
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        handleCooldown(event.getPlayer());
    }

    private void handleCooldown(Player player) {
        if (pvpConfig.getHitCoolDownBoolean()) {
            enableCooldown(player);
        } else {
            disableCooldown(player);
        }
    }

    public void disableCooldown(Player player) {
        AttributeInstance attackSpeedAttribute = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        if (attackSpeedAttribute != null) {
            attackSpeedAttribute.setBaseValue(100);
        }
    }

    public void enableCooldown(Player player) {
        AttributeInstance attackSpeedAttribute = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        if (attackSpeedAttribute != null) {
            attackSpeedAttribute.setBaseValue(4);
        }
    }


}
