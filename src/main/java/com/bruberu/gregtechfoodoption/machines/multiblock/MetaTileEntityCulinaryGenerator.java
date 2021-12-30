package com.bruberu.gregtechfoodoption.machines.multiblock;

import com.bruberu.gregtechfoodoption.block.GTFOOtherCasing;
import com.bruberu.gregtechfoodoption.machines.multiblock.culinary.CulinaryGeneratorMultiblockController;
import com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.impl.GAMultiblockRecipeLogic;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.*;
import gregicadditions.item.components.SensorCasing;
import gregicadditions.item.components.EmitterCasing;
import gregicadditions.machines.multi.IMaintenance;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

import static gregtech.api.unification.material.Materials.StainlessSteel;

import static com.bruberu.gregtechfoodoption.block.GTFOMetaBlocks.GTFO_OTHER_CASING;
import static com.bruberu.gregtechfoodoption.client.GTFOClientHandler.BIOCHEMICAL;

public class MetaTileEntityCulinaryGenerator extends CulinaryGeneratorMultiblockController implements IMaintenance {

    private int maxTier;
    private long maxVoltage;

        public MetaTileEntityCulinaryGenerator(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTFORecipeMaps.CULINARY_GENERATOR_RECIPES, false, true, true);
        this.recipeMapWorkable = new CulinaryGeneratorRecipeLogic(this);
    }

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.OUTPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH
    };

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("         ", "         ", " PXXXXXP ", " PXXXXXP ", " PXXXXXP ", "         ", "         ")
                .aisle("         ", " PYYYYYP ", " R~~X~~R ", " R~~s~~R ", " R~~X~~R ", " PYYYYYP ", "         ")
                .aisle("F       F", "FPYYYYYPF", "FR~~~~~RF", "CR~~~~~RC", " R~~~~~R ", " PYYYYYP ", "         ")
                .aisle("         ", " PYYYYYP ", " R~~X~~R ", " R~~e~~R ", " R~~X~~R ", " PYYYYYP ", "         ")
                .aisle("         ", "         ", " PXXXXXP ", " PXX#XXP ", " PXXXXXP ", "         ", "         ")
                .where(' ', blockWorldState -> true)
                .where('#', selfPredicate())
                .where('Y', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('~', isAirPredicate())
                .where('R', statePredicate(GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.BOROSILICATE_GLASS)))
                .where('C', statePredicate(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN)))
                .where('P', statePredicate(GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.PTFE_PIPE)))
                .where('F', statePredicate(getFrameState()))
                .where('s', sensorPredicate())
                .where('e', emitterPredicate())
                .build();
    }

    public static IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(StainlessSteel).getDefaultState();
    }

    public static Predicate<BlockWorldState> sensorPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof SensorCasing)) {
                return false;
            } else {
                SensorCasing motorCasing = (SensorCasing) blockState.getBlock();
                SensorCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
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
                EmitterCasing motorCasing = (EmitterCasing) blockState.getBlock();
                EmitterCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
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
        //if (isStructureFormed()) {
        //    textList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.culinary_generator.unit_glucose", unitGlucose));
        //    textList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.culinary_generator.unit_triglyceride", unitTriglyceride));
        //}
        textList.add(new TextComponentTranslation("gregtech.multiblock.universal.framework", this.maxVoltage));
    }

    public IBlockState getCasingState() {
        return GTFO_OTHER_CASING.getState(GTFOOtherCasing.CasingType.BIOCHEMICAL);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return BIOCHEMICAL;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        EmitterCasing.CasingType emitter = context.getOrDefault("Emitter", EmitterCasing.CasingType.EMITTER_LV);
        SensorCasing.CasingType sensor = context.getOrDefault("Sensor", SensorCasing.CasingType.SENSOR_LV);

        maxTier = Math.min(emitter.getTier(), sensor.getTier());
        if (maxTier < 0)
            maxVoltage = 0;
        else
            maxVoltage = (long) (Math.pow(4, maxTier) * 8);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityCulinaryGenerator(metaTileEntityId);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return Textures.CHEMICAL_REACTOR_OVERLAY;
    }

    public class CulinaryGeneratorRecipeLogic extends GAMultiblockRecipeLogic {

        public CulinaryGeneratorRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        protected int unitGlucose;
        protected int unitTriglyceride;

        @Override
        protected int[] calculateOverclock(int EUt, long voltage, int duration) {
            int numMaintenanceProblems = (this.metaTileEntity instanceof GARecipeMapMultiblockController) ?
                    ((GARecipeMapMultiblockController) metaTileEntity).getNumProblems() : 0;

            double maintenanceDurationMultiplier = 1.0 - (0.2 * numMaintenanceProblems);
            int durationModified = (int) (duration * maintenanceDurationMultiplier);

            if (!allowOverclocking) {
                return new int[]{EUt, durationModified};
            }
            boolean negativeEU = EUt < 0;
            if (negativeEU)
                EUt = -EUt;
            int resultEUt = EUt;
            double resultDuration = durationModified;
            //Perfect Overclocking
            if (EUt <= 16) {
                int finalEUt = (resultEUt / 4);
                int finalDuration = (int) (resultDuration * 4);
                return new int[]{negativeEU ? -finalEUt : finalEUt, finalDuration};
            } else{
                while (resultDuration >= 4 && resultEUt <= GAValues.V[maxTier - 1]) {
                    resultEUt *= 4;
                    resultDuration /= 4;
                }
                //Precise total energy output
                int finalDuration = (int) Math.ceil(resultDuration);
                int finalEUt = (int) Math.ceil(resultEUt * (resultDuration / finalDuration));
                previousRecipeDuration = (int) resultDuration;
                return new int[]{negativeEU ? -finalEUt : finalEUt, finalDuration};
            }
        }
    }
}
