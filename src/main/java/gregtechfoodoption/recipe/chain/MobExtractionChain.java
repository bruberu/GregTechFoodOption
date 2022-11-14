package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtechfoodoption.entity.EntityItalianBuffalo;
import net.minecraft.entity.passive.*;
import net.minecraft.util.ResourceLocation;

import static gregtech.api.unification.material.Materials.Milk;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.recipe.GTFORecipeMaps.MOB_EXTRACTOR_RECIPES;

public class MobExtractionChain {
    public static void init() {
        MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(3))
                .mob(EntityCow.class)
                .fluidOutputs(Milk.getFluid(10))
                .EUt(16)
                .duration(20)
                .buildAndRegister();
        MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(1))
                .mob(EntityItalianBuffalo.class)
                .fluidOutputs(ItalianBuffaloMilk.getFluid(10))
                .EUt(16)
                .duration(20)
                .buildAndRegister();
        MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(2))
                .mob(EntityMooshroom.class)
                .fluidOutputs(MushroomSoup.getFluid(1))
                .EUt(16)
                .duration(20)
                .buildAndRegister();

        MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(4))
                .mob(EntityCow.class)
                .EUt(16)
                .duration(20)
                .fluidOutputs(Blood.getFluid(10))
                .causeDamage(0.5f)
                .buildAndRegister();
        MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(5))
                .mob(EntityChicken.class)
                .EUt(16)
                .duration(20)
                .fluidOutputs(Blood.getFluid(1))
                .causeDamage(0.5f)
                .buildAndRegister();
        MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(6))
                .mob(EntitySheep.class)
                .EUt(16)
                .duration(20)
                .fluidOutputs(Blood.getFluid(5))
                .causeDamage(0.5f)
                .buildAndRegister();
        MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(7))
                .mob(EntityPig.class)
                .EUt(16)
                .duration(20)
                .fluidOutputs(Blood.getFluid(5))
                .causeDamage(0.5f)
                .buildAndRegister();
        MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(8))
                .mob(EntityVillager.class)
                .EUt(64)
                .duration(20)
                .fluidOutputs(Blood.getFluid(100))
                .causeDamage(0.5f)
                .buildAndRegister();
        MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(9))
                .mob(new ResourceLocation("player"))
                .EUt(16)
                .duration(20)
                .fluidOutputs(Blood.getFluid(200))
                .causeDamage(1.5f)
                .buildAndRegister();
    }
}