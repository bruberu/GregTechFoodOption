package gregtechfoodoption.block;

import gregtechfoodoption.GregTechFoodOption;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
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
    protected final PropertyInteger AGE_GTFO;

    public static final PropertyInteger DEFAULT_AGE = PropertyInteger.create("age", 0, 5);
    private static final AxisAlignedBB CROPS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
    protected ItemStack seed;
    protected ItemStack crop;
    public static List<GTFOCrop> CROP_BLOCKS = new ArrayList<>();
    private String name;

    protected GTFOCrop(String name, PropertyInteger age) {
        AGE_GTFO = age;
        this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), 0));
        this.setRegistryName(GregTechFoodOption.MODID, "crop_" + name);
        CROP_BLOCKS.add(this);
        this.name = name;
        this.setTranslationKey("gtfo_crop_" + name);
    }

    protected GTFOCrop(String name) {
        this(name, DEFAULT_AGE);
    }

    public static GTFOCrop create(String name) {
        return new GTFOCrop(name);
    }

    public static GTFOCrop create(String name, int min, int max) {
        return new GTFOCrop(name, PropertyInteger.create("age", min, max));
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
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
        Random rand = world instanceof World ? ((World) world).rand : new Random();

        if (age >= this.getMaxAge()) {
            if (!seed.isEmpty()) {
                ItemStack seedStack = seed.copy();
                if (rand.nextInt(9) == 0) seedStack.setCount(seedStack.getCount() + 1);
                drops.add(seedStack);
            }

            int cropCount = 0;

            for (int i = 0; i < 3 + fortune; ++i) {
                if (rand.nextInt(2 * this.getMaxAge()) <= age) {
                    cropCount++;
                }
            }

            if (cropCount > 0) {
                ItemStack crop = this.crop.copy();
                crop.setCount(cropCount);
                drops.add(crop);
            }
        }

    }

    public Item getSeed() {
        return seed.getItem();
    }

    public Item getCrop() {
        return crop.getItem();
    }

    public ItemStack getCropStack() {
        return crop;
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

    // The One Probe needs this!
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return this.seed;
    }

    public ItemStack getSeedStack() {
        return this.seed;
    }

    @Override
    public PropertyInteger getAgeProperty() {
        return AGE_GTFO;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, getAgeProperty());
    }

    public String getName() {
        return this.name;
    }
}
