package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.item.GTFOMetaItem;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class RussianChain {
    public static void init() {
        ModHandler.addShapelessRecipe("pelmeni_hand", GTFOMetaItem.UNCOOKED_PELMENI.getStackForm(), GTFOMetaItem.DOUGH.getStackForm(), OreDictUnifier.get(OrePrefix.dust, Materials.Meat));
        ModHandler.addShapelessRecipe("pelmeni_hand_3", GTFOMetaItem.UNCOOKED_PELMENI.getStackForm(3), GTFOMetaItem.DOUGH.getStackForm(3), OreDictUnifier.get(OrePrefix.dust, Materials.Meat), new ItemStack(Blocks.BROWN_MUSHROOM));


        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(100)
                .inputs(GTFOMetaItem.DOUGH.getStackForm())
                .input(OrePrefix.dust, Materials.Meat)
                .circuitMeta(1)
                .outputs(GTFOMetaItem.UNCOOKED_PELMENI.getStackForm())
                .buildAndRegister();
        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(100)
                .inputs(GTFOMetaItem.DOUGH.getStackForm(3))
                .input(OrePrefix.dust, Materials.Meat)
                .input(Blocks.BROWN_MUSHROOM)
                .circuitMeta(2)
                .outputs(GTFOMetaItem.UNCOOKED_PELMENI.getStackForm(3))
                .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().EUt(16).duration(400)
                .fluidInputs(Materials.Milk.getFluid(8000))
                .circuitMeta(2)
                .fluidOutputs(GTFOMaterialHandler.LacticAcidBacteria.getFluid(2))
                .buildAndRegister();


        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(4).duration(3000)
                .fluidInputs(GTFOMaterialHandler.PasteurizedMilk.getFluid(8000))
                .notConsumable(GTFOMaterialHandler.LacticAcidBacteria.getFluid(8))
                .fluidOutputs(GTFOMaterialHandler.SourCream.getFluid(8000))
                .buildAndRegister();

        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(100)
                .inputs(GTFOMetaItem.UNCOOKED_PELMENI.getStackForm(), GTFOMaterialHandler.GratedHorseradishRoot.getItemStack(), GTFOMaterialHandler.BlackPepper.getItemStack())
                .fluidInputs(GTFOMaterialHandler.SourCream.getFluid(100))
                .outputs(GTFOMetaItem.UNCOOKED_SEASONED_PELMENI.getStackForm())
                .buildAndRegister();

        GTFORecipeMaps.MULTICOOKER_RECIPES.recipeBuilder().EUt(24).duration(2000)
                .inputs(GTFOMetaItem.UNCOOKED_PELMENI.getStackForm())
                .fluidInputs(Materials.Water.getFluid(500))
                .outputs(GTFOMetaItem.PELMENI.getStackForm())
                .circuitMeta(1)
                .buildAndRegister();
        GTFORecipeMaps.MULTICOOKER_RECIPES.recipeBuilder().EUt(24).duration(2000)
                .inputs(GTFOMetaItem.UNCOOKED_SEASONED_PELMENI.getStackForm())
                .fluidInputs(Materials.Water.getFluid(500))
                .outputs(GTFOMetaItem.SEASONED_PELMENI.getStackForm())
                .circuitMeta(2)
                .buildAndRegister();

    }
}
