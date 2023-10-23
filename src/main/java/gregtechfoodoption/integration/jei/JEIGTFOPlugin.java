package gregtechfoodoption.integration.jei;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.item.GTFOFoodStats;
import gregtechfoodoption.item.food.GTFOFoodUseManager;
import gregtechfoodoption.potion.LacingEntry;
import it.unimi.dsi.fastutil.objects.ObjectArrays;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@JEIPlugin
public class JEIGTFOPlugin implements IModPlugin {

    private IIngredientBlacklist itemBlacklist;
    private IIngredientRegistry iItemRegistry;
    public static final List<ItemStack> itemStacksToHide = new ArrayList<>();
    public static final List<FluidStack> fluidsToHide = new ArrayList<>();
    public static final List<ItemStack> FOOD_ITEMS = new ArrayList<>();


    @Override
    public void register(@Nonnull IModRegistry registry) {
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
                    if (manager.getFoodStats() instanceof GTFOFoodStats // A sanity check can't hurt
                            && ((GTFOFoodStats) manager.getFoodStats()).stackSupplier.get() != ItemStack.EMPTY) {
                        registry.addRecipes(ObjectSets.singleton(new EatingRecipeInfo(metaValueItem.getStackForm(),
                                ((GTFOFoodStats) manager.getFoodStats()).stackSupplier.get())),
                                GTFOValues.MODID + ":eating_recipe");
                    }
                }
            }
        }
    }
}