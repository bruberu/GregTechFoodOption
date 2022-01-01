package gregtechfoodoption.recipe.chain;

import gregtechfoodoption.integration.GTFOAAMaterialHandler;
import gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import gregtechfoodoption.utils.RecipeUtils;
import gregtechfoodoption.GTFOConfig;
import de.ellpeck.actuallyadditions.mod.items.InitItems;
import de.ellpeck.actuallyadditions.mod.items.metalists.TheMiscItems;
import de.ellpeck.actuallyadditions.mod.misc.apiimpl.MethodHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.Paper;
import static gregtech.api.unification.material.Materials.Water;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class CoffeeChain {
    public static NBTTagCompound getCoffeeNBT(int duration, int id, int amplifier) {
        NBTTagCompound resultNBT = new NBTTagCompound();
        resultNBT.setTag("Duration", new NBTTagInt(duration));
        resultNBT.setTag("ID", new NBTTagInt(id));
        resultNBT.setTag("Amplifier", new NBTTagInt(amplifier));
        return resultNBT;
    }

    public static void init() {
        if(GTFOConfig.gtfoaaConfig.disableCoffeeMaker)
            ModHandler.removeRecipeByName(new ResourceLocation("actuallyadditions:recipes125"));

        ItemStack basicCoffee = new ItemStack(InitItems.itemCoffee, 1);
        ItemStack energizedCoffee = new ItemStack(InitItems.itemCoffee, 1);
        MethodHandler aaMethodHandler = new MethodHandler();

        aaMethodHandler.addEffectToStack(basicCoffee, new PotionEffect(Potion.getPotionById(1), 90, 1));
        aaMethodHandler.addEffectToStack(basicCoffee, new PotionEffect(Potion.getPotionById(10), 3, 0));
        aaMethodHandler.addEffectToStack(energizedCoffee, new PotionEffect(Potion.getPotionById(1), 50, 3));
        aaMethodHandler.addEffectToStack(energizedCoffee, new PotionEffect(Potion.getPotionById(10), 10, 2));
        aaMethodHandler.addEffectToStack(energizedCoffee, new PotionEffect(Potion.getPotionById(5), 10, 1));
        aaMethodHandler.addEffectToStack(energizedCoffee, new PotionEffect(Potion.getPotionById(11), 10, 1));

        ItemStack emptyCoffeeCup = new ItemStack(InitItems.itemMisc, 1, TheMiscItems.CUP.ordinal());
        ItemStack coffeeFilter = new ItemStack(InitItems.itemMisc, 1, TheMiscItems.PAPER_CONE.ordinal());

        GTFOAppleCoreCompat.addToSparedItems(InitItems.itemCoffee, 2, (float) 0.5);

        CANNER_RECIPES.recipeBuilder()
                .fluidInputs(GTFOAAMaterialHandler.Coffee.getFluid(100))
                .inputs(emptyCoffeeCup)
                .outputs(basicCoffee)
                .EUt(40)
                .duration(20)
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .fluidInputs(GTFOAAMaterialHandler.EnergizedCoffee.getFluid(100))
                .inputs(emptyCoffeeCup)
                .outputs(energizedCoffee)
                .EUt(40)
                .duration(20)
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder()
                .fluidInputs(GTFOAAMaterialHandler.Coffee.getFluid(10))
                .input(Items.SUGAR)
                .fluidOutputs(GTFOAAMaterialHandler.EnergizedCoffee.getFluid(10))
                .EUt(120)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .inputs(GTFOAAMaterialHandler.COFFEE_GROUNDS.getItemStack())
                .inputs(coffeeFilter) // This is a paper cone, or a coffee filter, if you like.
                .fluidInputs(Materials.Steam.getFluid(1000))
                .output(dust, Paper, 1)
                .fluidOutputs(GTFOAAMaterialHandler.Coffee.getFluid(15))
                .EUt(120)
                .duration(30)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(Items.PAPER)
                .circuitMeta(1)
                .outputs(coffeeFilter)
                .EUt(30)
                .duration(14)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(GTFOAAMaterialHandler.LARGE_ROASTED_COFFEE.getItemStack())
                .outputs(GTFOAAMaterialHandler.COFFEE_GROUNDS.getItemStack(2))
                .EUt(20)
                .duration(40)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(GTFOAAMaterialHandler.SMALL_ROASTED_COFFEE.getItemStack())
                .outputs(GTFOAAMaterialHandler.COFFEE_GROUNDS.getItemStack())
                .EUt(20)
                .duration(20)
                .buildAndRegister();

        PYROLYSE_RECIPES.recipeBuilder()
                .inputs(GTFOAAMaterialHandler.LARGE_GRADED_COFFEE.getItemStack())
                .circuitMeta(0)
                .outputs(GTFOAAMaterialHandler.LARGE_ROASTED_COFFEE.getItemStack())
                .fluidOutputs(Water.getFluid(200))
                .EUt(120)
                .duration(80)
                .buildAndRegister();

        PYROLYSE_RECIPES.recipeBuilder()
                .inputs(GTFOAAMaterialHandler.SMALL_GRADED_COFFEE.getItemStack())
                .circuitMeta(0)
                .outputs(GTFOAAMaterialHandler.SMALL_ROASTED_COFFEE.getItemStack())
                .fluidOutputs(Water.getFluid(100))
                .EUt(120)
                .duration(40)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(GTFOAAMaterialHandler.LARGE_HULLED_COFFEE.getItemStack())
                .notConsumable(MetaItems.SENSOR_LV)
                .chancedOutput(GTFOAAMaterialHandler.LARGE_GRADED_COFFEE.getItemStack(), 5000, 10)
                .EUt(60)
                .duration(20)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(GTFOAAMaterialHandler.SMALL_HULLED_COFFEE.getItemStack())
                .notConsumable(MetaItems.SENSOR_LV)
                .chancedOutput(GTFOAAMaterialHandler.SMALL_GRADED_COFFEE.getItemStack(), 5000, 10)
                .EUt(60)
                .duration(10)
                .buildAndRegister();
        RecipeUtils.clusterMillBuilderProxy().recipeBuilder()
                .inputs(GTFOAAMaterialHandler.SMALL_DRIED_COFFEE.getItemStack())
                .outputs(GTFOAAMaterialHandler.SMALL_HULLED_COFFEE.getItemStack())
                .EUt(30)
                .duration(10)
                .buildAndRegister();
        RecipeUtils.clusterMillBuilderProxy().recipeBuilder()
                    .inputs(GTFOAAMaterialHandler.LARGE_DRIED_COFFEE.getItemStack())
                    .outputs(GTFOAAMaterialHandler.LARGE_HULLED_COFFEE.getItemStack())
                    .EUt(30)
                    .duration(10)
                    .buildAndRegister();


        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .inputs(GTFOAAMaterialHandler.LARGE_WET_COFFEE.getItemStack(32))
                .outputs(GTFOAAMaterialHandler.LARGE_DRIED_COFFEE.getItemStack(32))
                .fluidOutputs(Water.getFluid(24000))
                .EUt(30)
                .duration(3600)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .inputs(GTFOAAMaterialHandler.SMALL_WET_COFFEE.getItemStack(64))
                .outputs(GTFOAAMaterialHandler.SMALL_DRIED_COFFEE.getItemStack(64))
                .fluidOutputs(Water.getFluid(24000))
                .EUt(30)
                .duration(1800)
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder()
                .inputs(GTFOAAMaterialHandler.LARGE_BASIC_COFFEE.getItemStack(32))
                .outputs(GTFOAAMaterialHandler.LARGE_WET_COFFEE.getItemStack(32))
                .fluidInputs(Water.getFluid(32000))
                .EUt(60)
                .duration(3600)
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder()
                .inputs(GTFOAAMaterialHandler.SMALL_BASIC_COFFEE.getItemStack(64))
                .outputs(GTFOAAMaterialHandler.SMALL_WET_COFFEE.getItemStack(64))
                .fluidInputs(Water.getFluid(32000))
                .EUt(60)
                .duration(3600)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(GTFOAAMaterialHandler.UNSORTED_BASIC_COFFEE.getItemStack(30))
                .outputs(GTFOAAMaterialHandler.LARGE_BASIC_COFFEE.getItemStack(9))
                .chancedOutput(GTFOAAMaterialHandler.LARGE_BASIC_COFFEE.getItemStack(), 5000, 200)
                .outputs(GTFOAAMaterialHandler.SMALL_BASIC_COFFEE.getItemStack(19))
                .chancedOutput(GTFOAAMaterialHandler.SMALL_BASIC_COFFEE.getItemStack(), 5000, 200)
                .EUt(20)
                .duration(600)
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder()
                .input(InitItems.itemCoffeeBean)
                .outputs(GTFOAAMaterialHandler.UNSORTED_BASIC_COFFEE.getItemStack(9))
                .chancedOutput(GTFOAAMaterialHandler.UNSORTED_BASIC_COFFEE.getItemStack(), 5000, 500)
                .EUt(60)
                .duration(20)
                .buildAndRegister();

        RecipeUtils.addGreenHouseRecipes(new ItemStack(InitItems.itemCoffeeSeed), InitItems.itemCoffeeBean);
    }
}
