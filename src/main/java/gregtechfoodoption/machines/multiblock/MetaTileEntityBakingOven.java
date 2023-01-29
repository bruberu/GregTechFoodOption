package gregtechfoodoption.machines.multiblock;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.ItemHandlerProxy;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.LabelWidget;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapPrimitiveMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.MetaBlocks;
import gregtechfoodoption.block.GTFOBlockCasing;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.client.GTFOGuiTextures;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.ItemStackHandler;

import static gregtech.api.unification.material.Materials.Iron;

public class MetaTileEntityBakingOven extends RecipeMapPrimitiveMultiblockController {

    public MetaTileEntityBakingOven(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTFORecipeMaps.BAKING_OVEN_RECIPES);
    }

    @Override
    public int getActualLightValue() {
        return recipeMapWorkable.isActive() ? 15 : 0;
    }

    protected IBlockState getCasingState() {
        return GTFOMetaBlocks.GTFO_CASING.getState(GTFOBlockCasing.CasingType.ADOBE_BRICKS);
    }

    protected IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(Iron).getBlock(Iron);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GTFOClientHandler.ADOBE_BRICKS;
    }

    @Override
    protected ICubeRenderer getFrontOverlay() {
        return GTFOClientHandler.BAKING_OVEN_OVERLAY;
    }


    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.getFrontOverlay().renderOrientedState(renderState, translation, pipeline, this.getFrontFacing(), this.recipeMapWorkable.isActive(), this.recipeMapWorkable.isWorkingEnabled());
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();
        ItemStackHandler emptyHandler = new ItemStackHandler(0);
        this.itemInventory = new ItemHandlerProxy(emptyHandler, emptyHandler);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX")
                .aisle("XFX", "X#X")
                .aisle("XYX", "XXX")
                .where('X', states(getCasingState()))
                .where('F', states(getFrameState()))
                .where('#', air())
                .where(' ', any())
                .where('Y', selfPredicate())
                .build();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityBakingOven(metaTileEntityId);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return ModularUI.builder(GuiTextures.PRIMITIVE_BACKGROUND, 176, 166)
                .widget(new LabelWidget(5, 5, getMetaFullName()))
                .widget(new SlotWidget(importItems, 0, 53, 20, true, true)
                        .setBackgroundTexture(GuiTextures.PRIMITIVE_SLOT, GTFOGuiTextures.PRIMITIVE_FOOD_OVERLAY))
                .widget(new SlotWidget(importItems, 1, 53, 38, true, true)
                        .setBackgroundTexture(GuiTextures.PRIMITIVE_SLOT, GuiTextures.PRIMITIVE_FURNACE_OVERLAY))
                .progressBar(recipeMapWorkable::getProgressPercent, 78, 31, 20, 15, GuiTextures.PRIMITIVE_BLAST_FURNACE_PROGRESS_BAR, ProgressWidget.MoveType.HORIZONTAL, this.getRecipeMap())
                .widget(new SlotWidget(exportItems, 0, 105, 29, true, false)
                        .setBackgroundTexture(GuiTextures.PRIMITIVE_SLOT, GTFOGuiTextures.PRIMITIVE_FOOD_OVERLAY))
                .bindPlayerInventory(entityPlayer.inventory, GuiTextures.PRIMITIVE_SLOT, 0)
                .build(getHolder(), entityPlayer);
    }

    public boolean hasMaintenanceMechanics() {
        return false;
    }
}
