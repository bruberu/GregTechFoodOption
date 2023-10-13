package gregtechfoodoption.block;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
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

    protected GTFOBerryBush(String name) {
        super(name, 2);
        this.setTranslationKey("gtfo_berry_bush_" + name);
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
            if (!seed.isEmpty()) {
                drops.add(seed.copy());
                if (rand.nextInt(9) == 0) {
                    drops.add(seed.copy());
                }
            }

            for (int i = 0; i < 3 + efficiency; ++i) {
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
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();

        if (i > j)
        {
            i = j;
        }

        worldIn.setBlockState(pos, withEfficiency(this.withAge(i), getEfficiency(worldIn, pos, state)), 2);
    }

    public int getEfficiency(World worldIn, BlockPos pos, IBlockState state) {
        int[] efficiencies = new int[EFFICIENCY_GTFO.getAllowedValues().stream().max(Integer::compare).get() + 1];
        BlockPos.getAllInBox(pos.east().north(), pos.west().south()).forEach((blockpos) -> {
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
        return state.withProperty(EFFICIENCY_GTFO, Integer.valueOf(efficiency));
    }

    public int getGrowthSlowdown(World world, BlockPos pos, IBlockState state) {
        int growthSlowdown = 320 << getEfficiency(state);
        if (!world.isDaytime()) {
            growthSlowdown *= 2;
        }
        if (world.isRaining()) {
            growthSlowdown -= 10;
        }

        return growthSlowdown;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return this.getBoundingBox(blockState, worldIn, pos);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return this.getAge(state) == 0 ? SMALL_AABB : Block.FULL_BLOCK_AABB;
    }

    public int getMaxAge() {
        return 2;
    }

}
