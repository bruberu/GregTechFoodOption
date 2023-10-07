package gregtechfoodoption.block;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class GTFOBerryBush extends GTFOCrop {
    public static final PropertyInteger EFFICIENCY_GTFO = PropertyInteger.create("efficiency", 0, 4);

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
        return state.getValue(EFFICIENCY_GTFO).intValue();
    }

    @Override
    public void grow(World worldIn, BlockPos pos, IBlockState state) {
        super.grow(worldIn, pos, state);
        addEfficiency(worldIn, pos, state);
    }

    public void addEfficiency(World worldIn, BlockPos pos, IBlockState state) {
        int[] efficiencies = new int[EFFICIENCY_GTFO.getAllowedValues().stream().max(Integer::compare).get()];
        BlockPos.getAllInBox(pos.east().north(), pos.west().south()).forEach((blockpos) -> {
            efficiencies[getEfficiency(worldIn.getBlockState(blockpos))]++;
        });
        for (int i = efficiencies.length - 1; i >= 0; --i) {
            if (i > 2) {
                worldIn.setBlockState(pos, withEfficiency(state, i + 1), 3);
            }
        }
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
}
