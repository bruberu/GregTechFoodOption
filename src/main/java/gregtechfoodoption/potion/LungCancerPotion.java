package gregtechfoodoption.potion;

import gregtechfoodoption.utils.GTFODamageSources;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LungCancerPotion extends GTFOPotion {
    public static LungCancerPotion INSTANCE = null;

    public LungCancerPotion() {
        super("lungcancer", true, 0x663650, 8);
        INSTANCE = this;
    }

    @Override
    protected boolean canRender() {
        return true;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList(); // Nothing :)
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {
        int phase = entity.getActivePotionEffect(this).getDuration();

        if (phase % 600 == 0) {
            IAttributeInstance attr = entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
            attr.setBaseValue(attr.getBaseValue() - 1);
            if (attr.getBaseValue() <= 0) {
                entity.onDeath(GTFODamageSources.LUNG_CANCER);
            }
            entity.setHealth((float) attr.getBaseValue());
        }
    }

}
