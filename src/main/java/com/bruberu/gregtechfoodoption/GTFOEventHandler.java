package com.bruberu.gregtechfoodoption;

import com.bruberu.gregtechfoodoption.potion.AddictionPotion;
import com.bruberu.gregtechfoodoption.potion.CreativityPotion;
import com.bruberu.gregtechfoodoption.potion.WithdrawalPotion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = GregTechFoodOption.MODID)
public class GTFOEventHandler {
    private static Set<EntityLivingBase> addictedSet = new HashSet<>();
    private static HashMap<EntityLivingBase, Integer> addictionAmplifiers = new HashMap<>();

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entityLivingBase = event.getEntityLiving();
        if(GTFOConfig.gtfoPotionConfig.addictionWithdrawal) {
            if(entityLivingBase.isDead && addictedSet.contains(entityLivingBase))
            {
                addictedSet.remove(entityLivingBase);
                addictionAmplifiers.remove(entityLivingBase);
            }
            if (AddictionPotion.instance != null && entityLivingBase.isPotionActive(AddictionPotion.instance) && !entityLivingBase.isDead) {
                addictedSet.add(entityLivingBase);
                int amplifier = entityLivingBase.getActivePotionEffect(AddictionPotion.instance).getAmplifier();
                if (addictionAmplifiers.containsKey(entityLivingBase) && addictionAmplifiers.get(entityLivingBase) < amplifier)
                    addictionAmplifiers.replace(entityLivingBase, addictionAmplifiers.get(entityLivingBase), amplifier);
                else
                    addictionAmplifiers.put(entityLivingBase, amplifier);
            }
            if (addictedSet.contains(entityLivingBase) && !event.getEntityLiving().isPotionActive(AddictionPotion.instance) && !entityLivingBase.world.isRemote) {
                entityLivingBase.addPotionEffect(new PotionEffect(WithdrawalPotion.instance, 200, addictionAmplifiers.get(entityLivingBase)));
                addictedSet.remove(entityLivingBase);
                addictionAmplifiers.remove(entityLivingBase);
            }
            if (WithdrawalPotion.instance != null && entityLivingBase.isPotionActive(WithdrawalPotion.instance)) {
                entityLivingBase.attackEntityFrom(new DamageSource("withdrawal"), ((float) (entityLivingBase.getActivePotionEffect(WithdrawalPotion.instance).getAmplifier() + 1) / 5));
            }
        }
        if ((event.getEntityLiving() instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            NBTTagCompound persisted = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
            if (!player.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
                player.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, persisted);
            }
            if (GTFOConfig.gtfoPotionConfig.creativity) {
                if (CreativityPotion.instance != null && player.isPotionActive(CreativityPotion.instance)) {
                    if (!persisted.getBoolean(CreativityPotion.TAG_NAME)) {
                        persisted.setBoolean(CreativityPotion.TAG_NAME, true);
                    }
                    player.capabilities.allowFlying = true;
                    //player.fallDistance = 0;
                } else {
                    if (persisted.getBoolean(CreativityPotion.TAG_NAME)) {
                        persisted.setBoolean(CreativityPotion.TAG_NAME, false);

                        player.fallDistance = 0;
                        if (!player.capabilities.isCreativeMode) {
                            player.capabilities.isFlying = false;
                            player.capabilities.allowFlying = false;
                        }
                    }
                }
            }

        }

    }
}