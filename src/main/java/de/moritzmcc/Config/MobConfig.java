package de.moritzmcc.Config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MobConfig {


        private final File file;
        private final YamlConfiguration config;

        public MobConfig() {
            File dir = new File("./plugins/WorldManage");

            if (!dir.exists()){
                dir.mkdir();
            }

            this.file = new File(dir,"MobConfig.yml");

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

        public void set(String name, Boolean allowedToSpawn){
            config.set(name, allowedToSpawn);
        }

        public Boolean getAllowed(String name){
            return (Boolean) config.get(name);
        }



}
