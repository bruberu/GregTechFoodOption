package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtechfoodoption.entity.EntityItalianBuffalo;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;

import static gregtech.api.unification.material.Materials.Milk;
import static gregtechfoodoption.GTFOMaterialHandler.ItalianBuffaloMilk;
import static gregtechfoodoption.GTFOMaterialHandler.MushroomSoup;
import static gregtechfoodoption.recipe.GTFORecipeMaps.MOB_EXTRACTOR_RECIPES;

public class MobExtractionChain {
    public static void init() {
        MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(0))
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
    }
}