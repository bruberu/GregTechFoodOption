package com.bruberu.gregtechfoodoption.recipe.chain;

import com.bruberu.gregtechfoodoption.GTFOConfig;
import com.bruberu.gregtechfoodoption.GTFOMaterialHandler;
import com.bruberu.gregtechfoodoption.item.GTFOMetaItem;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.common.items.MetaItems;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SeedsChain {
    public static void init() {

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().EUt(6).duration(20)
                .inputs(MetaItems.PLANT_BALL.getStackForm())
                .chancedOutput(GTFOMaterialHandler.PopcornKernel.getItemStack(), 2000, 250)
                .buildAndRegister();
        GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(6).duration(20)
                .inputs(new ItemStack(Blocks.LEAVES, 1, BlockPlanks.EnumType.JUNGLE.getMetadata()))
                .chancedOutput(GTFOMetaItem.LEMON.getStackForm(), 2000, 250)
                .chancedOutput(GTFOMetaItem.LIME.getStackForm(), 2000, 250)
                .buildAndRegister();

        if(GTFOConfig.gtfoMiscConfig.centrifugeSeeds) {
            ItemStack[] seeds = new ItemStack[]{
                    GTFOMetaItem.LEMON.getStackForm(),
                    GTFOMetaItem.LIME.getStackForm(),
                    GTFOMaterialHandler.PopcornKernel.getItemStack()
            };
            for (ItemStack seed : seeds) {
                RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().EUt(5).duration(144)
                        .inputs(seed)
                        .fluidOutputs(Materials.Methane.getFluid(34))
                        .buildAndRegister();
            }
        }
    }
}
