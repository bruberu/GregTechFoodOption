package com.bruberu.gregtechfoodoption.integration.jei;

import com.bruberu.gregtechfoodoption.GregTechFoodOption;
import com.bruberu.gregtechfoodoption.integration.jei.multi.*;
import com.bruberu.gregtechfoodoption.machines.GTFOTileEntities;
import com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps;
import com.bruberu.gregtechfoodoption.recipe.multiblock.ElectricBakingOvenRecipeMap;
import gregtech.api.recipes.RecipeMap;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

import java.util.List;
import java.util.stream.Collectors;

@JEIPlugin
public class JEIGTFOPlugin implements IModPlugin {
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new GTFOMultiblockInfoCategory(registry.getJeiHelpers()));

        registry.addRecipeCategories(new BakingOvenRecipeCategory(registry.getJeiHelpers().getGuiHelper()));

        registry.addRecipeCategories(new NoEnergyRecipeCategory(ElectricBakingOvenRecipeMap.INSTANCE, registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry) {
        GTFOMultiblockInfoCategory.registerRecipes(registry);

        String bakingOvenId = GregTechFoodOption.MODID + ":" + "baking_oven";
        registry.addRecipes(GTFORecipeMaps.BAKING_OVEN_RECIPES.stream()
                .map(BakingOvenRecipeWrapper::new)
                .collect(Collectors.toList()), bakingOvenId);
        registry.addRecipeCatalyst(GTFOTileEntities.BAKING_OVEN.getStackForm(), bakingOvenId);

        String electricBakingOvenId = GregTechFoodOption.MODID + ":" + "electric_baking_oven";
        registry.addRecipes(GTFORecipeMaps.ELECTRIC_BAKING_OVEN_RECIPES.getRecipeList().stream()
                .map(NoEnergyRecipeWrapper::new)
                .collect(Collectors.toList()), electricBakingOvenId);
        registry.addRecipeCatalyst(GTFOTileEntities.ELECTRIC_BAKING_OVEN.getStackForm(), electricBakingOvenId);

        String culinaryGeneratorId = GregTechFoodOption.MODID + ":" + "culinary_generator";
        registry.addRecipes(GTFORecipeMaps.CULINARY_GENERATOR_RECIPES.getRecipeList().stream()
                .map(NoEnergyRecipeWrapper::new)
                .collect(Collectors.toList()), culinaryGeneratorId);
        registry.addRecipeCatalyst(GTFOTileEntities.CULINARY_GENERATOR.getStackForm(), culinaryGeneratorId);
    }
}