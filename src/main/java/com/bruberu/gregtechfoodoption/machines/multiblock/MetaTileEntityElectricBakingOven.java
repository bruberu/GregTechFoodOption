package com.bruberu.gregtechfoodoption.machines.multiblock;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.bruberu.gregtechfoodoption.client.GTFOClientHandler;
import com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps;
import gregicadditions.capabilities.impl.GAMultiblockRecipeLogic;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregtech.api.gui.Widget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static gregtech.api.gui.widgets.AdvancedTextWidget.withButton;
import static gregtech.api.unification.material.Materials.BismuthBronze;
import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntityElectricBakingOven extends GARecipeMapMultiblockController {

    private int temp;
    private int targetTemp;
    private boolean canAchieveTargetTemp;
    private boolean hasEnoughEnergy;

    public MetaTileEntityElectricBakingOven(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTFORecipeMaps.ELECTRIC_BAKING_OVEN_RECIPES);
        this.recipeMapWorkable = new ElectricBakingOvenLogic(this);

        temp = 300;
        targetTemp = 300;
    }

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.INPUT_ENERGY
    };

    @Override
    protected void updateFormedValid() {
        if (getWorld().isRemote) {
            return;
        }

        if(temp > 300)
            hasEnoughEnergy = drainEnergy();

        if (getTimer() % 20 == 9 && targetTemp != temp) {
            stepTowardsTargetTemp();
        }
    }

    private void stepTowardsTargetTemp() {
        canAchieveTargetTemp = true;
        if(targetTemp < temp) {
            temp -= 5;
            return;
        }
        if(temperatureEnergyCost(this.temp + 5) <= this.getEnergyContainer().getInputVoltage() * this.getEnergyContainer().getInputAmperage() && hasEnoughEnergy) {
            temp += 5;
        }
        else {
            canAchieveTargetTemp = false;
        }
    }

    private boolean drainEnergy() {
        if (energyContainer.getEnergyStored() >= temperatureEnergyCost(this.temp)) {
            energyContainer.removeEnergy(temperatureEnergyCost(this.temp));
            return true;
        }
        temp -= 5;
        return false;
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);


    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (this.isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.electric_baking_oven.tooltip.1", temp));
            textList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.electric_baking_oven.tooltip.4", temperatureEnergyCost(temp)));

            ITextComponent buttonText = new TextComponentTranslation("gregtechfoodoption.multiblock.electric_baking_oven.tooltip.3");
            buttonText.appendText(" ");
            buttonText.appendSibling(withButton(new TextComponentString("[-]"), "sub"));
            buttonText.appendText(" ");
            buttonText.appendSibling(withButton(new TextComponentString("[+]"), "add"));
            textList.add(buttonText);

            textList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.electric_baking_oven.tooltip.5", targetTemp));


            if (!canAchieveTargetTemp && hasEnoughEnergy)
                textList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.electric_baking_oven.tooltip.2")
                        .setStyle(new Style().setColor(TextFormatting.RED)));
            if (!hasEnoughEnergy)
                textList.add(new TextComponentTranslation("gregtech.multiblock.not_enough_energy")
                        .setStyle(new Style().setColor(TextFormatting.RED)));

        }
        else
            super.addDisplayText(textList);

    }



    @Override
    protected void handleDisplayClick(String componentData, Widget.ClickData clickData) {
        super.handleDisplayClick(componentData, clickData);
        int modifier = componentData.equals("add") ? 1 : -1;
        targetTemp += 5 * modifier;
        if(targetTemp < 300)
            targetTemp = 300;
    }

    protected IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(BismuthBronze);
    }

    protected IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(Steel).getDefaultState();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GAMetaBlocks.METAL_CASING.get(BismuthBronze);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        GTFOClientHandler.BAKING_OVEN_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), temp > 300);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXX", "XXXX", "XXXX", "#XX#")
                .aisle("XXXX", "XFFX", "X##X", "#XX#")
                .aisle("XXXX", "XFFX", "X##X", "#XX#")
                .aisle("XXXX", "YGGX", "XGGX", "#XX#")
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('F', statePredicate(getFrameState()))
                .where('G', statePredicate(GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.REINFORCED_GLASS)))
                .where('#', (tile) -> true)
                .where('Y', selfPredicate())
                .build();

    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityElectricBakingOven(metaTileEntityId);
    }

    public static int temperatureEnergyCost(int temp) {
        return temp <= 300 ? 0 : (int) Math.exp(((double) temp - 100) / 100);
    }

    // Is the inverse of the previous function.
    public static int temperatureForEnergy(int EUt) {
        if (EUt <= 8)
            return 300;
        return (int) (Math.log(EUt) * 100) + 100;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("temp", this.temp);
        data.setInteger("targetTemp", this.targetTemp);
        data.setBoolean("canAchieveTargetTemp", this.canAchieveTargetTemp);
        data.setBoolean("hasEnoughEnergy", this.hasEnoughEnergy);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.temp = data.getInteger("temp");
        this.targetTemp = data.getInteger("targetTemp");
        this.canAchieveTargetTemp = data.getBoolean("canAchieveTargetTemp");
        this.hasEnoughEnergy = data.getBoolean("hasEnoughEnergy");
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeInt(this.temp);
        buf.writeInt(this.targetTemp);
        buf.writeBoolean(this.canAchieveTargetTemp);
        buf.writeBoolean(this.hasEnoughEnergy);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.temp = buf.readInt();
        this.targetTemp = buf.readInt();
        this.canAchieveTargetTemp = buf.readBoolean();
        this.hasEnoughEnergy = buf.readBoolean();
    }

    private class ElectricBakingOvenLogic extends GAMultiblockRecipeLogic {

        public ElectricBakingOvenLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

    }
}
