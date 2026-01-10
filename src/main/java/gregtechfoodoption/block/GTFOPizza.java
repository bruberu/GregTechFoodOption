package gregtechfoodoption.block;

import gregtechfoodoption.GTFOValues;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class GTFOPizza extends Block {
    public static final PropertyInteger BITES = PropertyInteger.create("bites", 0, 3);
    private static final AxisAlignedBB PIZZA_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);

    public GTFOPizza(String name) {
        super(Material.CAKE);
        setTranslationKey(name);
        setHardness(0.5F);
        setSoundType(SoundType.CLOTH);
        setCreativeTab(GTFOValues.TAB_GTFO_BLOCKS);
        setDefaultState(getBlockState().getBaseState().withProperty(BITES, 0));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, BITES);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(BITES, Math.min(3, meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(BITES);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return PIZZA_AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand,
                                    EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        if (!playerIn.canEat(false)) {
            return false;
        }
        eatSlice(worldIn, pos, state, playerIn);
        return true;
    }

    private void eatSlice(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
        playerIn.getFoodStats().addStats(2, 0.1F);
        playerIn.addStat(StatList.CAKE_SLICES_EATEN);
        worldIn.playSound(null, pos, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F,
                worldIn.rand.nextFloat() * 0.1F + 0.9F);

        int bites = state.getValue(BITES);
        if (bites >= 3) {
            worldIn.setBlockToAir(pos);
        } else {
            worldIn.setBlockState(pos, state.withProperty(BITES, bites + 1), 3);
        }
    }
}
