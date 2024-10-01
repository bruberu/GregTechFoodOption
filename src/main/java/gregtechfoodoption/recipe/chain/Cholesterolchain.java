package gregtechfoodoption.recipe.chain;

import com.cleanroommc.groovyscript.compat.vanilla.OreDict;
import crafttweaker.api.item.IItemStack;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockCleanroomCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.item.GTFOMetaItem;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.MIXER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;
import static gregtechfoodoption.recipe.GTFORecipeMaps.*;
import static gregtechfoodoption.recipe.GTFORecipeMaps.MULTICOOKER_RECIPES;

public class Cholesterolchain {
    public static void init() {
        CENTRIFUGE_RECIPES.recipeBuilder().EUt(24).duration(100)
                .fluidInputs(Yolk.getFluid(1000))
                .fluidOutputs(EmulsifiedYolk.getFluid(1000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidInputs(EmulsifiedYolk.getFluid(1000))
                .fluidOutputs(yolkethanol.getFluid(1000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().EUt(24).duration(100)
                .fluidInputs(yolkethanol.getFluid(1000))
                .fluidOutputs(lipidsolvent.getFluid(1000))
                .buildAndRegister();

        DISTILLERY_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(lipidsolvent.getFluid(1000))
                .fluidOutputs(lipidsolution.getFluid(500))
                .buildAndRegister();

        AUTOCLAVE_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(lipidsolution.getFluid(1000))
                .fluidOutputs(Cholesterol.getFluid(1000))
                .buildAndRegister();
    }
}

