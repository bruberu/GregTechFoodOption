package gregtechfoodoption.machines.multiblock;

import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.Recipe;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtechfoodoption.block.GTFOGlassCasing;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntityGreenhouse extends RecipeMapMultiblockController {

    public MetaTileEntityGreenhouse(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTFORecipeMaps.GREENHOUSE_RECIPES);
        this.recipeMapWorkable = new GreenhouseWorkable(this);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("CCCCCCC", "XXXFXXX", "XXXFXXX", "XXXFXXX", "XXXFXXX", "XXXFXXX", "XXXFXXX", "XXXFXXX", "   F   ")
                .aisle("CDDDDDC", "X#####X", "X#####X", "X#####X", "X#####X", "X#####X", "X#####X", "X#####X", " XXFXX ")
                .aisle("CDDDDDC", "X#####X", "X#####X", "X#####X", "X#####X", "X#####X", "X#####X", "X#####X", " XXFXX ")
                .aisle("CDDDDDC", "F#####F", "F#####F", "F#####F", "F#####F", "F#####F", "F#####F", "F#####F", "FFFFFFF")
                .aisle("CDDDDDC", "X#####X", "X#####X", "X#####X", "X#####X", "X#####X", "X#####X", "X#####X", " XXFXX ")
                .aisle("CDDDDDC", "X#####X", "X#####X", "X#####X", "X#####X", "X#####X", "X#####X", "X#####X", " XXFXX ")
                .aisle("CCCYCCC", "XXXFXXX", "XXXFXXX", "XXXFXXX", "XXXFXXX", "XXXFXXX", "XXXFXXX", "XXXFXXX", "   F   ")
                .where('X', states(getCasingState()))
                .where('F', states(getFrameState()))
                .where('D', states(Blocks.DIRT.getDefaultState(), Blocks.GRASS.getDefaultState()))
                .where('C', states(getCasingState2()).setMinGlobalLimited(10).or(autoAbilities()))
                .where('#', air())
                .where(' ', any())
                .where('Y', selfPredicate())
                .build();
    }

    protected IBlockState getCasingState() {
        return GTFOMetaBlocks.GTFO_GLASS_CASING.getState(GTFOGlassCasing.CasingType.GREENHOUSE_GLASS);
    }
    protected IBlockState getCasingState2() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    protected IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(Steel).getBlock(Steel);
    }


    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.SOLID_STEEL_CASING;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityGreenhouse(metaTileEntityId);
    }

    public boolean checkNaturalLighting() {
        for (BlockPos pos : BlockPos.getAllInBox(this.getPos().up(8).offset(this.frontFacing.rotateY(), 3),
                this.getPos().up(8).offset(this.getFrontFacing().rotateYCCW(), 3).offset(this.getFrontFacing().getOpposite(), 6))) {
            if (!GTUtility.canSeeSunClearly(this.getWorld(), pos)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);

        tooltip.add(I18n.format("gregtechfoodoption.machine.electric_baking_oven.tooltip.1"));
        tooltip.add(I18n.format("gregtechfoodoption.machine.electric_baking_oven.tooltip.2"));
        tooltip.add(I18n.format("gregtechfoodoption.machine.electric_baking_oven.tooltip.3"));
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (this.isStructureFormed()) {
            if (!((GreenhouseWorkable)this.recipeMapWorkable).hasSun())
                textList.add(new TextComponentTranslation("gregtech.multiblock.not_enough_sun")
                        .setStyle(new Style().setColor(TextFormatting.RED)));
        }
    }


    public static class GreenhouseWorkable extends MultiblockRecipeLogic {

        private boolean hasSun;

        public GreenhouseWorkable(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override
        protected void setupRecipe(Recipe recipe) {
            super.setupRecipe(recipe);
            this.hasSun = ((MetaTileEntityGreenhouse) metaTileEntity).checkNaturalLighting();
        }

        public boolean hasSun() {
            return hasSun;
        }

        @Override
        protected void updateRecipeProgress() {
            if (this.canRecipeProgress && this.drawEnergy(this.recipeEUt, true)) {
                this.drawEnergy(this.recipeEUt, false);
                if (this.hasSun)
                    this.progressTime++;
                else
                    this.progressTime += Math.random() * 2;

                if (this.progressTime > this.maxProgressTime) {
                    this.completeRecipe();
                }

                if (this.hasNotEnoughEnergy && this.getEnergyInputPerSecond() > 19L * (long)this.recipeEUt) {
                    this.hasNotEnoughEnergy = false;
                }
            } else if (this.recipeEUt > 0) {
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
        public NBTTagCompound serializeNBT() {
            NBTTagCompound tag = super.serializeNBT();
            tag.setBoolean("hasSun", hasSun);
            return tag;
        }

        @Override
        public void deserializeNBT(@Nonnull NBTTagCompound compound) {
            super.deserializeNBT(compound);
            this.hasSun = compound.getBoolean("hasSun");
        }
    }


}
