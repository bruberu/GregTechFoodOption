package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.init.Items;

import static gregtech.api.recipes.RecipeMaps.LATHE_RECIPES;
import static gregtech.api.recipes.RecipeMaps.MIXER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;
import static gregtechfoodoption.recipe.GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES;

// Kebab Chain caus why not?!
public class KebabChain {
    public static void init() {
        // Core Kebab Stuff
        LATHE_RECIPES.recipeBuilder().EUt(30).duration(25)
                .input(stickLong, Steel, 1)
                .outputs(SKEWER.getStackForm(2))
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().EUt(30).duration(120)
                .input(stick, Steel, 1)
                .outputs(SKEWER.getStackForm(1))
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().EUt(40).duration(25)
                .input(stickLong, StainlessSteel, 1)
                .outputs(SKEWER.getStackForm(4))
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().EUt(40).duration(120)
                .input(stick, StainlessSteel, 1)
                .outputs(SKEWER.getStackForm(2))
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().EUt(200).duration(200)
                .input(stickLong, Titanium, 1)
                .outputs(SKEWER.getStackForm(8))
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().EUt(200).duration(100)
                .input(stick, Titanium, 1)
                .outputs(SKEWER.getStackForm(3))
                .buildAndRegister();

        //Kubide Line
        ModHandler.addShapedRecipe("gtfo_hand_kubide_kebab_meat", KubideMeat.getItemStack(4),
                "STO", "MMM", "FMF",
                'F', Fat.getItemStack(),
                'M', OreDictUnifier.get(dust, Meat),
                'S', OreDictUnifier.get(dust, Salt),
                'O', ONION,
                'T', TOMATO);
        ModHandler.addShapedRecipe("gtfo_hand_kubide_kebab", KEBAB_KUBIDEH.getStackForm(),
                "RMM", "SMM", "KTS",
                'K', SKEWER,
                'M', KubideMeat.getItemStack(),
                'S', OreDictUnifier.get(dustSmall, Salt),
                'T', TOMATO,
                'R',OreDictUnifier.get(GTFOValues.craftingToolRollingPin));

        MIXER_RECIPES.recipeBuilder().EUt(30).duration(90)
                .input(dust, Meat, 4)
                .inputs(ONION_SLICE.getStackForm(4), MUSHROOM_SLICE.getStackForm(3), GTFOMaterialHandler.Fat.getItemStack(1))
                .fluidInputs(GTFOMaterialHandler.TomatoSauce.getFluid(400))
                .outputs(KubideMeat.getItemStack(5))
                .notConsumable(new IntCircuitIngredient(0))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(30).duration(120)
                .input(dust, Meat, 4)
                .inputs(ONION_SLICE.getStackForm(4), MUSHROOM_SLICE.getStackForm(4), Zest.getItemStack(4), GTFOMaterialHandler.Fat.getItemStack(1))
                .fluidInputs(GTFOMaterialHandler.TomatoSauce.getFluid(400))
                .outputs(KubideMeat.getItemStack(15))
                .notConsumable(new IntCircuitIngredient(1))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(30).duration(120)
                .inputs(CHUM.getStackForm(8), ONION_SLICE.getStackForm(4), Zest.getItemStack(4), GTFOMaterialHandler.Fat.getItemStack(2))
                .fluidInputs(GTFOMaterialHandler.TomatoSauce.getFluid(400))
                .outputs(KubideMeat.getItemStack(30))
                .notConsumable(new IntCircuitIngredient(2))
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(27).duration(20)
                .inputs(KubideMeat.getItemStack(5), TOMATO_SLICE.getStackForm(4), SKEWER.getStackForm(2))
                .input(dustSmall, Salt)
                .output(KEBAB_KUBIDEH, 2)
                .buildAndRegister();

        //misc Kebabs (Intermediates to Mix Kebab)
        ModHandler.addShapedRecipe("gtfo_hand_barg_kebab_meat", BargMeat.getItemStack(4),
                "SML", "MOM", "ZMZ",
                'S', OreDictUnifier.get(dust, Salt),
                'M', OreDictUnifier.get(dust, Meat),
                'O', ONION,
                'L', OLIVE,
                'Z', Zest.getItemStack());
        ModHandler.addShapedRecipe("gtfo_hand_barg_kebab", KEBAB_BARG.getStackForm(),
                "RMM", "SMM", "KTS",
                'M', BargMeat.getItemStack(),
                'K', SKEWER,
                'S', OreDictUnifier.get(dustSmall, Salt),
                'T', TOMATO,
                'R',OreDictUnifier.get(GTFOValues.craftingToolRollingPin));

        MIXER_RECIPES.recipeBuilder().EUt(30).duration(40)
                .input(dust, Meat, 4)
                .input(dust, Salt, 2)
                .inputs(ONION_SLICE.getStackForm(8), Zest.getItemStack(2))
                .fluidInputs(OliveOil.getFluid(500))
                .outputs(BargMeat.getItemStack(10))
                .notConsumable(new IntCircuitIngredient(0))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(30).duration(100)
                .inputs(CHUM.getStackForm(8))
                .input(dust, Salt, 2)
                .inputs(ONION_SLICE.getStackForm(8), Zest.getItemStack(2))
                .fluidInputs(OliveOil.getFluid(1000))
                .outputs(BargMeat.getItemStack(20))
                .notConsumable(new IntCircuitIngredient(1))
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(27).duration(80)
                .inputs(BargMeat.getItemStack(5), Zest.getItemStack(4), TOMATO_SLICE.getStackForm(4), SKEWER.getStackForm(3))
                .output(KEBAB_BARG, 3)
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(27).duration(80)
                .inputs(BargMeat.getItemStack(5), Zest.getItemStack(4), SKEWER.getStackForm(3))
                .fluidInputs(TomatoSauce.getFluid(100))
                .output(KEBAB_BARG, 3)
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(120).duration(200)
                .inputs(KEBAB_BARG_COOKED.getStackForm(2), KEBAB_KUBIDEH_COOKED.getStackForm(), TOMATO.getStackForm(3), ONION.getStackForm(2), LEMON.getStackForm())
                .fluidInputs(Stearin.getFluid(1000), LemonExtract.getFluid(250))
                .outputs(KEBAB_SOLTANI.getStackForm(2), SKEWER.getStackForm())
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(100)
                .inputs(ONION.getStackForm(3))
                .input(dustTiny, Salt)
                .inputs(SKEWER.getStackForm())
                .outputs(KEBAB_ONION.getStackForm())
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(100)
                .inputs(TOMATO.getStackForm(2))
                .input(dustTiny, Salt)
                .inputs(SKEWER.getStackForm())
                .outputs(KEBAB_TOMATO.getStackForm())
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(400)
                .inputs(CHUM.getStackForm(8), BANANA_PEEL.getStackForm(2), ONION.getStackForm(), MashedPotato.getItemStack(4), SKEWER.getStackForm(4))
                .fluidInputs(Yolk.getFluid(200), Stearin.getFluid(400))
                .outputs(KEBAB_CHUM.getStackForm(4))
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(100).duration(1000)
                .inputs(KEBAB_CHUM_COOKED.getStackForm(2))
                .inputs(KEBAB_KUBIDEH_COOKED.getStackForm(5))
                .inputs(BURGER_CHUM.getStackForm(14))
                .input(Items.BUCKET)
                .fluidInputs(RabbitStew.getFluid(250))
                .fluidInputs(HotFryingOil.getFluid(250))
                .fluidInputs(PotatoJuice.getFluid(4000))
                .outputs(KEBAB_CHUM_BUCKET.getStackForm(), SKEWER.getStackForm(7))
                .buildAndRegister();

        GTFOUtils.addBakingOvenRecipes(KEBAB_KUBIDEH.getStackForm(), KEBAB_KUBIDEH_COOKED.getStackForm(), 600, 360, 2);
        GTFOUtils.addBakingOvenRecipes(KEBAB_BARG.getStackForm(), KEBAB_BARG_COOKED.getStackForm(), 600, 375, 2);
        GTFOUtils.addBakingOvenRecipes(KEBAB_TOMATO.getStackForm(), KEBAB_TOMATO_COOKED.getStackForm(), 500, 350, 2);
        GTFOUtils.addBakingOvenRecipes(KEBAB_ONION.getStackForm(), KEBAB_ONION_COOKED.getStackForm(), 500, 350, 2);
        GTFOUtils.addBakingOvenRecipes(KEBAB_CHUM.getStackForm(), KEBAB_CHUM_COOKED.getStackForm(), 500, 350, 2);
    }
}
