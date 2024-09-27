package de.moritzmcc.worldmanage;

import de.moritzmcc.Config.MobConfig;
import de.moritzmcc.Config.PVPConfig;
import de.moritzmcc.commands.ManageCommand;
import de.moritzmcc.gui.ManageMenuInventory;
import de.moritzmcc.mobspawn.DisableMobspawn;
import de.moritzmcc.mobspawn.MobspawnGui;
import de.moritzmcc.pvp.DamageNerf;
import de.moritzmcc.pvp.NoHitCooldown;
import de.moritzmcc.pvp.PVPGui;
import de.moritzmcc.pvp.SoupHealing;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

     PVPConfig pvpConfig = new PVPConfig();
     static MobConfig mobConfig = new MobConfig();
    static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        pvpConfig.setDefaultValues();


        getCommand("manage").setExecutor(new ManageCommand());

      PluginManager manager = Bukkit.getPluginManager();
      manager.registerEvents(new ManageMenuInventory(), this);
      manager.registerEvents(new PVPGui(), this);
      manager.registerEvents(new NoHitCooldown(), this);
      manager.registerEvents(new SoupHealing(), this);
      manager.registerEvents(new DamageNerf(1,0.5), this);
      manager.registerEvents(new MobspawnGui(), this);
      manager.registerEvents(new DisableMobspawn(), this);


    }

    @Override
    public void onDisable() {
        pvpConfig.save();
        mobConfig.save();
    }

    public PVPConfig getPvpConfig() {
        return pvpConfig;
    }

    public static Plugin getInstance() {
        return instance;
    }

    public static MobConfig getMobConfig() {
        return mobConfig;
    }
}
