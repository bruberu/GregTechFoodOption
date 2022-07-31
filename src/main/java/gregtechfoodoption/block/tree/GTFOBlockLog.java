package gregtechfoodoption.block.tree;

import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.block.GTFOTree;
import gregtechfoodoption.block.IVariantNamed;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import javax.annotation.Nonnull;

public class GTFOBlockLog extends BlockLog implements IVariantNamed {
    public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 3);
    private final int offset;

    public GTFOBlockLog(int offset) {
        this.offset = offset;
        setTranslationKey("gtfo_log_" + (offset + 1));
        setHarvestLevel("axe", 0);
        setDefaultState(this.blockState.getBaseState()
                .withProperty(LOG_AXIS, EnumAxis.Y));
        GTFOMetaBlocks.GTFO_LOGS.add(this);
        Blocks.FIRE.setFireInfo(this, 5, 5);
        this.setCreativeTab(GTFOValues.TAB_GTFO);
    }

    @Override
    public String getVariantTranslationKey(IBlockState state) {
        return "gregtechfoodoption.log." + this.getTreeFromState(state).name;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;
        meta |= state.getValue(LOG_AXIS).ordinal();
        meta |= state.getValue(VARIANT) << 2;
        return meta;
    }

    public GTFOTree getTreeFromState(IBlockState state) {
        return GTFOTree.TREES.get(state.getValue(VARIANT) + (offset * 4));
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
                .withProperty(LOG_AXIS, EnumAxis.values()[(meta & 3)])
                .withProperty(VARIANT, (meta & 12) >> 2);
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS, VARIANT);
    }


    /*

    public enum BlockType implements IStringSerializable {

        BANANA_LOG("banana"),
        ORANGE_LOG("orange"),
        MANGO_LOG("mango"),
        APRICOT_LOG("apricot"),
        LEMON_LOG("lemon"),
        LIME_LOG("lime"),
        OLIVE_LOG("olive");

        private final String name;

        BlockType(String name) {
            this.name = name;
        }

        @Nonnull
        @Override
        public String getName() {
            return this.name;
        }
    }

     */

}
