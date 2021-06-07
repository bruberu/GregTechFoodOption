package com.bruberu.gregtechfoodoption.tools;

import gregtech.common.tools.ToolBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ToolRollingPin extends ToolBase {

    public float getBaseDamage(ItemStack stack) {
        return 0F;
    }

    @Override
    public float getAttackSpeed(ItemStack stack) {
        return 0.7f;
    }

    @Override
    public float getNormalDamageBonus(EntityLivingBase entity, ItemStack stack, EntityLivingBase attacker) {
        String name = entity.getClass().getName();
        name = name.substring(name.lastIndexOf('.') + 1);
        return name.toLowerCase().contains("slime") ? 10.0F : 0F;
    }

}
