package com.bruberu.gregtechfoodoption;

import com.bruberu.gregtechfoodoption.block.GTFOMetaBlocks;
import com.bruberu.gregtechfoodoption.integration.appleskin.GTFOMetaTooltipOverlay;
import com.bruberu.gregtechfoodoption.item.GTFOOredictItem;
import com.bruberu.gregtechfoodoption.utils.GTFOLog;
import gregtech.api.unification.OreDictUnifier;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Optional;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {


    @Override
    public void preLoad() {
        super.preLoad();
        if (!Minecraft.getMinecraft().getFramebuffer().isStencilEnabled()) {
            Minecraft.getMinecraft().getFramebuffer().enableStencil();
        }
    }


    @Override
    public void onLoad() {
        if(Loader.isModLoaded("appleskin"))
            GTFOMetaTooltipOverlay.init();
    }


    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        //GTFOMetaBlocks.registerStateMappers();
        GTFOMetaBlocks.registerItemModels();
    }

    @SubscribeEvent
    public static void addFormulaHandler(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        Optional<String> oreDictName = OreDictUnifier.getOreDictionaryNames(itemStack).stream().findFirst();
        if (oreDictName.isPresent() && GTFOOredictItem.NAME_TO_OREDICTITEM.containsKey(oreDictName.get())) {
            GTFOOredictItem.OreDictItem material = GTFOOredictItem.NAME_TO_OREDICTITEM.get(oreDictName.get());
            if (material != null) {
                String formula = material.getFormula();
                if (formula != null && !formula.isEmpty()/* && event.getToolTip().size() == 0 */) {
                    event.getToolTip().add(1, TextFormatting.GRAY.toString() + material.getFormula());
                }
            }
        }
    }
}
