package gregtechfoodoption.block;

import gregtechfoodoption.utils.GTFOLog;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class GTFOBerryBush extends GTFOCrop {
    public static final PropertyInteger EFFICIENCY_GTFO = PropertyInteger.create("efficiency", 0, 4);
    private static final AxisAlignedBB SMALL_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
    private static final AxisAlignedBB LARGE_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
    private static final AxisAlignedBB STEM_AABB = new AxisAlignedBB(0.4325D, 0.0D, 0.4325D, 0.5675D, 0.25D, 0.5675D);


    private boolean isThorny = false;

    protected GTFOBerryBush(String name) {
        super(name, 2);
        this.setTranslationKey("gtfo_berry_bush_" + name);
        this.setHardness(1F);
    }

    @Override
    protected boolean canSustainBush(IBlockState state) {
        return state.getBlock() == Blocks.DIRT || super.canSustainBush(state);
    }

    public static GTFOBerryBush create(String name) {
        AGE_TEMP = PropertyInteger.create("age", 0, 2);
        return new GTFOBerryBush(name);
    }

    protected BlockStateContainer createBlockState() {
        return AGE_GTFO == null ? new BlockStateContainer(this, AGE_TEMP, EFFICIENCY_GTFO) : new BlockStateContainer(this, AGE_GTFO, EFFICIENCY_GTFO);
    }

    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        int age = this.getAge(state);
        int efficiency = this.getEfficiency(state);
        Random rand = world instanceof World ? ((World) world).rand : new Random();

        if (age >= this.getMaxAge()) {
            drops.add(this.crop.copy());
            for (int i = 0; i < 2 + efficiency; ++i) {
                if (rand.nextInt(2) == 0) {
                    drops.add(this.crop.copy());
                }
            }
        }

    }

    public int getEfficiency(IBlockState state) {
        return state.getProperties().get(EFFICIENCY_GTFO) != null ? state.getValue(EFFICIENCY_GTFO).intValue() : -1;
    }

    @Override
    public void grow(World worldIn, BlockPos pos, IBlockState state) {
        if (worldIn.rand.nextInt(Math.max(2, getGrowthSlowdown(worldIn, pos, state) / 8)) != 0) {
            return;
        }
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();

        if (i > j)
        {
            i = j;
        }

        worldIn.setBlockState(pos, withEfficiency(this.withAge(i), calcEfficiency(worldIn, pos)), 3);
    }

    public int calcEfficiency(World worldIn, BlockPos pos) {
        int[] efficiencies = new int[EFFICIENCY_GTFO.getAllowedValues().stream().max(Integer::compare).get() + 1];
        BlockPos.getAllInBox(pos.east().north(), pos.west().south()).forEach((blockpos) -> {
            if (!blockpos.equals(pos) && getEfficiency(worldIn.getBlockState(blockpos)) + 1 < efficiencies.length)
                efficiencies[getEfficiency(worldIn.getBlockState(blockpos)) + 1]++;
        });
        for (int i = efficiencies.length - 1; i >= 0; --i) {
            if (efficiencies[i] > 2) {
                return i;
            }
        }
        return 0;
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return 1;
    }

    public IBlockState withEfficiency(IBlockState state, int efficiency) {
        if (efficiency > 4) {
            efficiency = 4;
            GTFOLog.logger.warn("Somehow, you managed to get your berry's efficiency higher than 4, which is really cool (or the result of a hacked mod/bug), but it's currently not available in GTFO. Please report this to the mod author, along with a screenshot of how great your berry setup is.");
        }
        return state.withProperty(EFFICIENCY_GTFO, Integer.valueOf(efficiency));
    }

    public int getGrowthSlowdown(World world, BlockPos pos, IBlockState state) {
        if (getAge(state) == 0) {
            return 4; // Usual value for growing crops
        }
        int growthSlowdown = 320 << getEfficiency(state);
        if (!world.isDaytime()) {
            growthSlowdown *= 2;
        }
        if (world.isRaining()) {
            growthSlowdown = growthSlowdown * 2 / 3;
        }

        return growthSlowdown;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (isThorny && entityIn instanceof EntityLiving)
            entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0F);
        double distanceFromCenter = entityIn.getDistanceSq(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
        distanceFromCenter += 0.5; // no singularity going on here
        distanceFromCenter /= 4;
        entityIn.stepHeight = 0.125F;
        entityIn.motionX *= distanceFromCenter;
        entityIn.motionY *= distanceFromCenter;
        entityIn.motionZ *= distanceFromCenter;
    }

    public GTFOBerryBush setThorny(boolean thorny) {
        isThorny = thorny;
        return this;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return STEM_AABB;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return this.getAge(state) == 0 ? SMALL_AABB : LARGE_AABB;
    }

    public int getMaxAge() {
        return 2;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (this.isMaxAge(state)) {
            int berries = 1;
            for (int i = 0; i < 2 + getEfficiency(state); ++i) {
                if (worldIn.rand.nextInt(2) == 0) {
                    berries++;
                }
            }

            ItemStack berryStack = this.getCropStack().copy();
            berryStack.setCount(berries);
            if (!playerIn.addItemStackToInventory(berryStack)) {
                playerIn.dropItem(this.getCropStack(), false);
            }
            worldIn.setBlockState(pos, state.withProperty(AGE_GTFO, Integer.valueOf(this.getMaxAge() - 1)), 3);
            return true;
        }
        return false;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.withAge(meta % 3).withProperty(EFFICIENCY_GTFO, Integer.valueOf(meta / 3));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return this.getEfficiency(state) * 3 + this.getAge(state);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!(worldIn.getBlockState(fromPos).getBlock() instanceof GTFOBerryBush)) {
            // We don't want crops transmuting to higher efficiencies.
            int newEfficiency = Math.min(calcEfficiency(worldIn, pos), getEfficiency(state));
            worldIn.setBlockState(pos, state.withProperty(EFFICIENCY_GTFO, newEfficiency), 3);
        }
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }
}
