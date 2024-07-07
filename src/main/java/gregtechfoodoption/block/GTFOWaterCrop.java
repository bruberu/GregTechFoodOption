package gregtechfoodoption.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class GTFOWaterCrop extends GTFOCrop {
    protected static final AxisAlignedBB WATER_CROP_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.09375D, 0.9375D);

    protected GTFOWaterCrop(String name) {
        super(name);
    }

    public static GTFOWaterCrop create(String name) {
        return new GTFOWaterCrop(name);
    }
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return WATER_CROP_AABB;
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        {
            IBlockState soil = worldIn.getBlockState(pos.down());
            return soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
        }
        return this.canSustainBush(worldIn.getBlockState(pos.down())) && acceptableSoil(worldIn.getBlockState(pos.down(2)));
    }

    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.WATER;
    }

    protected boolean acceptableSoil(IBlockState state) {
        return state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.GRASS;
    }
}
