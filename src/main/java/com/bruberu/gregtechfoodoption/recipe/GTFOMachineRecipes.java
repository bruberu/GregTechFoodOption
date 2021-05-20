package com.bruberu.gregtechfoodoption.recipe;

import com.bruberu.gregtechfoodoption.machines.GTFOTileEntities;
import gregicadditions.recipes.helper.GACraftingComponents;
import gregtech.api.items.OreDictNames;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;

import java.util.Arrays;

import static com.bruberu.gregtechfoodoption.machines.GTFOTileEntities.BIOREACTOR;
import static com.bruberu.gregtechfoodoption.machines.GTFOTileEntities.SLICER;
import static gregicadditions.recipes.helper.GACraftingComponents.*;

public class GTFOMachineRecipes {
    public static void init()
    {
        registerMachineRecipe(0, BIOREACTOR,
                "RUR", "SHG", "CIP",
                'R', CIRCUIT,
                'C', CABLE_SINGLE,
                'U', PUMP,
                'P', PLATE,
                'S', SENSOR,
                'H', HULL,
                'G', GLASS,
                'I', OreDictUnifier.get("platePolytetrafluoroethylene")
                );
        registerMachineRecipe(SLICER,
                "PCA", "SHC", "LOA",
                'P', PISTON,
                'C', CIRCUIT,
                'A', CABLE_SINGLE,
                'S', OreDictNames.craftingDiamondBlade,
                'H', HULL,
                'L', PLATE_DENSE,
                'O', CONVEYOR
        );
    }

    /**
     * Tiered Machine Recipe Registration method.
     * An example of how to use it:
     *
     * <cr>
     *     registerMachineRecipe(GATileEntities.DIODES,
     *                 "CCC", "XMX", "CCC",
     *                 'M', HULL,
     *                 'C', CABLE_SINGLE,
     *                 'X', MetaItems.SMALL_COIL);
     * </cr>
     *
     * - The MetaTileEntity input can be an Array, a List, or an Array of
     * - The Recipe Input must be Strings of up to 3 characters.
     * - The inputs must be in pairs of (char, [input]).
     * - Inputs can be a:
     *     - String representing an OreDictionary entry
     *     - ItemStack
     *     - {@link GACraftingComponents} or {@link gregtech.loaders.recipe.CraftingComponent}
     *     - {@link gregtech.api.unification.stack.UnificationEntry}
     *
     * @param ingredientOffset The starting position in the Array or List of MetaTileEntities
     * @param metaTileEntities The group of MetaTileEntities to register the same recipe for.
     * @param recipe           The Recipe Layout, detailed above.
     */
    public static <T extends MetaTileEntity & ITieredMetaTileEntity> void registerMachineRecipe(int ingredientOffset, T[] metaTileEntities, Object... recipe) {
        for (T metaTileEntity : metaTileEntities) {
            if (metaTileEntity != null)
                ModHandler.addShapedRecipe(String.format("ga_%s", metaTileEntity.getMetaName()), metaTileEntity.getStackForm(),
                        prepareRecipe(metaTileEntity.getTier() + ingredientOffset, Arrays.copyOf(recipe, recipe.length)));
        }
    }
    private static Object[] prepareRecipe(int tier, Object... recipe) {
        for (int i = 3; i < recipe.length; i++) {
            if (recipe[i] instanceof GACraftingComponents) {
                recipe[i] = ((GACraftingComponents) recipe[i]).getIngredient(tier);
            }
        }
        return recipe;
    }

    public static void registerMachineRecipe(GTFOTileEntities.MTE<?>[] metaTileEntities, Object... recipe) {
        for (GTFOTileEntities.MTE<?> metaTileEntity : metaTileEntities) {
            if (metaTileEntity != null)
                ModHandler.addShapedRecipe(String.format("ga_%s", metaTileEntity.getMetaTileEntity().getMetaName()), metaTileEntity.getMetaTileEntity().getStackForm(),
                        prepareRecipe(metaTileEntity.getITieredMetaTileEntity().getTier(), Arrays.copyOf(recipe, recipe.length)));
        }
    }
}
