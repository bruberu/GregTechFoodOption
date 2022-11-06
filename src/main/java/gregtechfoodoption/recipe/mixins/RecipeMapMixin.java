package gregtechfoodoption.recipe.mixins;

import gregtech.api.recipes.RecipeMap;
import gregtechfoodoption.recipe.IExpandableRecipeMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = RecipeMap.class, priority = 500)
public class RecipeMapMixin implements IExpandableRecipeMap {
    @Shadow(remap = false) @Mutable private int minInputs;
    @Shadow(remap = false) @Mutable private int minOutputs;
    @Shadow(remap = false) @Mutable private int minFluidInputs;
    @Shadow(remap = false) @Mutable private int minFluidOutputs;
    @Shadow(remap = false) @Mutable private int maxInputs;
    @Shadow(remap = false) @Mutable private int maxOutputs;
    @Shadow(remap = false) @Mutable private int maxFluidInputs;
    @Shadow(remap = false) @Mutable private int maxFluidOutputs;

    @Override
    public void setMinInputs(int newMinInputs) {
        minInputs = newMinInputs;
    }

    @Override
    public void setMinOutputs(int newMinOutputs) {
        minOutputs = newMinOutputs;
    }

    @Override
    public void setMinFluidInputs(int newMinFluidInputs) {
        minFluidInputs = newMinFluidInputs;
    }

    @Override
    public void setMinFluidOutputs(int newMinFluidOutputs) {
        minFluidOutputs = newMinFluidOutputs;
    }

    @Override
    public void setMaxInputs(int newMaxInputs) {
        maxInputs = newMaxInputs;
    }

    @Override
    public void setMaxOutputs(int newMaxOutputs) {
        maxOutputs = newMaxOutputs;
    }

    @Override
    public void setMaxFluidInputs(int newMaxFluidInputs) {
        maxFluidInputs = newMaxFluidInputs;
    }

    @Override
    public void setMaxFluidOutputs(int newMaxFluidOutputs) {
        maxFluidOutputs = newMaxFluidOutputs;
    }
}
