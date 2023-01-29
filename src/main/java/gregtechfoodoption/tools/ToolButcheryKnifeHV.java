/*
package gregtechfoodoption.tools;

import com.google.common.collect.Lists;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.enchants.EnchantmentData;
import gregtech.api.items.toolitem.behavior.IToolBehavior;
import gregtech.common.items.MetaItems;
import gregtech.common.tools.ToolButcheryKnife;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ToolButcheryKnifeHVBehavior implements IToolBehavior {

    private static final long use = GTValues.V[GTValues.HV * 4];

    @Override
    public float getBaseDamage(ItemStack stack) {
        return super.getBaseDamage(stack) + 5;
    }

    @Override
    public float getAttackSpeed(ItemStack stack) {
        if (stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null).canUse(use))
            return super.getAttackSpeed(stack) + 3;
        else
            return 0;
    }

    @Override
    public List<EnchantmentData> getEnchantments(ItemStack stack) {
        return Lists.newArrayList(new EnchantmentData(Enchantments.LOOTING, 5));
    }

    @Override
    public boolean canPerformSweepAttack(ItemStack stack) {
        return stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null).canUse(use);
    }

    public ItemStack getBrokenStack(ItemStack stack) {
        IElectricItem electricItem = stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        return MetaItems.POWER_UNIT_HV.getChargedStackWithOverride(electricItem);
    }

    @Override
    public float getMaxDurabilityMultiplier(ItemStack stack) {
        return 4;
    }

    @Override
    public boolean canMineBlock(IBlockState block, ItemStack stack) {
        return (block.getMaterial() == Material.LEAVES) || (block.getMaterial() == Material.WEB);
    }
}
*/
