package de.moritzmcc.pvp;

import de.moritzmcc.worldmanage.Main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

public class SoupCraftingRecipe {

    public static Recipe addCocoRecraft() {

        ShapelessRecipe soupRecipe = new ShapelessRecipe( new ItemStack(Material.MUSHROOM_STEW));
        soupRecipe.addIngredient(Material.BOWL);
        soupRecipe.addIngredient(Material.COCOA_BEANS);


        return soupRecipe;
    }

    public static Recipe addCactusRecraft() {

        ShapelessRecipe soupRecipe = new ShapelessRecipe( new ItemStack(Material.MUSHROOM_STEW));
        soupRecipe.addIngredient(Material.BOWL);
        soupRecipe.addIngredient(Material.CACTUS);

        return soupRecipe;
    }


}
