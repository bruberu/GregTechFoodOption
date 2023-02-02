package gregtechfoodoption;

import gregtech.api.GregTechAPI;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.util.GregFakePlayer;
import gregtechfoodoption.entity.EntityStrongSnowman;
import gregtechfoodoption.integration.GTFOGAMaterialHandler;
import gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import gregtechfoodoption.item.food.GTFOFoodDurationSetter;
import gregtechfoodoption.network.PacketAppleCoreFoodDivisorUpdate;
import gregtechfoodoption.potion.CreativityPotion;
import gregtechfoodoption.potion.SnowGolemSpawnerPotion;
import gregtechfoodoption.potion.StepAssistPotion;
import gregtechfoodoption.utils.GTFODamageSources;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Mod.EventBusSubscriber(modid = GregTechFoodOption.MODID)
public class GTFOEventHandler {
    protected static Random rand = new Random();

    private static final HashMap<EntityLivingBase, Integer> snowGolemSpawnSpeeds = new HashMap<>();

    private static final Set<EntityLivingBase> jumpBoostSet = new HashSet<>();


    @SubscribeEvent
    public static void onMaterialsInit(GregTechAPI.MaterialEvent event) { // Must be called during construction to be registered in time for MaterialEvents.
        GTFOMaterialHandler gtfoMaterials = new GTFOMaterialHandler();
        GTFOMaterialHandler.onMaterialsInit();
        if (Loader.isModLoaded(GTFOValues.MODID_GCYS)) {
            GTFOGAMaterialHandler gtfogaMaterials = new GTFOGAMaterialHandler();
            GTFOGAMaterialHandler.onMaterialsInit();
        }
        GTFOMaterialHandler.customFluidTextures();

    }


    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
/*
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
*/

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
            if (GTFOConfig.gtfoPotionConfig.stepAssist) {
                if (StepAssistPotion.instance != null && player.isPotionActive(StepAssistPotion.instance)) {
                    if (!persisted.getBoolean(StepAssistPotion.TAG_NAME)) {
                        persisted.setBoolean(StepAssistPotion.TAG_NAME, true);
                    }
                } else {
                    if (persisted.getBoolean(StepAssistPotion.TAG_NAME)) {
                        persisted.setBoolean(StepAssistPotion.TAG_NAME, false);

                        player.stepHeight = 0.6F;
                    }
                }
            }
            if (GTFOConfig.gtfoPotionConfig.snowGolemSpawner) {
                if (SnowGolemSpawnerPotion.instance != null && player.isPotionActive(SnowGolemSpawnerPotion.instance)) {
                    if (!persisted.getBoolean(SnowGolemSpawnerPotion.TAG_NAME)) {
                        persisted.setBoolean(SnowGolemSpawnerPotion.TAG_NAME, true);
                    } else {
                        if (!player.world.isRemote && GTFOValues.rand.nextInt(100 / (player.getActivePotionEffect(SnowGolemSpawnerPotion.instance).getAmplifier() + 1)) == 0) {
                            float angle = (float) (GTFOValues.rand.nextFloat() * Math.PI);

                            RayTraceResult result = player.world.rayTraceBlocks(player.getPositionVector(), new Vec3d(1, -0.3, 0).rotateYaw(angle), false, false, true);
                            EntitySnowman spawn = new EntityStrongSnowman(player.world);
                            spawn.setLocationAndAngles(result.getBlockPos().getX(), result.getBlockPos().getY() + 1, result.getBlockPos().getZ(), player.rotationYawHead, player.rotationPitch);
                            player.world.spawnEntity(spawn);
                            spawn.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 1000, 4));
                        }
                    }
                } else {
                    if (persisted.getBoolean(SnowGolemSpawnerPotion.TAG_NAME)) {
                        persisted.setBoolean(SnowGolemSpawnerPotion.TAG_NAME, false);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingUpdate(LivingEntityUseItemEvent.Start event) {
        if (event.getItem().getItem() instanceof ItemFood || event.getItem().getItem() instanceof MetaItem<?>) {
            event.setDuration(GTFOFoodDurationSetter.duration(event.getItem()));
        }
    }

    // This particular function helps with generating food eating particles when the use count is over 25, because Minecraft is dumb.

    @SubscribeEvent
    public static void onLivingUpdate(LivingEntityUseItemEvent.Tick event) {
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
                            livingBase.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, Item.getIdFromItem(stack.getItem()), stack.getMetadata());
                        } else {
                            livingBase.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, Item.getIdFromItem(stack.getItem()));
                        }
                    }

                    livingBase.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.5F + 0.5F * (float) rand.nextInt(2), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onAdvancement(AdvancementEvent event) {
        if (GTFOConfig.gtfoAppleCoreConfig.reduceForeignFoodStats) {
            float divisorObtained = GTFOAppleCoreCompat.getDivisorOnAdvancement(event.getAdvancement());
            if (divisorObtained > 1 && GTFOAppleCoreCompat.advancementLookup(event.getEntityPlayer()) == divisorObtained) {
                GregTechAPI.networkHandler.sendToAll(new PacketAppleCoreFoodDivisorUpdate(
                        event.getEntityPlayer().getUniqueID(), divisorObtained));
                event.getEntityPlayer().sendMessage(new TextComponentTranslation("gregtechfoodoption.chat.food_buff"));
            }
        }
    }


    @SubscribeEvent
    public static void onPlayerLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.player.getEntityWorld().isRemote && Loader.isModLoaded(GTFOValues.MODID_AP)) {
            GregTechAPI.networkHandler.sendToAll(new PacketAppleCoreFoodDivisorUpdate(
                    event.player.getUniqueID(), GTFOAppleCoreCompat.advancementLookup(event.player)));
        }

    }

    @SubscribeEvent
    public static void onWorldLoadEvent(WorldEvent.Load event) {
        if (!event.getWorld().isRemote)
            GTFODamageSources.EXTERMINATOR = GregFakePlayer.get((WorldServer) event.getWorld());
    }
}