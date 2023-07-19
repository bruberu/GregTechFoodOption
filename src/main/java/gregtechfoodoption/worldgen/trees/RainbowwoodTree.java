package gregtechfoodoption.worldgen.trees;

import gregtech.api.util.function.TriConsumer;
import gregtechfoodoption.block.GTFOTree;
import gregtechfoodoption.utils.GTFOUtils;
import gregtechfoodoption.worldgen.trees.condition.BiomeCondition;
import gregtechfoodoption.worldgen.trees.condition.TemperatureRainfallCondition;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

import java.util.Random;

import static gregtechfoodoption.GTFOMaterialHandler.RainbowSap;

public class RainbowwoodTree extends GTFOTree {
    public static final int[] RAINBOW_ARRAY = {
            0xff0000,
            0xff4000,
            0xff8000,
            0xffc000,
            0xffff00,
            0xc0ff00,
            0x80ff00,
            0x40ff00,
            0x00ff00,
            0x00ff40,
            0x00ff80,
            0x00ffc0,
            0x00ffff,
            0x00c0ff,
            0x0080ff,
            0x0040ff,
            0x0000ff,
            0x4000ff,
            0x8000ff,
            0xc000ff,
            0xff00ff,
            0xff00c0,
            0xff0080,
            0xff0040,
    };

    public RainbowwoodTree() {
        super("rainbowwood", 7);
        this.addCondition(new BiomeCondition(Biomes.PLAINS, 5, 0.89));
        this.addCondition(new BiomeCondition(Biomes.MUTATED_PLAINS, 5, 0.89));
        this.addCondition(new TemperatureRainfallCondition(5, 0.11, 0.8, 0.4, 0.1));
    }

    @Override
    public int getMinTrunkHeight(Random random) {
        return random.nextInt(3) + 6;
    }

    @Override
    protected void generateLeaves(World world, BlockPos.MutableBlockPos pos, int height, Random random, TriConsumer<World, BlockPos, IBlockState> notifier) {
        BlockPos.MutableBlockPos currentYPos = GTFOUtils.copy(pos);
        currentYPos.move(EnumFacing.UP, height - 3);
        for (int i = 0; i < 7; i++) {
            int layerSize = getMooreRadiusAtHeight(i + height - 3, height);
            Iterable<BlockPos> iterator = BlockPos.getAllInBox(
                    currentYPos.offset(EnumFacing.NORTH, layerSize).offset(EnumFacing.WEST, layerSize),
                    currentYPos.offset(EnumFacing.SOUTH, layerSize).offset(EnumFacing.EAST, layerSize));
            iterator.forEach(leavesPos -> {
                if (Math.abs(leavesPos.getX() - currentYPos.getX()) + Math.abs(leavesPos.getZ() - currentYPos.getZ()) < 6)
                    notifier.accept(world, leavesPos, getNaturalLeavesState());
            });
            currentYPos.move(EnumFacing.UP);
        }
    }

    @Override
    public int getBlockColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
        return RAINBOW_ARRAY[(Math.abs(pos.getX()) + Math.abs(pos.getY()) + Math.abs(pos.getZ())) % RAINBOW_ARRAY.length];
    }

    @Override
    public int getItemColor(ItemStack stack, int tintIndex) {
        return 0x8F00FF;
    }

    @Override
    protected int getMooreRadiusAtHeight(int height, int trunkHeight) {
        if (height < trunkHeight - 3)
            return 0;
        if (height == trunkHeight - 3)
            return 2;
        if (height < trunkHeight + 3)
            return 3;
        return 2;
    }

    @Override
    public double getPerlinScale() {
        return 0.05;
    }

    @Override
    public Fluid getSap() {
        return RainbowSap.getFluid();
    }
}
