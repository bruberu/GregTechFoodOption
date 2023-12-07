package gregtechfoodoption.item;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.items.gui.ItemUIFactory;
import gregtech.api.items.gui.PlayerInventoryHolder;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtechfoodoption.gui.widgets.KitchenRecipeWidget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class GTFOKitchenRecipeBehaviour implements ItemUIFactory, IItemBehaviour {
    @Override
    public ModularUI createUI(PlayerInventoryHolder playerInventoryHolder, EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 260, 140);
        ItemStack stack = playerInventoryHolder.getCurrentItem();
        builder.widget(new KitchenRecipeWidget(10, 10, 220, 243,
                getRecipeCount(stack),
                (tag) -> addRecipe(stack, tag),
                (index) -> getRecipe(stack, index)));
        builder.bindPlayerInventory(entityPlayer.inventory);
        return builder.build(playerInventoryHolder, entityPlayer);
    }

    private static void addRecipe(ItemStack stack, NBTTagCompound recipe) {
        if (!GTFOMetaItem.KITCHEN_RECIPE.isItemEqual(stack))
            return;
        NBTTagCompound tag = stack.getTagCompound();
        if (stack.getTagCompound() == null)
            tag = new NBTTagCompound();
        int count = tag.getInteger("recipecount");
        tag.setInteger("recipecount", count + 1);

        tag.setTag("recipe" + count, recipe);
        stack.setTagCompound(tag);
    }

    private static NBTTagCompound getRecipe(ItemStack stack, int index) {
        if (!GTFOMetaItem.KITCHEN_RECIPE.isItemEqual(stack))
            return null;

        if (stack.getTagCompound() == null)
            return null;
        return stack.getTagCompound().getCompoundTag("recipe" + index);
    }

    private static int getRecipeCount(ItemStack stack) {
        if (!GTFOMetaItem.KITCHEN_RECIPE.isItemEqual(stack))
            return 0;
        if (stack.getTagCompound() == null)
            return 0;
        return stack.getTagCompound().getInteger("recipecount");
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack heldItem = player.getHeldItem(hand);
        if (!world.isRemote) {
            PlayerInventoryHolder holder = new PlayerInventoryHolder(player, hand);
            holder.openUI();
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, heldItem);
    }
}
