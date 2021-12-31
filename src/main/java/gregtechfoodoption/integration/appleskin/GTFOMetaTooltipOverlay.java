package gregtechfoodoption.integration.appleskin;

import gregtechfoodoption.GTFOConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import squeek.appleskin.AppleSkin;
import squeek.appleskin.ModInfo;
import squeek.appleskin.helpers.AppleCoreHelper;
import squeek.appleskin.helpers.BetterWithModsHelper;
import squeek.appleskin.helpers.KeyHelper;

//Also yoinked from @idcppl from Greg's HarvestCraft, since I technically have no idea how to code complex classes.

public class GTFOMetaTooltipOverlay {
    private static ResourceLocation modIcons = new ResourceLocation(ModInfo.MODID_LOWER, "textures/icons.png");
    public static final int TOOLTIP_REAL_HEIGHT_OFFSET_BOTTOM = 3;
    public static final int TOOLTIP_REAL_HEIGHT_OFFSET_TOP = -3;
    public static final int TOOLTIP_REAL_WIDTH_OFFSET_RIGHT = 3;

    public static void init() {
        if(Loader.isModLoaded("appleskin"))
            MinecraftForge.EVENT_BUS.register(new GTFOMetaTooltipOverlay());
    }

    @SubscribeEvent
    public void onRenderTooltip(RenderTooltipEvent.PostText event) {
        ItemStack hoveredStack = event.getStack();
        if (hoveredStack.isEmpty())
            return;

        boolean shouldShowTooltip = (GTFOConfig.showTooltipsOnShift && KeyHelper.isShiftKeyDown()) || GTFOConfig.showTooltipsAlways;
        if (!shouldShowTooltip)
            return;

        Minecraft mc = Minecraft.getMinecraft();
        GuiScreen gui = mc.currentScreen;

        if (gui == null)
            return;

        if (!GTFOMetaFoodHelper.isFood(hoveredStack))
            return;

        EntityPlayer player = mc.player;
        ScaledResolution scale = new ScaledResolution(mc);
        int toolTipY = event.getY();
        int toolTipX = event.getX();
        int toolTipW = event.getWidth();
        int toolTipH = event.getHeight();

        GTFOMetaFoodHelper.BasicFoodValues defaultFoodValues = GTFOMetaFoodHelper.getDefaultFoodValues(hoveredStack);
        GTFOMetaFoodHelper.BasicFoodValues modifiedFoodValues = GTFOMetaFoodHelper.getModifiedFoodValues(hoveredStack, player);

        // Apply scale for altered max hunger if necessary
        if (AppleSkin.hasAppleCore) {
            defaultFoodValues = AppleCoreHelper.getFoodValuesForDisplay(defaultFoodValues, player);
            modifiedFoodValues = AppleCoreHelper.getFoodValuesForDisplay(modifiedFoodValues, player);
        }

        // Apply BWM tweaks if necessary
        defaultFoodValues = BetterWithModsHelper.getFoodValuesForDisplay(defaultFoodValues);
        modifiedFoodValues = BetterWithModsHelper.getFoodValuesForDisplay(modifiedFoodValues);

        if (defaultFoodValues.equals(modifiedFoodValues) && defaultFoodValues.hunger == 0)
            return;

        int biggestHunger = Math.max(defaultFoodValues.hunger, modifiedFoodValues.hunger);
        float biggestSaturationIncrement = Math.max(defaultFoodValues.getSaturationIncrement(), modifiedFoodValues.getSaturationIncrement());

        int barsNeeded = (int) Math.ceil(Math.abs(biggestHunger) / 2f);
        boolean hungerOverflow = barsNeeded > 10;
        String hungerText = hungerOverflow ? ((biggestHunger < 0 ? -1 : 1) * barsNeeded) + "x " : null;
        if (hungerOverflow)
            barsNeeded = 1;

        int saturationBarsNeeded = (int) Math.max(1, Math.ceil(Math.abs(biggestSaturationIncrement) / 2f));
        boolean saturationOverflow = saturationBarsNeeded > 10;
        String saturationText = saturationOverflow ? ((biggestSaturationIncrement < 0 ? -1 : 1) * saturationBarsNeeded) + "x " : null;
        if (saturationOverflow)
            saturationBarsNeeded = 1;

        int toolTipBottomY = toolTipY + toolTipH + 1 + TOOLTIP_REAL_HEIGHT_OFFSET_BOTTOM;
        int toolTipRightX = toolTipX + toolTipW + 1 + TOOLTIP_REAL_WIDTH_OFFSET_RIGHT;

        boolean shouldDrawBelow = toolTipBottomY + 20 < scale.getScaledHeight() - 3;

        int rightX = toolTipRightX - 3;
        int leftX = rightX - (Math.max(barsNeeded * 9 + (int) (mc.fontRenderer.getStringWidth(hungerText) * 0.75f), saturationBarsNeeded * 6 + (int) (mc.fontRenderer.getStringWidth(saturationText) * 0.75f))) - 3;
        int topY = (shouldDrawBelow ? toolTipBottomY : toolTipY - 20 + TOOLTIP_REAL_HEIGHT_OFFSET_TOP);
        int bottomY = topY + 19;

        GlStateManager.disableLighting();
        GlStateManager.disableDepth();

        // bg
        Gui.drawRect(leftX - 1, topY, rightX + 1, bottomY, 0xF0100010);
        Gui.drawRect(leftX, (shouldDrawBelow ? bottomY : topY - 1), rightX, (shouldDrawBelow ? bottomY + 1 : topY), 0xF0100010);
        Gui.drawRect(leftX, topY, rightX, bottomY, 0x66FFFFFF);

        // drawRect disables blending and modifies color, so reset them
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        int x = rightX - 2;
        int startX = x;
        int y = bottomY - 18;

        mc.getTextureManager().bindTexture(Gui.ICONS);

        for (int i = 0; i < barsNeeded * 2; i += 2) {
            x -= 9;

            if (modifiedFoodValues.hunger < 0)
                gui.drawTexturedModalRect(x, y, 34, 27, 9, 9);
            else if (modifiedFoodValues.hunger > defaultFoodValues.hunger && defaultFoodValues.hunger <= i)
                gui.drawTexturedModalRect(x, y, 133, 27, 9, 9);
            else if (modifiedFoodValues.hunger > i + 1 || defaultFoodValues.hunger == modifiedFoodValues.hunger)
                gui.drawTexturedModalRect(x, y, 16, 27, 9, 9);
            else if (modifiedFoodValues.hunger == i + 1)
                gui.drawTexturedModalRect(x, y, 124, 27, 9, 9);
            else
                gui.drawTexturedModalRect(x, y, 34, 27, 9, 9);

            GlStateManager.color(1.0F, 1.0F, 1.0F, .25F);
            gui.drawTexturedModalRect(x, y, defaultFoodValues.hunger - 1 == i ? 115 : 106, 27, 9, 9);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            if (modifiedFoodValues.hunger > i)
                gui.drawTexturedModalRect(x, y, modifiedFoodValues.hunger - 1 == i ? 61 : 52, 27, 9, 9);
        }
        if (hungerText != null) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.75F, 0.75F, 0.75F);
            mc.fontRenderer.drawStringWithShadow(hungerText, x * 4 / 3 - mc.fontRenderer.getStringWidth(hungerText) + 2, y * 4 / 3 + 2, 0xFFDDDDDD);
            GlStateManager.popMatrix();
        }

        y += 10;
        x = startX;
        float modifiedSaturationIncrement = modifiedFoodValues.getSaturationIncrement();
        float absModifiedSaturationIncrement = Math.abs(modifiedSaturationIncrement);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(modIcons);
        for (int i = 0; i < saturationBarsNeeded * 2; i += 2) {
            float effectiveSaturationOfBar = (absModifiedSaturationIncrement - i) / 2f;

            x -= 6;

            boolean shouldBeFaded = absModifiedSaturationIncrement <= i;
            if (shouldBeFaded)
                GlStateManager.color(1.0F, 1.0F, 1.0F, .5F);

            gui.drawTexturedModalRect(x, y, effectiveSaturationOfBar >= 1 ? 21 : effectiveSaturationOfBar > 0.5 ? 14 : effectiveSaturationOfBar > 0.25 ? 7 : effectiveSaturationOfBar > 0 ? 0 : 28, modifiedSaturationIncrement >= 0 ? 27 : 34, 7, 7);

            if (shouldBeFaded)
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }
        if (saturationText != null) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.75F, 0.75F, 0.75F);
            mc.fontRenderer.drawStringWithShadow(saturationText, x * 4 / 3 - mc.fontRenderer.getStringWidth(saturationText) + 2, y * 4 / 3 + 1, 0xFFDDDDDD);
            GlStateManager.popMatrix();
        }

        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        // reset to drawHoveringText state
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
    }
}

