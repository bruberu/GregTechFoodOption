package gregtechfoodoption.recipe.chain;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.item.GTFOMetaItem;
import net.minecraft.init.Items;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustSmall;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;

public class FentChain {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .notConsumable(OrePrefix.dust, Silver)
                .fluidInputs(Oxygen.getFluid(144))
                .fluidInputs(Ethylene.getFluid(144))
                .fluidOutputs(EthyleneOxide.getFluid(144))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(OrePrefix.dust, Aluminium)
                .fluidInputs(Ethylene.getFluid(288))
                .fluidOutputs(DiethylEther.getFluid(144))
                .fluidOutputs(Water.getFluid(144))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Benzene.getFluid(144))
                .fluidInputs(Bromine.getFluid(144))
                .fluidOutputs(BromoBenzene.getFluid(144))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(OrePrefix.dust, Magnesium)
                .fluidInputs(BromoBenzene.getFluid(144))
                .fluidInputs(DiethylEther.getFluid(144))
                .output(dust, PhenylmagnesiumBromide, 5)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(dustSmall, PhenylmagnesiumBromide)
                .fluidInputs(CarbonDioxide.getFluid(144))
                .output(dust, BenzoPhenone, 5)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(dust, Calcite, 5)
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(Acetylene.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Acetylene.getFluid(144))
                .fluidInputs(Formaldehyde.getFluid(144))
                .fluidOutputs(onefourButynediol.getFluid(288))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .notConsumable(OrePrefix.dust, Silver)
                .fluidInputs(Methanol.getFluid(288))
                .fluidInputs(Oxygen.getFluid(288))
                .fluidOutputs(Formaldehyde.getFluid(288))
                .fluidOutputs(Water.getFluid(288))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(onefourButynediol.getFluid(144))
                .fluidInputs(Water.getFluid(144))
                .fluidOutputs(onefourButanediol.getFluid(288))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(onefourButanediol.getFluid(144))
                .notConsumable(OrePrefix.wireGtSingle, Copper)
                .fluidOutputs(gammabutyrolacetone.getFluid(144))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Ammonia.getFluid(144))
                .fluidInputs(Methanol.getFluid(144))
                .notConsumable(dust, SiliconDioxide)
                .fluidOutputs(Methylamine.getFluid(144))
                .buildAndRegister();
        }

    }