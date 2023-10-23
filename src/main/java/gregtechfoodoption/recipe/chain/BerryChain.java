package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.recipe.GTFORecipeMaps;
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
        ModHandler.addShapelessRecipe("gtfo_berry_medley_1x_b", BERRY_MEDLEY.getStackForm(),
                RASPBERRY.getStackForm(), STRAWBERRY.getStackForm(), BLACK_CURRANT.getStackForm(), new ItemStack(Items.BOWL));
        ModHandler.addShapelessRecipe("gtfo_berry_medley_1x_c", BERRY_MEDLEY.getStackForm(),
                CRANBERRY.getStackForm(), RED_CURRANT.getStackForm(), WHITE_CURRANT.getStackForm(), new ItemStack(Items.BOWL));

        ModHandler.addShapelessRecipe("gtfo_berry_medley_2x_a", BERRY_MEDLEY.getStackForm(2),
                BLUEBERRY.getStackForm(), RASPBERRY.getStackForm(), WHITE_CURRANT.getStackForm(), LINGONBERRY.getStackForm(), new ItemStack(Items.BOWL), new ItemStack(Items.BOWL));
        ModHandler.addShapelessRecipe("gtfo_berry_medley_2x_b", BERRY_MEDLEY.getStackForm(2),
                STRAWBERRY.getStackForm(), CRANBERRY.getStackForm(), BLACK_CURRANT.getStackForm(), BLACKBERRY.getStackForm(), new ItemStack(Items.BOWL), new ItemStack(Items.BOWL));
        ModHandler.addShapelessRecipe("gtfo_berry_medley_2x_c", BERRY_MEDLEY.getStackForm(2),
                RASPBERRY.getStackForm(), CRANBERRY.getStackForm(), RED_CURRANT.getStackForm(), LINGONBERRY.getStackForm(), new ItemStack(Items.BOWL), new ItemStack(Items.BOWL));

        GTFORecipeMaps.MULTICOOKER_RECIPES.recipeBuilder()
                .EUt(16).duration(240)
                .input("cropElderberry")
                .input(Items.SUGAR)
                .fluidInputs(LemonExtract.getFluid(150))
                .fluidOutputs(ElderberryJam.getFluid(1000))
                .buildAndRegister();

        GTFORecipeMaps.MULTICOOKER_RECIPES.recipeBuilder()
                .EUt(16).duration(240)
                .input("cropLingonberry")
                .input(Items.SUGAR)
                .fluidOutputs(LingonberryJam.getFluid(1000))
                .buildAndRegister();

        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder()
                .EUt(8).duration(20)
                .fluidInputs(LingonberryJam.getFluid(100))
                .input("listAllberrytart", 1)
                .input("listAllberrysweet", 1)
                .input(Items.BOWL)
                .outputs(BERRY_MEDLEY.getStackForm())
                .buildAndRegister();

        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder()
                .EUt(8).duration(20)
                .fluidInputs(ElderberryJam.getFluid(50))
                .input("listAllberrytart", 1)
                .input("listAllberrysweet", 1)
                .input(Items.BOWL)
                .outputs(BERRY_MEDLEY.getStackForm())
                .buildAndRegister();
    }
}
