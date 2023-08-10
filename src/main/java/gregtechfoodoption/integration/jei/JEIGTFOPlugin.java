package gregtechfoodoption.integration.jei;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtechfoodoption.GTFOValues;
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

    @Override
    public void register(@Nonnull IModRegistry registry)
    {
        LacingInfo.initializeFoodItems();

        itemBlacklist = registry.getJeiHelpers().getIngredientBlacklist();
        iItemRegistry = registry.getIngredientRegistry();
        itemStacksToHide.forEach(itemBlacklist::addIngredientToBlacklist);
        fluidsToHide.forEach(itemBlacklist::addIngredientToBlacklist);


        LacingEntry.LACING_REGISTRY.forEach(entry -> registry.addRecipes(ObjectSets.singleton(new LacingInfo(entry)), GTFOValues.MODID + ":lacing_info"));

        for (MetaTileEntity cannerTier : MetaTileEntities.CANNER) {
            if (cannerTier != null)
                registry.addRecipeCatalyst(cannerTier.getStackForm(), GTFOValues.MODID + ":lacing_info");
        }
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new LacingCategory(registry.getJeiHelpers().getGuiHelper()));
    }


}