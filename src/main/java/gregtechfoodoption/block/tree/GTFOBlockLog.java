package gregtechfoodoption.block.tree;

import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.block.GTFOTree;
import gregtechfoodoption.block.IVariantNamed;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

public class GTFOBlockLog extends BlockLog implements IVariantNamed {
    public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 3);
    private final int offset;

    public GTFOBlockLog(int offset) {
        this.offset = offset;
        setTranslationKey("gtfo_log_" + offset);
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

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (int i = 0; i < 4; i++) {
            if (GTFOTree.TREES.size() <= i + offset * 4)
                break;
            items.add(new ItemStack(this, 1, i << 2));
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT) << 2;
    }
}
