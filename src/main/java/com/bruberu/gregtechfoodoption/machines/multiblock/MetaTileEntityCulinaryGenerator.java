package com.bruberu.gregtechfoodoption.machines.multiblock;

import com.bruberu.gregtechfoodoption.block.GTFOMetaBlocks;
import com.bruberu.gregtechfoodoption.block.GTFOOtherCasing;
import com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps;
import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.capabilities.impl.GAMultiblockRecipeLogic;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.*;
import gregicadditions.item.components.SensorCasing;
import gregicadditions.item.components.EmitterCasing;
import gregicadditions.machines.multi.simple.LargeSimpleRecipeMapMultiblockController;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import nc.multiblock.Multiblock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static com.bruberu.gregtechfoodoption.block.GTFOMetaBlocks.GTFO_OTHER_CASING;
import static com.bruberu.gregtechfoodoption.client.GTFOClientHandler.BIOCHEMICAL;

public class MetaTileEntityCulinaryGenerator extends GARecipeMapMultiblockController {

    private long maxVoltage;
    private int unitGlucose;
    private int unitTriglyceride;

    public MetaTileEntityCulinaryGenerator(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTFORecipeMaps.CULINARY_GENERATOR_RECIPES, false, true ,true);
    }

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.OUTPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH
    };

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "XXX", "XXX", "XXX")
                .aisle("YXX", "RXX", "RXX", "RXX", "XXX")
                .aisle("XXX", "XXX", "XXX", "XXX", "XXX")
                .where('Y', selfPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                //.where('~', frameworkPredicate().or(frameworkPredicate2()))
                .where('R', statePredicate(GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.REINFORCED_GLASS)))
                //.where('s', sensorPredicate())
                //.where('e', emitterPredicate())
                .build();
    }
/*
    public static Predicate<BlockWorldState> frameworkPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GAMultiblockCasing)) {
                return false;
            } else {
                GAMultiblockCasing framework = (GAMultiblockCasing) blockState.getBlock();
                GAMultiblockCasing.CasingType tieredCasingType = framework.getState(blockState);
                if(blockWorldState.getMatchContext().getOrPut("framework", tieredCasingType) == null)
                    return false;
                if (!tieredCasingType.getName().contains("tiered_hull"))
                    return false;

                GAMultiblockCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("framework", tieredCasingType);
                int tier = tieredCasingType.getTier();
                int currentTier = blockWorldState.getMatchContext().getOrPut("casingTier", tier);

                return currentCasing.getName().equals(tieredCasingType.getName()) && tier == currentTier;
            }
        };
    }

    public static Predicate<BlockWorldState> frameworkPredicate2() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GAMultiblockCasing2)) {
                return false;
            } else {
                GAMultiblockCasing2 framework = (GAMultiblockCasing2) blockState.getBlock();
                GAMultiblockCasing2.CasingType tieredCasingType = framework.getState(blockState);
                if(blockWorldState.getMatchContext().getOrPut("framework", tieredCasingType) == null)
                    return false;
                if (!tieredCasingType.getName().contains("tiered_hull"))
                    return false;

                GAMultiblockCasing2.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("framework", tieredCasingType);
                int tier = tieredCasingType.getTier();
                int currentTier = blockWorldState.getMatchContext().getOrPut("casingTier", tier);

                return currentCasing.getName().equals(tieredCasingType.getName()) && tier == currentTier;
            }
        };
    }

    public static Predicate<BlockWorldState> sensorPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof SensorCasing)) {
                return false;
            } else {
                SensorCasing sensorCasing = (SensorCasing) blockState.getBlock();
                SensorCasing.CasingType tieredCasingType = sensorCasing.getState(blockState);
                SensorCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Sensor", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> emitterPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof EmitterCasing)) {
                return false;
            } else {
                EmitterCasing emitterCasing = (EmitterCasing) blockState.getBlock();
                EmitterCasing.CasingType tieredCasingType = emitterCasing.getState(blockState);
                EmitterCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Emitter", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
    }

    @Override
    public void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.culinary_generator.unit_glucose", unitGlucose));
            textList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.culinary_generator.unit_triglyceride", unitTriglyceride));
        }
        textList.add(new TextComponentTranslation("gregtech.multiblock.universal.framework", this.maxVoltage));
    }
*/
    public IBlockState getCasingState() {
        return GTFO_OTHER_CASING.getState(GTFOOtherCasing.CasingType.BIOCHEMICAL);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return BIOCHEMICAL;
    }
/*
    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        int tier = context.getOrDefault("casingTier", -1);
        if (tier < 0)
            maxVoltage = 0;
        else
            maxVoltage = (long) (Math.pow(4, tier) * 8);
    }
*/
    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityCulinaryGenerator(metaTileEntityId);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return Textures.CHEMICAL_REACTOR_OVERLAY;
    }

}
