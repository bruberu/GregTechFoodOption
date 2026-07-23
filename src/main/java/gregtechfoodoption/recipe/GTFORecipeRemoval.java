package gregtechfoodoption.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

import de.ellpeck.actuallyadditions.mod.items.InitItems;
import de.ellpeck.actuallyadditions.mod.items.metalists.TheMiscItems;
import gregtech.api.recipes.ModHandler;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.integration.enderio.GTFOEIORecipeHandler;

public class GTFORecipeRemoval {

    public static void init() {
        if (Loader.isModLoaded("nuclearcraft")) {

        }
        if (Loader.isModLoaded("actuallyadditions")) {
            if (GTFOConfig.gtfoChainsConfig.deleteBreadRecipe) {
                ModHandler.removeFurnaceSmelting(new ItemStack(InitItems.itemMisc, 1, TheMiscItems.DOUGH.ordinal()));
                ModHandler.removeRecipeByName(new ResourceLocation("actuallyadditions:recipes218"));
            }
        }
        if (Loader.isModLoaded(GTFOValues.MODID_EIO)) {
            GTFOEIORecipeHandler.removeEnderios();
        }
    }
}
