package gregtechfoodoption;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import dev.tianmi.sussypatches.common.SusConfig;
import gregtech.api.util.Mods;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.client.GTFOConnectedTextures;
import gregtechfoodoption.entity.GTFOEntities;
import gregtechfoodoption.integration.appleskin.GTFOMetaHUDOverlay;
import gregtechfoodoption.integration.appleskin.GTFOMetaTooltipOverlay;
import gregtechfoodoption.potion.AntiSchizoPotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    private static final ResourceLocation GTFO_CAPE_TEXTURE = new ResourceLocation(GregTechFoodOption.MODID, "textures/gtfocape.png");


    @Override
    public void preLoad() {
        super.preLoad();
        if (!Minecraft.getMinecraft().getFramebuffer().isStencilEnabled()) {
            Minecraft.getMinecraft().getFramebuffer().enableStencil();
        }
        GTFOEntities.registerRenders();
    }


    @Override
    public void onLoad() {
        super.onLoad();
        if (Loader.isModLoaded("appleskin")) {
            GTFOMetaTooltipOverlay.init();
            GTFOMetaHUDOverlay.init();
        }
        GTFOMetaBlocks.registerColors();
    }

    @Override
    public void onPostLoad() {
        super.onPostLoad();
        capeHoldersUUIDs.add(UUID.fromString("aaf70ec1-ac70-494f-9966-ea5933712750"));
        if (Loader.isModLoaded("sussypatches")
                && Mods.CTM.isModLoaded()
                && SusConfig.FEAT.multiCTM) {
            GTFOConnectedTextures.init();
        }
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        //GTFOMetaBlocks.registerStateMappers();
        GTFOMetaBlocks.registerItemModels();
    }

    private static final Set<UUID> capeHoldersUUIDs = new HashSet<>();

    @SubscribeEvent
    public static void onPlayerRender(RenderPlayerEvent.Pre event) {
        AbstractClientPlayer clientPlayer = (AbstractClientPlayer) event.getEntityPlayer();
        if (capeHoldersUUIDs.contains(clientPlayer.getUniqueID()) && clientPlayer.hasPlayerInfo() && clientPlayer.getLocationCape() == null) {
            NetworkPlayerInfo playerInfo = ObfuscationReflectionHelper.getPrivateValue(AbstractClientPlayer.class, clientPlayer, 0);
            Map<MinecraftProfileTexture.Type, ResourceLocation> playerTextures = ObfuscationReflectionHelper.getPrivateValue(NetworkPlayerInfo.class, playerInfo, 1);
            playerTextures.put(MinecraftProfileTexture.Type.CAPE, GTFO_CAPE_TEXTURE);
        }
    }

    @SubscribeEvent
    public static void removeOtherPlayerMessages(ClientChatReceivedEvent event) {
        if (Minecraft.getMinecraft().player.isPotionActive(AntiSchizoPotion.INSTANCE) && event.getType() == ChatType.CHAT) {
            if (event.getMessage() instanceof TextComponentTranslation chatMessage
                    && chatMessage.getFormatArgs()[0] instanceof TextComponentString nameWrapperString
                    && nameWrapperString.getSiblings().get(0) instanceof TextComponentString nameString) {
                if (nameString.getText().equals(Minecraft.getMinecraft().player.getName())) {
                    return; // Allow the player to see their own messages
                }
            }
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPotionApplied(PotionEvent.PotionAddedEvent event) {
        if (event.getPotionEffect().getPotion() == AntiSchizoPotion.INSTANCE && event.getEntity().isEntityEqual(Minecraft.getMinecraft().player)) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages(true);
        }
    }

}
