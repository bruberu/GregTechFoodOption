package gregtechfoodoption.integration.appleskin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import squeek.appleskin.AppleSkin;
import squeek.appleskin.ModConfig;
import squeek.appleskin.helpers.AppleCoreHelper;
import squeek.appleskin.helpers.FoodHelper;

import static squeek.appleskin.client.HUDOverlayHandler.drawHungerOverlay;
import static squeek.appleskin.client.HUDOverlayHandler.drawSaturationOverlay;


public class GTFOMetaHUDOverlay {
    private float flashAlpha = 0f;
    private byte alphaDir = 1;


    protected int foodIconsOffset;

    public static void init() {
        if(Loader.isModLoaded("appleskin"))
            MinecraftForge.EVENT_BUS.register(new GTFOMetaHUDOverlay());
    }
    @SubscribeEvent(priority= EventPriority.LOW)
    public void onPreRender(RenderGameOverlayEvent.Pre event)
    {
        if (event.getType() != RenderGameOverlayEvent.ElementType.FOOD)
            return;

        foodIconsOffset = GuiIngameForge.right_height;
    }
    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event) {
        if (Loader.isModLoaded("appleskin")) {
            if (event.getType() != RenderGameOverlayEvent.ElementType.FOOD)
                return;

            if (!ModConfig.SHOW_FOOD_VALUES_OVERLAY && !ModConfig.SHOW_SATURATION_OVERLAY)
                return;

            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayer player = mc.player;
            ItemStack heldItem = player.getHeldItemMainhand();
            if(!GTFOMetaFoodHelper.isFood(heldItem)) {
                flashAlpha = 0;
                alphaDir = 1;
                return;
            }
            FoodStats stats = player.getFoodStats();

            ScaledResolution scale = event.getResolution();

            int left = scale.getScaledWidth() / 2 + 91;
            int top = scale.getScaledHeight() - foodIconsOffset;

            // saturation overlay
            if (ModConfig.SHOW_SATURATION_OVERLAY)
                drawSaturationOverlay(0, stats.getSaturationLevel(), mc, left, top, 1f);

            // restored hunger/saturation overlay while holding food
            FoodHelper.BasicFoodValues foodValues = GTFOMetaFoodHelper.getModifiedFoodValues(heldItem, player);
            // Apply scale for altered max hunger if necessary
            if (AppleSkin.hasAppleCore)
                foodValues = AppleCoreHelper.getFoodValuesForDisplay(foodValues, player);
            drawHungerOverlay(foodValues.hunger, stats.getFoodLevel(), mc, left, top, flashAlpha);

            if (ModConfig.SHOW_SATURATION_OVERLAY)
            {
                int newFoodValue = stats.getFoodLevel() + foodValues.hunger;
                float newSaturationValue = stats.getSaturationLevel() + foodValues.getSaturationIncrement();
                drawSaturationOverlay(newSaturationValue > newFoodValue ? newFoodValue - stats.getSaturationLevel() : foodValues.getSaturationIncrement(), stats.getSaturationLevel(), mc, left, top, flashAlpha);
            }
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        if (event.phase != TickEvent.Phase.END)
            return;

        flashAlpha += alphaDir * 0.25f;
        if (flashAlpha >= 1.5f)
        {
            flashAlpha = 1f;
            alphaDir = -1;
        }
        else if (flashAlpha <= -0.5f)
        {
            flashAlpha = 0f;
            alphaDir = 1;
        }
    }
}
