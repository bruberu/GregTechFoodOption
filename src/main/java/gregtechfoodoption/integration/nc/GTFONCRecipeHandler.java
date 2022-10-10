package gregtechfoodoption.integration.nc;

import gregtech.api.recipes.ModHandler;
import gregtechfoodoption.integration.jei.JEIGTFOPlugin;
import nc.block.fluid.NCBlockFluid;
import nc.init.NCFluids;
import nc.init.NCItems;
import nc.recipe.AbstractRecipeHandler;
import nc.recipe.NCRecipes;
import nc.recipe.ingredient.FluidIngredient;
import nc.recipe.ingredient.ItemIngredient;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.UniversalBucket;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Collections;

public class GTFONCRecipeHandler {
    public static void initSmingotRemoval() {
        removeRecipeByMapAndProducts(NCRecipes.ingot_former, convertToItemIngredient(NCItems.milk_chocolate, 1));
        removeRecipeByMapAndProducts(NCRecipes.ingot_former, convertToItemIngredient(NCItems.dark_chocolate, 1));
        removeRecipeByMapAndProducts(NCRecipes.ingot_former, convertToItemIngredient(NCItems.unsweetened_chocolate, 1));
        removeRecipeByMapAndProducts(NCRecipes.ingot_former, convertToItemIngredient(NCItems.marshmallow, 1));
        removeRecipeByMapAndProducts(NCRecipes.salt_mixer, convertToFluidIngredient("milk_chocolate", 288));
        removeRecipeByMapAndProducts(NCRecipes.salt_mixer, convertToFluidIngredient("dark_chocolate", 144));
        removeRecipeByMapAndProducts(NCRecipes.salt_mixer, convertToFluidIngredient("unsweetened_chocolate", 144));
        removeRecipeByMapAndProducts(NCRecipes.extractor, new ItemIngredient[]{convertToItemIngredient(NCItems.cocoa_solids, 1)}, new FluidIngredient[]{convertToFluidIngredient("cocoa_butter", 144)});
        removeRecipeByMapAndProducts(NCRecipes.manufactory, convertToItemIngredient(NCItems.flour, 1));
        removeRecipeByMapAndProducts(NCRecipes.manufactory, convertToItemIngredient(NCItems.gelatin, 4));
        removeRecipeByMapAndProducts(NCRecipes.manufactory, convertToItemIngredient(NCItems.gelatin, 8));
        removeRecipeByMapAndProducts(NCRecipes.manufactory, convertToItemIngredient(NCItems.ground_cocoa_nibs, 1));
        removeRecipeByMapAndProducts(NCRecipes.pressurizer, convertToItemIngredient(NCItems.graham_cracker, 1));

        ModHandler.removeFurnaceSmelting(new ItemStack(Items.DYE, 1, 3));
        ModHandler.removeRecipeByName(new ResourceLocation("nuclearcraft:smore"));
        ModHandler.removeRecipeByName(new ResourceLocation("nuclearcraft:moresmore"));
        ModHandler.removeRecipeByName(new ResourceLocation("nuclearcraft:foursmore"));

        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(NCItems.flour));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(NCItems.smore));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(NCItems.moresmore));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(NCItems.foursmore));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(NCItems.gelatin));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(NCItems.ground_cocoa_nibs));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(NCItems.graham_cracker));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(NCItems.cocoa_solids));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(NCItems.cocoa_butter));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(NCItems.dark_chocolate));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(NCItems.milk_chocolate));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(NCItems.unsweetened_chocolate));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(NCItems.marshmallow));

        JEIGTFOPlugin.itemStacksToHide.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FluidRegistry.getFluid("chocolate_liquor")));
        JEIGTFOPlugin.itemStacksToHide.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FluidRegistry.getFluid("cocoa_butter")));
        JEIGTFOPlugin.itemStacksToHide.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FluidRegistry.getFluid("unsweetened_chocolate")));
        JEIGTFOPlugin.itemStacksToHide.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FluidRegistry.getFluid("dark_chocolate")));
        JEIGTFOPlugin.itemStacksToHide.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FluidRegistry.getFluid("milk_chocolate")));
        JEIGTFOPlugin.itemStacksToHide.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FluidRegistry.getFluid("gelatin")));
        JEIGTFOPlugin.itemStacksToHide.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FluidRegistry.getFluid("hydrated_gelatin")));
        JEIGTFOPlugin.itemStacksToHide.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FluidRegistry.getFluid("marshmallow")));

        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(GTFONCRecipeHandler.getFluidBlock("fluid.chocolate_liquor")));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(GTFONCRecipeHandler.getFluidBlock("fluid.cocoa_butter")));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(GTFONCRecipeHandler.getFluidBlock("fluid.unsweetened_chocolate")));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(GTFONCRecipeHandler.getFluidBlock("fluid.dark_chocolate")));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(GTFONCRecipeHandler.getFluidBlock("fluid.milk_chocolate")));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(GTFONCRecipeHandler.getFluidBlock("fluid.gelatin")));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(GTFONCRecipeHandler.getFluidBlock("fluid.hydrated_gelatin")));
        JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(GTFONCRecipeHandler.getFluidBlock("fluid.marshmallow")));
    }

    public static void removeRecipeByMapAndProducts(AbstractRecipeHandler handler, ItemIngredient... items) {
        handler.removeRecipe(handler.getRecipeFromProducts(Arrays.asList(items), Collections.emptyList()));
    }

    public static void removeRecipeByMapAndProducts(AbstractRecipeHandler handler, FluidIngredient... fluids) {
        handler.removeRecipe(handler.getRecipeFromProducts(Collections.emptyList(), Arrays.asList(fluids)));
    }

    public static void removeRecipeByMapAndProducts(AbstractRecipeHandler handler, ItemIngredient[] items, FluidIngredient[] fluids) {
        handler.removeRecipe(handler.getRecipeFromProducts(Arrays.asList(items), Arrays.asList(fluids)));
    }

    public static ItemIngredient convertToItemIngredient(Item item, int count) {
        return new ItemIngredient(new ItemStack(item, count));
    }

    public static FluidIngredient convertToFluidIngredient(String fluid, int count) {
        return new FluidIngredient(fluid, count);
    }

    public static NCBlockFluid getFluidBlock(String fluid) {
        for (Pair<Fluid, NCBlockFluid> pair : NCFluids.fluidPairList) {
            if (pair.getLeft().getUnlocalizedName().equals(fluid)) {
                return pair.getRight();
            }
        }
        return null;
    }
}
