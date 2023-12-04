package gregtechfoodoption.integration.jei;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.category.GTRecipeCategory;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.utils.ModularUIGuiHandler;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.item.GTFOFoodStats;
import gregtechfoodoption.item.food.GTFOFoodUseManager;
import gregtechfoodoption.potion.LacingEntry;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JEIPlugin
public class JEIGTFOPlugin implements IModPlugin {

    private IIngredientBlacklist itemBlacklist;
    private IIngredientRegistry iItemRegistry;
    private IRecipeTransferRegistry iRecipeTransferRegistry;
    public static final List<ItemStack> itemStacksToHide = new ArrayList<>();
    public static final List<FluidStack> fluidsToHide = new ArrayList<>();
    public static final List<ItemStack> FOOD_ITEMS = new ArrayList<>();


    @Override
    public void register(@Nonnull IModRegistry registry) {
        iRecipeTransferRegistry = registry.getRecipeTransferRegistry();
        ModularUIGuiHandler kitchenGuiHandler = new ModularUIGuiHandler(registry.getJeiHelpers().recipeTransferHandlerHelper());
        for (RecipeMap<?> recipeMap : RecipeMap.getRecipeMaps()) {
            if (!recipeMap.isHidden) {
                for (Map.Entry<GTRecipeCategory, List<Recipe>> entry : recipeMap.getRecipesByCategory().entrySet()) {
                    registry.getRecipeTransferRegistry().addRecipeTransferHandler(kitchenGuiHandler, entry.getKey().getUniqueID());
                }
            }
        }
        itemBlacklist = registry.getJeiHelpers().getIngredientBlacklist();
        iItemRegistry = registry.getIngredientRegistry();
        itemStacksToHide.forEach(itemBlacklist::addIngredientToBlacklist);
        fluidsToHide.forEach(itemBlacklist::addIngredientToBlacklist);


        LacingEntry.LACING_REGISTRY.forEach(entry -> registry.addRecipes(ObjectSets.singleton(new LacingInfo(entry)), GTFOValues.MODID + ":lacing_info"));

        for (MetaTileEntity cannerTier : MetaTileEntities.CANNER) {
            if (cannerTier != null)
                registry.addRecipeCatalyst(cannerTier.getStackForm(), GTFOValues.MODID + ":lacing_info");
        }

        initializeFoodItems(registry);
    }


    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new LacingCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new EatingRecipeCategory(registry.getJeiHelpers().getGuiHelper()));

    }

    public static void initializeFoodItems(IModRegistry registry) {
        for (MetaItem<?> metaItem : MetaItem.getMetaItems()) {
            for (MetaItem.MetaValueItem metaValueItem : metaItem.getAllItems()) {
                if (metaValueItem.getUseManager() instanceof GTFOFoodUseManager manager) {
                    FOOD_ITEMS.add(metaValueItem.getStackForm());

                    // Handle eating recipes
                    if (manager.getFoodStats() instanceof GTFOFoodStats stats // A sanity check can't hurt
                            && stats.stackSupplier.get() != ItemStack.EMPTY) {
                        registry.addRecipes(ObjectSets.singleton(new EatingRecipeInfo(metaValueItem.getStackForm(),
                                        stats.stackSupplier.get())),
                                GTFOValues.MODID + ":eating_recipe");
                    }
                }
            }
        }
    }
}