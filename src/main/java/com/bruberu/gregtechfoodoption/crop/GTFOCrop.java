package com.bruberu.gregtechfoodoption.crop;

import com.bruberu.gregtechfoodoption.item.GTFOMetaItem;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

import static com.bruberu.gregtechfoodoption.GTFOMaterialHandler.POPCORN_KERNEL;
import static com.bruberu.gregtechfoodoption.item.GTFOMetaItem.POPCORN_EAR;


public class GTFOCrop extends BlockCrops implements IGrowable {
    public static final PropertyInteger CROP_AGE = PropertyInteger.create("age", 0, 4);
    private static final AxisAlignedBB[] CROP_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.35D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.40D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D)};
    private final Item SEED_ITEM;
    private final Item CROP_ITEM;
    private final int MAX_AGE;

    public static GTFOCrop POPCORN_CROP;

    public static void init()
    {
        POPCORN_CROP = new GTFOCrop("crop_popcorn", POPCORN_KERNEL, POPCORN_EAR.getMetaItem(), 5);
    }

    public GTFOCrop(String registryName, Item seed, Item crop, int maxAge)
    {
        super();
        this.setRegistryName(registryName);
        this.setTranslationKey(registryName);

        this.SEED_ITEM = seed;
        this.CROP_ITEM = crop;
        this.MAX_AGE = maxAge;
    }
    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.FARMLAND;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return (worldIn.getLight(pos) >= 8 || worldIn.canSeeSky(pos)) && soil.getBlock() == Blocks.FARMLAND;
    }

    @Override
    protected PropertyInteger getAgeProperty()
    {
        return CROP_AGE;
    }

    @Override
    public int getMaxAge()
    {
        return MAX_AGE;
    }

    @Override
    protected Item getSeed()
    {
        return SEED_ITEM;
    }

    @Override
    protected Item getCrop()
    {
        return CROP_ITEM;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (rand.nextInt(4) == 0)
        {
            this.checkAndDropBlock(worldIn, pos, state);
        }
        else
        {
            super.updateTick(worldIn, pos, state, rand);
        }
    }

    @Override
    protected int getBonemealAgeIncrease(World worldIn)
    {
        return MathHelper.getInt(worldIn.rand, 1, 3);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, CROP_AGE);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return CROP_AABB[(Integer) state.getValue(this.getAgeProperty())];
    }
}
