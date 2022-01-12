package gregtechfoodoption.recipe.chain;

import gregtechfoodoption.item.GTFOMetaItem;
import net.minecraft.init.Items;

import static gregtechfoodoption.recipe.GTFORecipeMaps.MICROWAVE_RECIPES;

public class MicrowaveChain {
    public static void init() {
        MICROWAVE_RECIPES.recipeBuilder().EUt(16).duration(200)
                .input(Items.MUSHROOM_STEW)
                .output(GTFOMetaItem.HOT_MUSHROOM_STEW)
                .buildAndRegister();
        MICROWAVE_RECIPES.recipeBuilder().EUt(16).duration(200)
                .input(Items.RABBIT_STEW)
                .output(GTFOMetaItem.HOT_RABBIT_STEW)
                .buildAndRegister();
        MICROWAVE_RECIPES.recipeBuilder().EUt(16).duration(200)
                .input(Items.BEETROOT_SOUP)
                .output(GTFOMetaItem.HOT_BEETROOT_SOUP)
                .buildAndRegister();
    }
}
