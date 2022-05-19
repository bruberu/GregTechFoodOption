package gregtechfoodoption.block;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GTFOCrop extends BlockCrops {

    private static final AxisAlignedBB CROPS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
    protected ItemStack seed;
    protected ItemStack crop;
    public static List<GTFOCrop> CROP_BLOCKS = new ArrayList<>();

    public GTFOCrop(String name) {
        super();
        this.setRegistryName("gregtechfoodoption", "crop_" + name);
        //this.setCreativeTab((CreativeTabs)null);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
        CROP_BLOCKS.add(this);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return CROPS_AABB;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }

    public int getMaxAge() {
        return 5;
    }

    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        int age = this.getAge(state);
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (!seed.isEmpty()) {
            drops.add(seed.copy());
        }

        if (age >= this.getMaxAge()) {
            int k = 3 + fortune;

            for(int i = 0; i < 3 + fortune; ++i) {
                if (rand.nextInt(2 * this.getMaxAge()) <= age) {
                    drops.add(this.crop.copy());
                }
            }
        }

    }

    public Item getSeed() {
        return seed.getItem();
    }

    public Item getCrop() {
        return crop.getItem();
    }

    public void setSeed(ItemStack seed) {
        this.seed = seed;
    }

    public void setCrop(ItemStack crop) {
        this.crop = crop;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return seed.getItemDamage();
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return this.seed;
    }
}
