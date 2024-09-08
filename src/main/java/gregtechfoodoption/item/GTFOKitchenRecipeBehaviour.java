package gregtechfoodoption.item;

import com.cleanroommc.modularui.factory.HandGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.items.gui.ItemUIFactory;
import gregtech.api.items.gui.PlayerInventoryHolder;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtechfoodoption.gui.widgets.KitchenRecipeWidget;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

public class GTFOKitchenRecipeBehaviour implements ItemUIFactory, IItemBehaviour {
    @Override
    public ModularUI createUI(PlayerInventoryHolder playerInventoryHolder, EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 220, 240);
        ItemStack stack = playerInventoryHolder.getCurrentItem();
        builder.widget(new KitchenRecipeWidget(10, 10, 180, 243,
                getRecipeCount(stack),
                (tag) -> addRecipe(stack, tag),
                (index) -> getRecipe(stack, index),
                (finalResult) -> setFinalResult(stack, finalResult),
                (index) -> deleteRecipe(stack, index),
                getFinalResult(stack)));
        builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 30, 155);
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

    private static void deleteRecipe(ItemStack stack, int location) {
        if (!GTFOMetaItem.KITCHEN_RECIPE.isItemEqual(stack))
            return;
        NBTTagCompound tag = stack.getTagCompound();
        if (stack.getTagCompound() == null)
            return;
        int count = tag.getInteger("recipecount");
        if (location >= count)
            return;
        tag.removeTag("recipe" + location);
        for (int i = location; i < count - 1; i++) {
            tag.setTag("recipe" + i, tag.getCompoundTag("recipe" + (i + 1)));
        }
        tag.setInteger("recipecount", count - 1);

        stack.setTagCompound(tag);
    }

    private static int getRecipeCount(ItemStack stack) {
        if (!GTFOMetaItem.KITCHEN_RECIPE.isItemEqual(stack))
            return 0;
        if (stack.getTagCompound() == null)
            return 0;
        return stack.getTagCompound().getInteger("recipecount");
    }

    private static void setFinalResult(ItemStack stack, ItemStack result) {
        if (!GTFOMetaItem.KITCHEN_RECIPE.isItemEqual(stack))
            return;
        NBTTagCompound tag = stack.getTagCompound();
        if (stack.getTagCompound() == null)
            tag = new NBTTagCompound();
        tag.setTag("finalresult", result.serializeNBT());
        stack.setTagCompound(tag);
    }

    private static ItemStack getFinalResult(ItemStack stack) {
        if (!GTFOMetaItem.KITCHEN_RECIPE.isItemEqual(stack))
            return null;
        if (stack.getTagCompound() == null)
            return null;
        return new ItemStack(stack.getTagCompound().getCompoundTag("finalresult"));
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

    @Override
    public void addInformation(ItemStack itemStack, List<String> lines) {
        ItemStack finalItem = getFinalResult(itemStack);
        if (finalItem != null && !finalItem.isEmpty()) {
            lines.add(I18n.format("gregtechfoodoption.kitchen_recipe.final_result", finalItem.getDisplayName()));
        }
    }

    @Override
    public ModularPanel buildUI(HandGuiData data, PanelSyncManager syncManager) {
        return null;
    }
}
