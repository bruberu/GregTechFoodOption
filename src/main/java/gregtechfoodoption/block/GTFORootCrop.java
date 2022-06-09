package gregtechfoodoption.block;

import gregtech.api.util.GTUtility;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class GTFORootCrop extends GTFOCrop {
    protected GTFORootCrop(String name) {
        super(name, 7);
    }

    public static GTFORootCrop create(String name) {
        AGE_TEMP = PropertyInteger.create("age", 0, 7);
        return new GTFORootCrop(name);
    }

    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        int age = this.getAge(state);
        Random rand = world instanceof World ? ((World) world).rand : new Random();

        if (age >= this.getMinHarvestingAge() && age <= this.getMaxHarvestingAge()) {
            for (int i = 0; i < 1 + fortune; ++i) {
                drops.add(this.crop.copy());
            }
        } else if (age >= this.getMaxAge()) {
            drops.add(GTUtility.copyAmount(3 + fortune, this.seed));
        }

    }

    public int getMinHarvestingAge() {
        return 4;
    }

    public int getMaxHarvestingAge() {
        return 5;
    }

    public int getMaxAge() {
        return 7;
    }

    public boolean seedHarvestable(IBlockState state) {
        return this.getAge(state) == getMaxAge();
    }

    public boolean cropHarvestable(IBlockState state) {
        return this.getAge(state) <= getMaxHarvestingAge() && this.getAge(state) >= getMinHarvestingAge();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (this.getAge(state) >= this.getMaxAge()) {
            Random rand = world instanceof World ? ((World) world).rand : new Random();
            spawnAsEntity(world, pos, GTUtility.copyAmount(rand.nextInt(2) + 1, this.seed));
            world.setBlockState(pos, this.withAge(this.getAge(state) - 1), 2);
        }

        return super.onBlockActivated(world, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
