package de.moritzmcc.mobspawn;


import de.moritzmcc.Config.MobConfig;
import de.moritzmcc.worldmanage.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisableMobspawn implements Listener {

    List<String> hostileMobs = new ArrayList<>(Arrays.asList(
            "Blaze", "Bogged", "Breeze", "Cave Spider", "Creeper", "Drowned",
            "Elder Guardian", "Ender Dragon", "Enderman", "Endermite", "Evoker",
            "Ghast", "Giant", "Guardian", "Hoglin", "Husk", "Illusioner",
            "Magma Cube", "Phantom", "Piglin", "Piglin Brute", "Pillager",
            "Ravager", "Shulker", "Silverfish", "Skeleton", "Slime", "Spider",
            "Stray", "Vex", "Vindicator", "Warden", "Witch", "Wither",
            "Wither Skeleton", "Zoglin", "Zombie", "Zombie Villager",
            "Zombified Piglin"
    ));

    List<String> friendlyMobs = new ArrayList<>(Arrays.asList(
            "Allay", "Armadillo", "Axolotl", "Bat", "Bee", "Camel", "Cat",
            "Chicken", "Cod", "Cow", "Dolphin", "Donkey", "Fox", "Frog",
            "Glow Squid", "Goat", "Horse", "Llama", "Mooshroom", "Mule",
            "Ocelot", "Panda", "Parrot", "Pig", "Polar Bear", "Pufferfish",
            "Rabbit", "Salmon", "Sheep", "Skeleton Horse", "Sniffer",
            "Snow Golem", "Squid", "Strider", "Tadpole", "Trader Llama",
            "Tropical Fish", "Turtle", "Villager", "Wandering Trader",
            "Wolf", "Zombie Horse"
    ));

    List<String>disabledMobs = new ArrayList<>();

    public void setDefault(){
        List<String> allMobs =new ArrayList<>();
        allMobs.addAll(hostileMobs);
        allMobs.addAll(friendlyMobs);
        for (String s: allMobs ){
            Main.getMobConfig().set(s, true);
        }
        Main.getMobConfig().save();
    }

    private void fillDisableList(){
        List<String> allMobs =new ArrayList<>();
        allMobs.addAll(hostileMobs);
        allMobs.addAll(friendlyMobs);
       for (String s: allMobs){
           if (!Main.getMobConfig().getAllowed(s))disabledMobs.add(s);
       }
    }

    public void changeMobSpawning(String name, Boolean allowSpawning){
        Main.getMobConfig().set(name, allowSpawning);
        Main.getMobConfig().save();
    }

    @EventHandler
    public void onMobspawn(EntitySpawnEvent event){
        fillDisableList();
        if (disabledMobs.contains(event.getEntityType().toString())) event.setCancelled(true);
    }

}
