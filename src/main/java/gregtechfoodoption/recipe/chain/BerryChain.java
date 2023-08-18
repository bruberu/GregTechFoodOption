package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtechfoodoption.GTFOValues;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.GTFOMaterialHandler.LemonLimeSludge;
import static gregtechfoodoption.item.GTFOMetaItem.*;

public class BerryChain {
    public static void init() {
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(CranberrySludge.getFluid(500))
                .input(Items.SUGAR, 6)
                .fluidOutputs(CranberrySodaSyrup.getFluid(500))
                .EUt(60)
                .duration(40)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(CranberryExtract.getFluid(1500))
                .fluidOutputs(CranberrySludge.getFluid(1000))
                .EUt(30)
                .duration(140)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(CarbonatedWater.getFluid(1000), CranberrySodaSyrup.getFluid(1000))
                .fluidOutputs(EtirpsCranberry.getFluid(2000))
                .EUt(60)
                .duration(20)
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .fluidInputs(EtirpsCranberry.getFluid(500))
                .inputs(PLASTIC_BOTTLE.getStackForm())
                .outputs(ETIRPS_CRANBERRY.getStackForm())
                .EUt(30)
                .duration(20)
                .buildAndRegister();

        ModHandler.addShapelessRecipe("gtfo_berry_medley_1x_a", BERRY_MEDLEY.getStackForm(),
                BLUEBERRY.getStackForm(), BLACKBERRY.getStackForm(), LINGONBERRY.getStackForm(), new ItemStack(Items.BOWL));
        ModHandler.addShapelessRecipe("gtfo_berry_medley_1x_a", BERRY_MEDLEY.getStackForm(),
                BLACK_CURRANT.getStackForm(), RASPBERRY.getStackForm(), LINGONBERRY.getStackForm(), new ItemStack(Items.BOWL));

    }
}
