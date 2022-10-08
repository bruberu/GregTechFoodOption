package gregtechfoodoption.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IIngredientRegistry;
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
    public void register(@Nonnull IModRegistry iModRegistry)
    {
        itemBlacklist = iModRegistry.getJeiHelpers().getIngredientBlacklist();
        iItemRegistry = iModRegistry.getIngredientRegistry();
        itemStacksToHide.forEach(itemBlacklist::addIngredientToBlacklist);
        fluidsToHide.forEach(itemBlacklist::addIngredientToBlacklist);
    }
}