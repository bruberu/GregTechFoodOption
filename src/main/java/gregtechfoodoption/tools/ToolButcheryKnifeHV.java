package gregtechfoodoption.tools;

import com.google.common.collect.Lists;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.enchants.EnchantmentData;
import gregtech.common.items.MetaItems;
import gregtech.common.tools.ToolButcheryKnife;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ToolButcheryKnifeHV extends ToolButcheryKnife {

    @Override
    public float getBaseDamage(ItemStack stack) {
        return super.getBaseDamage(stack) + 5;
    }

    @Override
    public float getAttackSpeed(ItemStack stack) {
        if (stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null).canUse(GTValues.V[GTValues.HV]))
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
        return stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null).canUse(GTValues.V[GTValues.HV]);
    }

    public ItemStack getBrokenStack(ItemStack stack) {
        IElectricItem electricItem = stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        return MetaItems.POWER_UNIT_HV.getChargedStackWithOverride(electricItem);
    }

    @Override
    public float getMaxDurabilityMultiplier(ItemStack stack) {
        return 4;
    }
}
