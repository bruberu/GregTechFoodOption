package gregtechfoodoption.block;

import gregtechfoodoption.block.tree.GTFOBlockLeaves;
import gregtechfoodoption.block.tree.GTFOBlockLog;
import gregtechfoodoption.block.tree.GTFOBlockPlanks;
import gregtechfoodoption.block.tree.GTFOBlockSapling;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GTFOMetaBlocks {
    public static GTFOBlockCasing GTFO_CASING;
    public static GTFOMetalCasing GTFO_METAL_CASING;

    public static List<GTFOBlockLeaves> GTFO_LEAVES = new ArrayList<>();
    public static List<GTFOBlockLog> GTFO_LOGS = new ArrayList<>();
    public static List<GTFOBlockPlanks> GTFO_PLANKS = new ArrayList<>();
    public static List<GTFOBlockSapling> GTFO_SAPLINGS = new ArrayList<>();

    public static void init() {
        GTFO_CASING = new GTFOBlockCasing();
        GTFO_CASING.setRegistryName("gtfo_casing");

        GTFO_METAL_CASING = new GTFOMetalCasing();
        GTFO_METAL_CASING.setRegistryName("gtfo_metal_casing");

        for (int i = 0; i <= GTFOTree.TREES.size() / 4; i++) {
            GTFOBlockLeaves leaves = new GTFOBlockLeaves(i);
            leaves.setRegistryName("gtfo_leaves_" + i);
        }

        GTFOCrops.init();
        GTFOTrees.init();
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {
        registerItemModel(GTFO_CASING);
        registerItemModel(GTFO_METAL_CASING);
        for (GTFOBlockLeaves leaves : GTFO_LEAVES) {
            registerItemModel(leaves);
        }
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
