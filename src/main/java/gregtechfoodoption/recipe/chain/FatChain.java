package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Arrays;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Calcite;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.common.items.MetaItems.CARBON_MESH;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.GTFOMaterialHandler.Sludge;
import static gregtechfoodoption.item.GTFOMetaItem.SCRAP_MEAT;

public class FatChain {
    public static void init() {
        ModHandler.addShapelessRecipe("meat_hand_recipe", ToughMeat.getItemStack(2), OreDictUnifier.get("dustWheat"), new UnificationEntry(dust, Meat), new UnificationEntry(dust, Meat), Items.WATER_BUCKET);
        GTFOUtils.getMeat().forEach(itemStack -> {
            ArrayList<ItemStack> input = new ArrayList<>();
            input.add(itemStack);
            Recipe conflict = MACERATOR_RECIPES.findRecipe(2, input, new ArrayList<>(), false);
            if (conflict != null) {
                MACERATOR_RECIPES.removeRecipe(conflict);
            }

            itemStack.setCount(8);

            MACERATOR_RECIPES.recipeBuilder().EUt(20).duration(400)
                    .inputs(itemStack)
                    .output(dust, Meat, 13)
                    .output(dustSmall, Bone, 8)
                    .outputs(Fat.getItemStack(8))
                    .chancedOutput(Fat.getItemStack(4), 5000, 2000)
                    .buildAndRegister();
            LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(256).duration(1000)
                    .input(itemStack.getItem(), 32)
                    .fluidInputs(Methanol.getFluid(4000), Chloroform.getFluid(4000))
                    .output(dust, Meat, 40)
                    .output(dust, Bone, 16)
                    .fluidOutputs(Stearin.getFluid(3200), Sludge.getFluid(12000), Chlorine.getFluid(12000))
                    .buildAndRegister();

            ModHandler.addShapelessRecipe("gtfo_hand_mince_meat" + itemStack.getDisplayName(), OreDictUnifier.get(dust, Meat), itemStack, OreDictUnifier.get("craftingToolMortar"));
        });

        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(256).duration(1000)
                .input(SCRAP_MEAT, 32)
                .fluidInputs(Methanol.getFluid(4000), Chloroform.getFluid(4000))
                .output(dust, Meat, 32)
                .output(dust, Bone, 20)
                .fluidOutputs(Stearin.getFluid(3500), Sludge.getFluid(16000), Chlorine.getFluid(12000))
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().EUt(20).duration(400)
                .input(SCRAP_MEAT, 8)
                .output(dust, Meat, 10)
                .output(dustSmall, Bone, 16)
                .outputs(Fat.getItemStack(10))
                .chancedOutput(Fat.getItemStack(4), 5000, 2000)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().EUt(30).duration(300)
                .input(dust, Meat, 3)
                .fluidOutputs(Biomass.getFluid(200), Stearin.getFluid(10))
                .buildAndRegister();

        FERMENTING_RECIPES.recipeBuilder().EUt(32).duration(1200)
                .input(SCRAP_MEAT, 1)
                .fluidInputs(Chloroform.getFluid(100))
                .output(dust, Meat, 1)
                .fluidOutputs(Stearin.getFluid(40))
                .buildAndRegister();

        GTFOUtils.getOrganicOils().forEach(f -> {
            // turning Plant Oil into Animal Oil?! Magik!
            CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                    .input(dustTiny, SodaAsh)
                    .fluidInputs(new FluidStack(f, 1000), Hydrogen.getFluid(1000))
                    .fluidOutputs(Stearin.getFluid(100))
                    .notConsumable(new IntCircuitIngredient(1))
                    .buildAndRegister();
        });

        Arrays.asList(Methanol, Ethanol).forEach(f -> {
            CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(600)
                    .input(dustTiny, SodiumHydroxide)
                    .fluidInputs(Stearin.getFluid(3000), f.getFluid(1000))
                    .fluidOutputs(Glycerol.getFluid(1000), BioDiesel.getFluid(6000))
                    .buildAndRegister();
            LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(5400)
                    .input(dust, SodiumHydroxide)
                    .fluidInputs(Stearin.getFluid(27000), f.getFluid(9000))
                    .fluidOutputs(Glycerol.getFluid(9000), BioDiesel.getFluid(54000))
                    .buildAndRegister();
        });

        EXTRACTOR_RECIPES.recipeBuilder().EUt(16).duration(10)
                .inputs(Fat.getItemStack())
                .fluidOutputs(Stearin.getFluid(100))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(16).duration(60)
                .notConsumable(MetaItems.SHAPE_MOLD_INGOT)
                .fluidInputs(Stearin.getFluid(100))
                .outputs(Fat.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(120).duration(40)
                .fluidInputs(Stearin.getFluid(1000), Water.getFluid(2000))
                .input(dust, SodiumHydroxide, 3)
                .fluidOutputs(SodiumStearate.getFluid(3000), Glycerol.getFluid(1000))
                .buildAndRegister();

        //"Stearic acid is used along with simple sugar or corn syrup as a hardener in candies. In fireworks, stearic acid is often used to coat metal powders such as aluminium and iron. This prevents oxidation, allowing compositions to be stored for a longer period of time"
/*        DISTILLATION_RECIPES.recipeBuilder().EUt(32).duration(10)
                .fluidInputs(SodiumStearate.getFluid(100))
                .fluidOutputs(StearicAcid.getFluid(100))
                .output(dustTiny, SodiumHydroxide, 1)
                .buildAndRegister();*/

        /*FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(32).duration(100)
                .fluidInputs(StearicAcidSoap.getFluid(1000))
                .output(soap)
                .buildAndRegister();
                //TODO: Maybe do soaps with this :p (this works as a food addtive soap? with stearin atleast idk)
                */

        //TODO: make it refine to Fertilizer Later! & Biosolids
        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(800)
                .fluidInputs(Sludge.getFluid(24000))
                .fluidInputs(Bacteria.getFluid(1000))
                .fluidOutputs(Biomass.getFluid(10000))
                .fluidOutputs(Water.getFluid(14000))
                .fluidOutputs(SulfurDioxide.getFluid(8000))
                .fluidOutputs(Methane.getFluid(8000))
                .input(dust, Calcite, 3)
                .notConsumable(CARBON_MESH)
                .notConsumable(new IntCircuitIngredient(1))
                .buildAndRegister();
    }

}
