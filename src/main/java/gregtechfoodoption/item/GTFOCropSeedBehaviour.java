package gregtechfoodoption.item;

import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOCrop;
import gregtechfoodoption.block.GTFORootCrop;
import gregtechfoodoption.integration.sereneseasons.GTFOSSTooltipHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLContainer;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.relauncher.Side;
import sereneseasons.core.SereneSeasons;

import java.util.List;

// bri'ish class
public class GTFOCropSeedBehaviour implements IItemBehaviour {
    protected final GTFOCrop crop;

    public GTFOCropSeedBehaviour(GTFOCrop cropBlock, ItemStack seed, ItemStack crop) {
        cropBlock.setSeed(seed);
        cropBlock.setCrop(crop);
        this.crop = cropBlock;
    }

    @Override
    public ActionResult<ItemStack> onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isAirBlock(pos.up()) && this.crop.getDefaultState().getBlock().canPlaceBlockAt(world, pos.up())) {
            world.setBlockState(pos.up(), this.crop.getDefaultState());
            ItemStack heldItem = player.getHeldItem(hand);
            heldItem.shrink(1);
            return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
        }
        return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
    }

    @Override
    public void addInformation(ItemStack itemStack, List<String> lines) {
        lines.add(I18n.format("gregtechfoodoption.seed.0"));
        if (crop instanceof GTFORootCrop)
            lines.add(I18n.format("gregtechfoodoption.seed.root_crop"));

        if (Loader.isModLoaded(GTFOValues.MODID_SS)) {
            GTFOSSTooltipHandler.addTooltip(crop, lines);
        }
    }
}
