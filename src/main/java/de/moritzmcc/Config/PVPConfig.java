package de.moritzmcc.Config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PVPConfig {

    private final File file;
    private final YamlConfiguration config;

    public PVPConfig() {
        File dir = new File("./plugins/WorldManage");

        if (!dir.exists()){
            dir.mkdir();
        }

        this.file = new File(dir,"PVPConfig.yml");

        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        config.set("PVP", true);
        config.set("NoHitCoolDown", false);
        config.set("DamageNerf", false);
        config.set("OldKnockback", false);
        config.set("SoupHealing", false);
        config.set("SoupCrafting", false);
        save();
    }

    public void setPVP(Boolean pvpEnable){
        config.set("PVP", pvpEnable);
        save();
    }

    public void setNoHitCoolDown(Boolean noHitCoolDown){
        config.set("NoHitCoolDown", noHitCoolDown);
        save();
    }

    public void setDamageNerf(Boolean damageNerf){
        config.set("DamageNerf", damageNerf);
        save();
    }

    public void setOldKnockback(Boolean oldKnockback){
        config.set("OldKnockback", oldKnockback);
        save();
    }

    public void setSoupHealing(Boolean soupHealing){
        config.set("SoupHealing", soupHealing);
        save();
    }
    public void setSoupCrafting(Boolean soupCrafting){
        config.set("SoupCrafting", soupCrafting);
        save();
    }

    public Boolean getPVPBoolean(){
        return config.getBoolean("PVP");
    }

    public Boolean getHitCoolDownBoolean(){
        return config.getBoolean("NoHitCoolDown");
    }
    public Boolean getSoupHealing(){
        return config.getBoolean("SoupHealing");
    }
    public Boolean getSoupCrafting(){
        return config.getBoolean("SoupCrafting");
    }


}
