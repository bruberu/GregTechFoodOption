package gregtechfoodoption.block;

import gregtechfoodoption.GTFOValues;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GTFOPizzaBox extends Block {
    private static final AxisAlignedBB PIZZA_BOX_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
    private final Block pizzaBlock;

    public GTFOPizzaBox(String name, Block pizzaBlock) {
        super(Material.WOOD);
        this.pizzaBlock = pizzaBlock;
        setTranslationKey(name);
        setHardness(0.5F);
        setResistance(1.0F);
        setSoundType(SoundType.WOOD);
        setCreativeTab(GTFOValues.TAB_GTFO_BLOCKS);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return PIZZA_BOX_AABB;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return PIZZA_BOX_AABB;
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
    public boolean isTopSolid(IBlockState state) {
        return true;
    }

    @Override
    public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return side == EnumFacing.UP;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return face == EnumFacing.UP ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean onBlockActivated(net.minecraft.world.World worldIn, BlockPos pos, IBlockState state,
                                    net.minecraft.entity.player.EntityPlayer playerIn, net.minecraft.util.EnumHand hand,
                                    EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        if (pizzaBlock == null) {
            return false;
        }
        worldIn.setBlockState(pos, pizzaBlock.getDefaultState(), 3);
        return true;
    }
}
