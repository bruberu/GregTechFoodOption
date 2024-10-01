package gregtechfoodoption.machines;

import gregtech.api.GTValues;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.RecipeLogicEnergy;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.client.renderer.ICubeRenderer;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.item.GTFOSimpleMachineMetaTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;

import java.util.function.Supplier;

public class MetaTileEntityFreezedryer extends GTFOSimpleMachineMetaTileEntity {
    public MetaTileEntityFreezedryer(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer, int tier) {
        super(metaTileEntityId, recipeMap, renderer, tier, true);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityFreezedryer(this.metaTileEntityId, this.workable.getRecipeMap(), this.renderer, this.getTier());
    }

    @Override
    public void update() {
        if (this.energyContainer.getEnergyStored() > GTValues.V[this.getTier()]) {
            MaterialStack materialIn = OreDictUnifier.getMaterial(importItems.getStackInSlot(0));
            if (materialIn != null) {
                if (materialIn.material.hasProperty(PropertyKey.INGOT) || materialIn.material.hasFlag(MaterialFlags.FLAMMABLE) || materialIn.material.hasFlag(MaterialFlags.EXPLOSIVE)) {
                    this.doExplosion(this.getTier() * 4);
                    return;
                }
            }
            if (TileEntityFurnace.getItemBurnTime(importItems.getStackInSlot(0)) > 0) {
                this.doExplosion(this.getTier() * 4);
                return;
            }
        }
        super.update();
    }

    protected RecipeLogicEnergy createWorkable(RecipeMap<?> recipeMap) {
        return new RecipeLogicMicrowave(this, recipeMap, () -> this.energyContainer);
    }

    public class RecipeLogicMicrowave extends RecipeLogicEnergy {
        public RecipeLogicMicrowave(MetaTileEntity tileEntity, RecipeMap<?> recipeMap, Supplier<IEnergyContainer> energyContainer) {
            super(tileEntity, recipeMap, energyContainer);
        }


    }
}