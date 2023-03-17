package gregtechfoodoption.block.tree;

import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.block.GTFOTree;
import gregtechfoodoption.block.IVariantNamed;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class GTFOBlockPlanks extends Block implements IVariantNamed {
    public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 15);

    private final int offset;

    public GTFOBlockPlanks(int offset) {
        super(Material.WOOD);
        this.offset = offset;
        setTranslationKey("gtfo_planks_" + offset);
        setHardness(2.0F);
        setResistance(5.0F);
        setHarvestLevel("axe", 0);
        Blocks.FIRE.setFireInfo(this, 5, 20);
        GTFOMetaBlocks.GTFO_PLANKS.add(this);
        this.setCreativeTab(GTFOValues.TAB_GTFO);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public String getVariantTranslationKey(IBlockState state) {
        try {
            return "gregtechfoodoption.planks." + this.getTreeFromState(state).name;
        } catch (IndexOutOfBoundsException e) {
            return "gregtechfoodoption.hello_buildcraft";
        }
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT);
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, meta);
    }

    public GTFOTree getTreeFromState(IBlockState state) {
        return GTFOTree.TREES.get(state.getValue(VARIANT) + (offset * 16));
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (int i = 0; i < 16; i++) {
            if (GTFOTree.TREES.size() <= i + offset * 16)
                break;
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }
}
