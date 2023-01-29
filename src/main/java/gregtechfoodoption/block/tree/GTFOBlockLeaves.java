package gregtechfoodoption.block.tree;

import com.google.common.collect.Lists;
import gregtech.core.CoreModule;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.block.GTFOTree;
import gregtechfoodoption.block.IVariantNamed;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class GTFOBlockLeaves extends BlockLeaves implements IVariantNamed {

    public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 3);
    private final int offset;

    public GTFOBlockLeaves(int offset) {
        this.offset = offset;
        setTranslationKey("gtfo_leaves_" + offset);
        setHardness(0.2F);
        this.setLightOpacity(1);
        setDefaultState(this.blockState.getBaseState()
                .withProperty(CHECK_DECAY, true)
                .withProperty(DECAYABLE, true)
                .withProperty(VARIANT, 0));
        GTFOMetaBlocks.GTFO_LEAVES.add(this);
        this.setCreativeTab(GTFOValues.TAB_GTFO);
        Blocks.FIRE.setFireInfo(this, 30, 60);
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int i) {
        return null;
    }

    public GTFOTree getTreeFromState(IBlockState state) {
        return GTFOTree.TREES.get(state.getValue(VARIANT) + (offset * 4));
    }

    @Nonnull
    @Override
    public Item getItemDropped(@Nonnull IBlockState state, @Nonnull Random rand, int fortune) {
        return Item.getItemFromBlock(getTreeFromState(state).saplingState.getBlock());
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
                .withProperty(DECAYABLE, (meta & 1) == 1)
                .withProperty(CHECK_DECAY, (meta & 2) == 2)
                .withProperty(VARIANT, (meta & 12) >> 2);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;
        if (state.getValue(DECAYABLE)) {
            meta |= 1;
        }
        if (state.getValue(CHECK_DECAY)) {
            meta |= 2;
        }
        meta |= state.getValue(VARIANT) << 2;
        return meta;
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return Lists.newArrayList(new ItemStack(this, 1, this.getMetaFromState(world.getBlockState(pos))));
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (int i = 0; i < 4; i++) {
            if (GTFOTree.TREES.size() <= i + offset * 4)
                break;
            items.add(new ItemStack(this, 1, i << 2));
        }
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, DECAYABLE, CHECK_DECAY, VARIANT);
    }

    @SideOnly(Side.CLIENT)
    public void registerColors() {
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> this.getTreeFromState(state).getBlockColor(state, worldIn, pos, tintIndex), this);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> this.getTreeFromState(this.getStateFromMeta(stack.getItemDamage())).getItemColor(stack, tintIndex), this);
    }

    @Override
    public String getVariantTranslationKey(IBlockState state) {
        return "gregtechfoodoption.leaves." + this.getTreeFromState(state).name;
    }

    // The following code mostly taken from GregTechCEu's BlockRubberLeaves.java

    @Override
    @Nonnull
    public BlockRenderLayer getRenderLayer() {
        if (!fancyLeaves()) {
            return super.getRenderLayer();
        }
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        if (!fancyLeaves()) {
            return super.isOpaqueCube(state);
        }
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean shouldSideBeRendered(@Nonnull IBlockState blockState, @Nonnull IBlockAccess blockAccess, @Nonnull BlockPos pos, @Nonnull EnumFacing side) {
        if (!fancyLeaves()) {
            return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        }
        return true;
    }

    private boolean fancyLeaves() {
        return CoreModule.proxy.isFancyGraphics();
    }

    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
        spawnAsEntity(worldIn, pos, ((GTFOBlockLeaves)state.getBlock()).getTreeFromState(state).getAppleDrop(chance));
    }

    // Primarily for getting the correct sapling type.
    @Override
    public int damageDropped(IBlockState state) {
        return (state.getValue(VARIANT) << 1) + ((offset % 2) * 8);
    }

    @Override
    @SuppressWarnings("deprecation")
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, state.getValue(VARIANT) << 2);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        LeafDecayHelper.leafDecay(this, worldIn, pos);
    }
}
