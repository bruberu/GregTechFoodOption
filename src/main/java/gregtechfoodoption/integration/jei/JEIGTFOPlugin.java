package gregtechfoodoption.integration.jei;

import gregtechfoodoption.GregTechFoodOption;
import gregtechfoodoption.integration.jei.multi.GTFOMultiblockInfoCategory;
import gregtechfoodoption.integration.jei.multi.NoEnergyRecipeCategory;
import gregtechfoodoption.integration.jei.multi.NoEnergyRecipeWrapper;
import com.bruberu.gregtechfoodoption.integration.jei.multi.*;
import gregtechfoodoption.machines.GTFOTileEntities;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import gregtechfoodoption.recipe.multiblock.ElectricBakingOvenRecipeMap;
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

        registry.addRecipeCategories(new NoEnergyRecipeCategory(ElectricBakingOvenRecipeMap.INSTANCE, registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry) {
        GTFOMultiblockInfoCategory.registerRecipes(registry);

        String electricBakingOvenId = GregTechFoodOption.MODID + ":" + "electric_baking_oven";
        registry.addRecipes(GTFORecipeMaps.ELECTRIC_BAKING_OVEN_RECIPES.getRecipeList().stream()
                .map(NoEnergyRecipeWrapper::new)
                .collect(Collectors.toList()), electricBakingOvenId);
        registry.addRecipeCatalyst(GTFOTileEntities.ELECTRIC_BAKING_OVEN.getStackForm(), electricBakingOvenId);
    }
}