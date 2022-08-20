package gregtechfoodoption.machines.farmer;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public class StemFarmerMode extends CustomCropFarmerMode {
    public StemFarmerMode(Block crop, Item seed) {
        super(crop, seed);
    }

    @Override
    public boolean canPlaceAt(MutableBlockPos operationPos, MutableBlockPos farmerPos, EnumFacing facing, World world) {
        return (((operationPos.getX() - farmerPos.getX()) + (operationPos.getZ() - farmerPos.getZ())) % 2 == 0)
                && Math.abs(operationPos.getX() - farmerPos.move(facing, MetaTileEntityFarmer.LENGTH / 2 + 1).getX()) != 4 && Math.abs(operationPos.getZ() - farmerPos.move(facing, MetaTileEntityFarmer.LENGTH / 2 + 1).getZ()) != 4;
    }
}
