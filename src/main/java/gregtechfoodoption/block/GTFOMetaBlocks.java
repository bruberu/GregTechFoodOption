package gregtechfoodoption.block;

import com.google.common.collect.ImmutableMap;
import gregtech.api.GTValues;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;
import gregtechfoodoption.block.tree.GTFOBlockLeaves;
import gregtechfoodoption.block.tree.GTFOBlockLog;
import gregtechfoodoption.block.tree.GTFOBlockPlanks;
import gregtechfoodoption.block.tree.GTFOBlockSapling;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;
import java.util.stream.Collectors;

public class GTFOMetaBlocks {
    public static GTFOBlockCasing GTFO_CASING;
    public static GTFOMetalCasing GTFO_METAL_CASING;
    public static GTFOGlassCasing GTFO_GLASS_CASING;

    public static List<GTFOBlockLeaves> GTFO_LEAVES = new ArrayList<>();
    public static List<GTFOBlockLog> GTFO_LOGS = new ArrayList<>();
    public static List<GTFOBlockPlanks> GTFO_PLANKS = new ArrayList<>();
    public static List<GTFOBlockSapling> GTFO_SAPLINGS = new ArrayList<>();

    public static void init() {
        GTFO_CASING = new GTFOBlockCasing();
        GTFO_CASING.setRegistryName("gtfo_casing");

        GTFO_METAL_CASING = new GTFOMetalCasing();
        GTFO_METAL_CASING.setRegistryName("gtfo_metal_casing");

        GTFO_GLASS_CASING = new GTFOGlassCasing();
        GTFO_GLASS_CASING.setRegistryName("gtfo_glass_casing");

        GTFOTrees.init();
        for (int i = 0; i <= (GTFOTree.TREES.size() - 1) / 4; i++) {
            GTFOBlockLeaves leaves = new GTFOBlockLeaves(i);
            leaves.setRegistryName("gtfo_leaves_" + i);
        }
        for (int i = 0; i <= (GTFOTree.TREES.size() - 1) / 4; i++) {
            GTFOBlockLog log = new GTFOBlockLog(i);
            log.setRegistryName("gtfo_log_" + i);
        }
        for (int i = 0; i <= (GTFOTree.TREES.size() - 1) / 8; i++) {
            GTFOBlockSapling sapling = new GTFOBlockSapling(i);
            sapling.setRegistryName("gtfo_sapling_" + i);
        }
        for (int i = 0; i <= (GTFOTree.TREES.size() - 1) / 16; i++) {
            GTFOBlockPlanks planks = new GTFOBlockPlanks(i);
            planks.setRegistryName("gtfo_planks_" + i);
        }

        GTFOCrops.init();
        GTFOTree.TREES.forEach(GTFOTree::setupBlocks);
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {
        registerItemModel(GTFO_CASING);
        registerItemModel(GTFO_METAL_CASING);
        registerItemModel(GTFO_GLASS_CASING);
        GTFO_LEAVES.forEach(GTFOMetaBlocks::registerItemModel);
        GTFO_SAPLINGS.forEach(GTFOMetaBlocks::registerItemModel);
        for (GTFOBlockSapling sapling : GTFO_SAPLINGS) {
            registerItemModel(sapling);
            for (int v = 0; v < 8; v++)
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(sapling), v << 1,
                    new ModelResourceLocation(sapling.getRegistryName() + "_" + v, "inventory"));
        }
        for (GTFOBlockLog log : GTFO_LOGS) {
            registerItemModelWithOverride(log, ImmutableMap.of(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
        }
        GTFO_PLANKS.forEach(GTFOMetaBlocks::registerItemModel);
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(Block block) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            //noinspection ConstantConditions
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                    block.getMetaFromState(state),
                    new ModelResourceLocation(block.getRegistryName(),
                            statePropertiesToString(state.getProperties())));
        }
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModelWithOverride(Block block, Map<IProperty<?>, Comparable<?>> stateOverrides) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            HashMap<IProperty<?>, Comparable<?>> stringProperties = new HashMap<>(state.getProperties());
            stringProperties.putAll(stateOverrides);
            //noinspection ConstantConditions
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                    block.getMetaFromState(state),
                    new ModelResourceLocation(block.getRegistryName(),
                            statePropertiesToString(stringProperties)));
        }
    }

    public static void registerOreDict() {
        GTFO_LEAVES.forEach(leaves -> {
            OreDictUnifier.registerOre(new ItemStack(leaves, 1, GTValues.W), "treeLeaves");
        });
        GTFO_SAPLINGS.forEach(sapling -> {
            OreDictUnifier.registerOre(new ItemStack(sapling, 1, GTValues.W), "treeSapling");
        });
        GTFO_LOGS.forEach(log -> {
            OreDictUnifier.registerOre(new ItemStack(log, 1, GTValues.W), OrePrefix.log, Materials.Wood);
            GameRegistry.addSmelting(new ItemStack(log, 1, GTValues.W), new ItemStack(Items.COAL, 1, 1), 0.15F);
        });
        GTFO_PLANKS.forEach(planks -> {
            OreDictUnifier.registerOre(new ItemStack(planks, 1, GTValues.W), OrePrefix.plank, Materials.Wood);
            OreDictUnifier.registerOre(new ItemStack(planks, 1, GTValues.W), new ItemMaterialInfo(new MaterialStack(Materials.Wood, GTValues.M)));
        });
    }

    private static String statePropertiesToString(Map<IProperty<?>, Comparable<?>> properties) {
        StringBuilder stringbuilder = new StringBuilder();

        List<Map.Entry<IProperty<?>, Comparable<?>>> entries = properties.entrySet().stream()
                .sorted(Comparator.comparing(c -> c.getKey().getName()))
                .collect(Collectors.toList());

        for (Map.Entry<IProperty<?>, Comparable<?>> entry : entries) {
            if (stringbuilder.length() != 0) {
                stringbuilder.append(",");
            }

            IProperty<?> property = entry.getKey();
            stringbuilder.append(property.getName());
            stringbuilder.append("=");
            stringbuilder.append(getPropertyName(property, entry.getValue()));
        }

        if (stringbuilder.length() == 0) {
            stringbuilder.append("normal");
        }

        return stringbuilder.toString();
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> String getPropertyName(IProperty<T> property, Comparable<?> value) {
        return property.getName((T) value);
    }

    @SideOnly(Side.CLIENT)
    public static void registerStateMappers() {
    }

    @SideOnly(Side.CLIENT)
    public static void registerColors() {
        GTFO_LEAVES.forEach(GTFOBlockLeaves::registerColors);
    }
}
