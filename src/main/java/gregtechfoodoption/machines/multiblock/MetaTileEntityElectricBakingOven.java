package gregtechfoodoption.machines.multiblock;

import gregtechfoodoption.block.GTFOMetalCasing;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.gui.Widget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.*;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.recipeproperties.RecipeProperty;
import gregtech.api.util.RelativeDirection;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.*;

import static gregtechfoodoption.block.GTFOMetaBlocks.GTFO_METAL_CASING;
import static gregtech.api.gui.widgets.AdvancedTextWidget.withButton;
import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntityElectricBakingOven extends RecipeMapMultiblockController {

    private int temp;
    private int targetTemp;
    private boolean canAchieveTargetTemp;
    private boolean hasEnoughEnergy;
    public int size;

    public MetaTileEntityElectricBakingOven(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTFORecipeMaps.ELECTRIC_BAKING_OVEN_RECIPES);
        this.recipeMapWorkable = new ElectricBakingOvenLogic(this);

        temp = 300;
        targetTemp = 300;
    }

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.INPUT_ENERGY, MultiblockAbility.MAINTENANCE_HATCH
    };

    @Override
    protected void updateFormedValid() {
        if (getWorld().isRemote) {
            return;
        }

        super.updateFormedValid();

        if (temp > 300)
            hasEnoughEnergy = drainEnergy();
        else
            hasEnoughEnergy = true;

        if (getOffsetTimer() % 20 == 0 && targetTemp != temp && !recipeMapWorkable.isActive())
            stepTowardsTargetTemp();
        else if (targetTemp == temp) {
            canAchieveTargetTemp = true;

        }

    }

    private void stepTowardsTargetTemp() {
        canAchieveTargetTemp = true;
        if (targetTemp < temp) {
            setTemp(temp - 5);
            if (temp == 300)
                markDirty();
            return;
        }
        if (temperatureEnergyCost(this.temp + 5) <= this.getEnergyContainer().getInputVoltage() * this.getEnergyContainer().getInputAmperage() && hasEnoughEnergy) {
            setTemp(temp + 5);
            if (temp == 305)
                markDirty();
        } else {
            canAchieveTargetTemp = false;
        }
    }

    private boolean drainEnergy() {
        if (energyContainer.getEnergyStored() >= temperatureEnergyCost(this.temp)) {
            energyContainer.removeEnergy(temperatureEnergyCost(this.temp));
            return true;
        }
        setTemp(temp - 5);
        return false;
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);


    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
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
    }


    @Override
    protected void handleDisplayClick(String componentData, Widget.ClickData clickData) {
        super.handleDisplayClick(componentData, clickData);
        int modifier = componentData.equals("add") ? 1 : -1;
        targetTemp += 5 * modifier;
        if (targetTemp < 300)
            targetTemp = 300;
    }

    protected IBlockState getCasingState() {
        return GTFO_METAL_CASING.getState(GTFOMetalCasing.CasingType.BISMUTH_BRONZE_CASING);
    }

    protected IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(Steel).getDefaultState();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GTFOClientHandler.BISMUTH_BRONZE_CASING;
    }

    @Override
    protected ICubeRenderer getFrontOverlay() {
        return GTFOClientHandler.BAKING_OVEN_OVERLAY;
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.FRONT, RelativeDirection.UP, RelativeDirection.RIGHT)
                .aisle("XXXX", "YXXX", "XXXX", "####")
                .aisle("XXXX", "GFFX", "GIOX", "XXXX").setRepeatable(2, 14)
                .aisle("XXXX", "XXXX", "XXXX", "####")
                .where('X', states(getCasingState()).or(abilities(ALLOWED_ABILITIES)))
                .where('F', states(getFrameState()))
                .where('G', states(Blocks.GLASS.getDefaultState()))
                .where('#', any())
                .where('O', air())
                .where('I', isIndicatorPredicate())
                .where('Y', selfPredicate())
                .build();

    }

    // This function is highly useful for detecting the length of this multiblock.
    public static TraceabilityPredicate isIndicatorPredicate() {
        return new TraceabilityPredicate((blockWorldState) -> {
            if (air().test(blockWorldState)) {
                blockWorldState.getMatchContext().increment("bakingOvenLength", 1);
                return true;
            } else
                return false;
        });
    }


    @Override
    public void invalidateStructure() {
        setTemp(300);
        super.invalidateStructure();
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.size = context.getOrDefault("bakingOvenLength", 1) - 2;
        System.out.println(size);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityElectricBakingOven(metaTileEntityId);
    }

    public int temperatureEnergyCost(int temp) {
        return temp <= 300 ? 0 : (int) Math.exp(((double) temp - 100 + (size * 5)) / 100);
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

    public void setTemp(int temp) {
        this.temp = temp;
        if (!getWorld().isRemote) {
            writeCustomData(600, buf -> buf.writeInt(temp));
            markDirty();
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == 600) {
            this.temp = buf.readInt();
            scheduleRenderUpdate();
        }
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


    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        return recipe.getProperty(BakingTemperatureProperty.getInstance(), 0) == temp;
    }

    @Override
    public int getLightValueForPart(IMultiblockPart sourcePart) {
        return sourcePart == null && temp > 300 ? 15 : 0;
    }

    private class ElectricBakingOvenLogic extends MultiblockRecipeLogic {
        public ElectricBakingOvenLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override
        public int getParallelLimit() {
            return ((MetaTileEntityElectricBakingOven) this.getMetaTileEntity()).size;
        }
    }

    public static class BakingTemperatureProperty extends RecipeProperty<Integer> {
        public static final String KEY = "temperature";

        private static BakingTemperatureProperty INSTANCE;

        private BakingTemperatureProperty() {
            super(KEY, Integer.class);
        }

        public static BakingTemperatureProperty getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new BakingTemperatureProperty();
            }
            return INSTANCE;
        }


        @Override
        public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
            minecraft.fontRenderer.drawString(I18n.format("gregtechfoodoption.recipe.baking_oven_temperature",
                    value), x, y, color);
        }
    }

}
