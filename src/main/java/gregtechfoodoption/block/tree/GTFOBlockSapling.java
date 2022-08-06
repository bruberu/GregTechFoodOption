package gregtechfoodoption.block.tree;

import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.block.GTFOTree;
import gregtechfoodoption.block.IVariantNamed;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import javax.annotation.Nonnull;
import java.util.Random;

import static net.minecraft.block.BlockSapling.STAGE;

public class GTFOBlockSapling extends BlockBush implements IGrowable, IVariantNamed {

    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.1, 0.0D, 0.1, 0.9, 0.8, 0.9);

    public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 7);
    public final int offset;

    public GTFOBlockSapling(int offset) {
        super(Material.LEAVES);
        this.offset = offset;
        setTranslationKey("gtfo_leaves_" + offset);
        this.setTickRandomly(true);
        this.setHardness(0.0F);
        this.setLightOpacity(1);
        this.setSoundType(SoundType.PLANT);
        GTFOMetaBlocks.GTFO_SAPLINGS.add(this);
        this.setCreativeTab(GTFOValues.TAB_GTFO);
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STAGE, VARIANT);
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(STAGE, (meta & 1)).withProperty(VARIANT, (meta & 14) >> 1);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i |= state.getValue(STAGE);
        i |= state.getValue(VARIANT) << 1;
        return i;
    }

    @Override
    public String getVariantTranslationKey(IBlockState state) {
        return "gregtechfoodoption.sapling." + this.getTreeFromState(state).name;
    }

    public GTFOTree getTreeFromState(IBlockState state) {
        return GTFOTree.TREES.get(state.getValue(VARIANT) + (offset * 8));
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos) {
        return SAPLING_AABB;
    }

    @Override
    public boolean canGrow(@Nonnull World world, @Nonnull BlockPos blockPos, @Nonnull IBlockState iBlockState, boolean b) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(@Nonnull World world, @Nonnull Random random, @Nonnull BlockPos blockPos, @Nonnull IBlockState iBlockState) {
        return true;
    }

    @Override
    public boolean canBeReplacedByLeaves(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
        return true;
    }

    @Override
    public void grow(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        this.getTreeFromState(state).getTreeGrowInstance().generate(worldIn, rand, pos);
    }

    @Override
    @Nonnull
    public EnumPlantType getPlantType(@Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
        return EnumPlantType.Plains;
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (int i = 0; i < 8; i++) {
            if (GTFOTree.TREES.size() <= i + offset * 8)
                break;
            items.add(new ItemStack(this, 1, i * 2));
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT) << 1;
    }
}
