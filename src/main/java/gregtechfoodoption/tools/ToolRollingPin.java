/*
package gregtechfoodoption.tools;

import gregtech.common.tools.ToolBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.ItemStack;

import java.util.List;

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
        boolean isSlime = entity instanceof EntitySlime;
        if (isSlime) {
            ((EntitySlime) entity).squishAmount = 10; // Doesn't work, but I'd need ASM to fix it, so no
        }
        return isSlime ? 10.0F : 0F;
    }

    @Override
    public void addInformation(ItemStack stack, List<String> lines, boolean isAdvanced) {
        lines.add(I18n.format("metaitem.tool.tooltip.rolling_pin.slime"));
    }
}
*/
