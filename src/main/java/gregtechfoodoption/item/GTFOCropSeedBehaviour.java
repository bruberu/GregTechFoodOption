package gregtechfoodoption.item;

import codechicken.lib.raytracer.RayTracer;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtechfoodoption.block.GTFOCrop;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

// bri'ish class
public class GTFOCropSeedBehaviour implements IItemBehaviour {
    private final IBlockState placeState;

    public GTFOCropSeedBehaviour(GTFOCrop cropBlock, ItemStack seed, ItemStack crop) {
        cropBlock.setSeed(seed);
        cropBlock.setCrop(crop);
        this.placeState = cropBlock.getDefaultState();
    }

    public GTFOCropSeedBehaviour(Block block) {
        this(block.getDefaultState());
    }

    public GTFOCropSeedBehaviour(IBlockState state) {
        this.placeState = state;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        RayTraceResult result = RayTracer.retrace(player);

        if (result != null && world.isAirBlock(result.getBlockPos().up()) && this.placeState.getBlock().canPlaceBlockAt(world, result.getBlockPos().up())) {
            world.setBlockState(result.getBlockPos().up(), this.placeState);
            ItemStack heldItem = player.getHeldItem(hand);
            heldItem.shrink(1);
            return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
        }

        return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
    }

}
