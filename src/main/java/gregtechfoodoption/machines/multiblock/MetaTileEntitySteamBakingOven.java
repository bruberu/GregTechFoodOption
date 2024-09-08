package gregtechfoodoption.machines.multiblock;

import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import gregtech.api.GTValues;
import gregtech.api.capability.impl.SteamMultiWorkable;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapSteamMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.logic.OCParams;
import gregtech.api.recipes.logic.OCResult;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtechfoodoption.block.GTFOBlockCasing;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.client.GTFOGuiTextures;
import gregtechfoodoption.recipe.builder.ElectricBakingOvenRecipeBuilder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntitySteamBakingOven extends RecipeMapSteamMultiblockController {
    public MetaTileEntitySteamBakingOven(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, double conversionRate) {
        super(metaTileEntityId, recipeMap, conversionRate);
        this.recipeMapWorkable = new SteamBakingOvenWorkable(this, CONVERSION_RATE);
        this.recipeMapWorkable.setParallelLimit(1);
    }

    protected IBlockState getCasingState() {
        return GTFOMetaBlocks.GTFO_CASING.getState(GTFOBlockCasing.CasingType.REINFORCED_ADOBE_BRICKS);
    }

    protected IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(Steel).getBlock(Steel);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start().aisle("XXXX", "XGGX", "XXXX").aisle("XXXX", "GFFG", "XFFX").aisle("XXXX", "GFFG", "XFFX").aisle("XXXX", "YGGX", "XXXX").where('X', states(getCasingState()).or(this.autoAbilities(true, false, true, true, false))).where('F', states(getFrameState())).where('#', air()).where(' ', any()).where('Y', selfPredicate()).where('G', states(getCasingState()).or(states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS)))).build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GTFOClientHandler.REINFORCED_ADOBE_BRICKS;
    }

    @Nonnull
    protected ICubeRenderer getFrontOverlay() {
        return GTFOClientHandler.MICROWAVE_OVERLAY;
    }

    @Override
    public boolean hasMaintenanceMechanics() {
        return false;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntitySteamBakingOven(metaTileEntityId, getRecipeMap(), CONVERSION_RATE);
    }

    @Override
    public ModularPanel buildUI(PosGuiData data, PanelSyncManager syncManager) {
        return null;
    }

    public static class SteamBakingOvenWorkable extends SteamMultiWorkable {

        protected int recipeSteamT;

        public SteamBakingOvenWorkable(RecipeMapSteamMultiblockController tileEntity, double conversionRate) {
            super(tileEntity, conversionRate);
        }

        @Override
        protected void updateRecipeProgress() {
            if (this.canRecipeProgress && this.drawEnergy(recipeSteamT, true)) {
                this.drawEnergy(recipeSteamT, false);
                if (++this.progressTime > this.maxProgressTime) {
                    this.completeRecipe();
                }

                if (this.hasNotEnoughEnergy && this.getEnergyInputPerSecond() > 19L * (long) recipeSteamT) {
                    this.hasNotEnoughEnergy = false;
                }
            } else if (recipeSteamT > 0) {
                this.hasNotEnoughEnergy = true;
                if (this.progressTime >= 2) {
                    if (ConfigHolder.machines.recipeProgressLowEnergy) {
                        this.progressTime = 1;
                    } else {
                        this.progressTime = Math.max(1, this.progressTime - 2);
                    }
                }
            }
        }

        @Override
        protected void setupRecipe(Recipe recipe) {
            super.setupRecipe(recipe);
            recipeSteamT = previousRecipe == null ? 0 : this.previousRecipe.getProperty(ElectricBakingOvenRecipeBuilder.TemperatureProperty.getInstance(), 0) / 100;
        }

        @Override
        protected void performOverclocking(@NotNull Recipe recipe, @NotNull OCParams ocParams, @NotNull OCResult ocResult) {
            super.performOverclocking(recipe, ocParams, ocResult);
            ocResult.setDuration(ocResult.duration() * 4);
        }

        protected boolean drawEnergy(int recipeEUt, boolean simulate) {
            return recipeEUt == 0 || super.drawEnergy(recipeEUt, simulate);
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound compound = super.serializeNBT();
            compound.setInteger("RecipeSteamT", recipeSteamT);
            return compound;
        }

        @Override
        public void deserializeNBT(@Nonnull NBTTagCompound compound) {
            super.deserializeNBT(compound);
            recipeSteamT = compound.getInteger("RecipeSteamT");
        }

        @Override
        public void writeInitialSyncData(@Nonnull PacketBuffer buf) {
            super.writeInitialSyncData(buf);
            buf.writeInt(recipeSteamT);
        }

        @Override
        public void receiveInitialSyncData(@Nonnull PacketBuffer buf) {
            super.receiveInitialSyncData(buf);
            recipeSteamT = buf.readInt();
        }



        public long getMaxVoltage() {
            return GTValues.V[4];
        }

    }

    @Override
    protected @NotNull TextureArea getLogo() {
        return GTFOGuiTextures.GTFO_LOGO_WORKING;
    }

    @Override
    protected @NotNull TextureArea getWarningLogo() {
        return GTFOGuiTextures.GTFO_LOGO_WARNING;
    }

    @Override
    protected @NotNull TextureArea getErrorLogo() {
        return GTFOGuiTextures.GTFO_LOGO_ERROR;
    }
}
