package com.bruberu.gregtechfoodoption.integration.jei;

import com.bruberu.gregtechfoodoption.GregTechFoodOption;
import com.bruberu.gregtechfoodoption.integration.jei.multi.BakingOvenRecipeCategory;
import com.bruberu.gregtechfoodoption.integration.jei.multi.BakingOvenRecipeWrapper;
import com.bruberu.gregtechfoodoption.integration.jei.multi.GTFOMultiblockInfoCategory;
import com.bruberu.gregtechfoodoption.machines.GTFOTileEntities;
import com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

import java.util.stream.Collectors;

@JEIPlugin
public class JEIGTFOPlugin implements IModPlugin {
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new GTFOMultiblockInfoCategory(registry.getJeiHelpers()));

        registry.addRecipeCategories(new BakingOvenRecipeCategory(registry.getJeiHelpers().getGuiHelper()));

    }

    @Override
    public void register(IModRegistry registry) {
        GTFOMultiblockInfoCategory.registerRecipes(registry);

        String bakingOvenId = GregTechFoodOption.MODID + ":" + "baking_oven";
        registry.addRecipes(GTFORecipeMaps.BAKING_OVEN_RECIPES.stream()
                .map(BakingOvenRecipeWrapper::new)
                .collect(Collectors.toList()), bakingOvenId);
        registry.addRecipeCatalyst(GTFOTileEntities.BAKING_OVEN.getStackForm(), bakingOvenId);
    }
}