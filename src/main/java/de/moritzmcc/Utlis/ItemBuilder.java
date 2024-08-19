package de.moritzmcc.Utlis;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {
    ItemStack itemStack;
    ItemMeta itemMeta;


    public ItemBuilder(Material material){
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder withName(String name){
        itemMeta.setDisplayName(name);
        return this;
    }

    public ItemBuilder withAmount(int amount){
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder withLore(String...lore){
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemStack build(){
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
