package gregtechfoodoption.item;

import gregtechfoodoption.block.GTFOBerryBush;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

// bri'ish class
public class GTFOBerrySeedBehaviour extends GTFOCropSeedBehaviour {

    public GTFOBerrySeedBehaviour(GTFOBerryBush cropBlock, ItemStack seed, ItemStack crop) {
        super(cropBlock, seed, crop);
    }

    @Override
    public ActionResult<ItemStack> onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!isBlocked(world, pos, player)) {
            return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
        }
        return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
    }

    private boolean isBlocked(World world, BlockPos pos, EntityPlayer player) {
        AtomicInteger areAnyBlocked = new AtomicInteger(0);
        AtomicReference<BlockPos> blockedCrop = new AtomicReference<>();
        BlockPos.getAllInBox(pos.up().east().north(), pos.up().west().south()).forEach((crop) -> {
            if (crop.equals(pos.up()) || world.getBlockState(crop).getBlock() instanceof GTFOBerryBush) {
                AtomicBoolean isBlocked = new AtomicBoolean(true);
                BlockPos.getAllInBox(crop.east().north(), crop.west().south()).forEach((blockpos) -> {
                    if (!blockpos.equals(pos.up()) && world.getBlockState(blockpos).getBlock().isAir(world.getBlockState(blockpos), world, blockpos)) {
                        isBlocked.set(false);
                    }
                });
                if (isBlocked.get()) {
                    blockedCrop.set(crop);
                    areAnyBlocked.set(areAnyBlocked.get() + 1);
                }
            }
        });
        if (world.isRemote && areAnyBlocked.get() > 0) {
            String posString = "(" + blockedCrop.get().getX() + ", " + blockedCrop.get().getY() + ", " + blockedCrop.get().getZ() + ")";
            player.sendMessage(new TextComponentTranslation("gregtechfoodoption.blocked", posString, areAnyBlocked.get()));
        }
        return areAnyBlocked.get() > 0;
    }

    @Override
    public void addInformation(ItemStack itemStack, List<String> lines) {
        super.addInformation(itemStack, lines);
        for (int i = 0; i < 4; i++) {
            lines.add(I18n.format("gregtechfoodoption.berry.description." + i));
        }
    }
}
