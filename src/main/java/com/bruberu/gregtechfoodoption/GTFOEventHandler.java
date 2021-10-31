package com.bruberu.gregtechfoodoption;

import com.bruberu.gregtechfoodoption.item.GTFOFoodDurationSetter;
import com.bruberu.gregtechfoodoption.potion.AddictionPotion;
import com.bruberu.gregtechfoodoption.potion.CreativityPotion;
import com.bruberu.gregtechfoodoption.potion.WithdrawalPotion;
import gregtech.api.items.metaitem.MetaItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Mod.EventBusSubscriber(modid = GregTechFoodOption.MODID)
public class GTFOEventHandler {
    protected Random rand = new Random();

    private static Set<EntityLivingBase> addictedSet = new HashSet<>();
    private static HashMap<EntityLivingBase, Integer> addictionAmplifiers = new HashMap<>();

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entityLivingBase = event.getEntityLiving();
        if (GTFOConfig.gtfoPotionConfig.addictionWithdrawal) {
            if (entityLivingBase.isDead && addictedSet.contains(entityLivingBase)) {
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

    @SubscribeEvent
    public void onLivingUpdate(LivingEntityUseItemEvent.Start event) {
        if (event.getItem().getItem() instanceof ItemFood) {
            event.setDuration(GTFOFoodDurationSetter.duration(event.getItem()));
        } else if (event.getItem().getItem() instanceof MetaItem<?>) {
            MetaItem.MetaValueItem metaItem = ((MetaItem<?>) event.getItem().getItem()).getItem(event.getItem());
            event.setDuration(GTFOFoodDurationSetter.duration(event.getItem()));
        }
    }

    // This particular function helps with generating food eating particles when the use count is over 25, because Minecraft is dumb.

    @SubscribeEvent
    public void onLivingUpdate(LivingEntityUseItemEvent.Tick event) {
        if (event.getItem().getItem() instanceof ItemFood || event.getItem().getItem() instanceof MetaItem<?>) {
            EntityLivingBase livingBase = event.getEntityLiving();
            ItemStack stack = event.getItem();
            if (livingBase.getItemInUseCount() <= GTFOFoodDurationSetter.duration(stack) - 7 && livingBase.getItemInUseCount() % 4 == 0) {
                if (stack.getItemUseAction() == EnumAction.EAT) {
                    for (int i = 0; i < 5; ++i) {
                        Vec3d vec3d = new Vec3d(((double) rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
                        vec3d = vec3d.rotatePitch(-livingBase.rotationPitch * 0.017453292F);
                        vec3d = vec3d.rotateYaw(-livingBase.rotationYaw * 0.017453292F);
                        double d0 = (double) (rand.nextFloat()) * 0.6D - 0.3D;
                        Vec3d vec3d1 = new Vec3d(((double) rand.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
                        vec3d1 = vec3d1.rotatePitch(-livingBase.rotationPitch * 0.017453292F);
                        vec3d1 = vec3d1.rotateYaw(-livingBase.rotationYaw * 0.017453292F);
                        vec3d1 = vec3d1.add(livingBase.posX, livingBase.posY + (double) livingBase.getEyeHeight(), livingBase.posZ);
                        if (stack.getHasSubtypes()) {
                            livingBase.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, new int[]{Item.getIdFromItem(stack.getItem()), stack.getMetadata()});
                        } else {
                            livingBase.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, new int[]{Item.getIdFromItem(stack.getItem())});
                        }
                    }

                    livingBase.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.5F + 0.5F * (float) this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                }
            }
        }
    }
}