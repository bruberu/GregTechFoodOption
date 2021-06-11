package com.bruberu.gregtechfoodoption;

import com.bruberu.gregtechfoodoption.block.GTFOMetaBlocks;
import com.bruberu.gregtechfoodoption.fluid.GTFOMetaFluids;
import com.bruberu.gregtechfoodoption.integration.GTFOAAMaterialHandler;
import com.bruberu.gregtechfoodoption.integration.GTFONCMaterialHandler;
import com.bruberu.gregtechfoodoption.item.GTFOMetaItem;
import com.bruberu.gregtechfoodoption.item.GTFOMetaItems;
import com.bruberu.gregtechfoodoption.machines.GTFOTileEntities;
import com.bruberu.gregtechfoodoption.potion.GTFOPotions;
import com.bruberu.gregtechfoodoption.recipe.GTFORecipeAddition;
import com.bruberu.gregtechfoodoption.recipe.GTFORecipeHandler;
import com.bruberu.gregtechfoodoption.recipe.GTFORecipeRemoval;
import com.bruberu.gregtechfoodoption.recipe.chain.VanillaOverrideChain;
import com.bruberu.gregtechfoodoption.utils.GTFOLog;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.blocks.VariantItemBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;


@Mod.EventBusSubscriber(modid = GregTechFoodOption.MODID)
public class CommonProxy {


    public void preLoad() {

        GTFOMaterialHandler gtfoMaterials = new GTFOMaterialHandler();
        GTFOPotions.initPotionInstances();
        GTFOMetaFluids.init();
        GTFOMetaItems.init();

        GTFORecipeHandler.register();

        if(GTFOConfig.gtfoncConfig.nuclearCompat)
        {
            GTFONCMaterialHandler gtfoncMaterials = new GTFONCMaterialHandler();
        }
        if(GTFOConfig.gtfoaaConfig.actuallyCompat)
        {
            GTFOAAMaterialHandler gtfoaaMaterials = new GTFOAAMaterialHandler();
        }
    }

    public void onLoad() throws IOException {

    }

    @SubscribeEvent
    public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(GregTechFoodOption.MODID)) {
            ConfigManager.sync(GregTechFoodOption.MODID, Config.Type.INSTANCE);
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        GTFOLog.logger.info("Registering blocks...");
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(GTFOMetaBlocks.GTFO_CASING);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        GTFOLog.logger.info("Registering Items...");
        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(createItemBlock(GTFOMetaBlocks.GTFO_CASING, VariantItemBlock::new));

    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        GTFOLog.logger.info("Registering recipe normal...");
        GTFORecipeAddition.init();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerLowRecipes(RegistryEvent.Register<IRecipe> event) {
        GTFOLog.logger.info("Registering recipe low...");
        GTFORecipeAddition.lowInit();
    }


    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerOrePrefix(RegistryEvent.Register<IRecipe> event) {
        GTFOLog.logger.info("Registering ore prefix...");
        GTFOMetaItems.registerOreDict();
        OrePrefix.runMaterialHandlers();
    }

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        itemBlock.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
        return itemBlock;
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        GTFOLog.logger.info("Registering recipe very low...");
        GTFORecipeRemoval.init();
        GTFORecipeAddition.compatInit();
    }

    // These recipes are generated at the beginning of the init() phase with the proper config set.
    // This is not great practice, but ensures that they are run AFTER CraftTweaker,
    // meaning they will follow the recipes in the map with CraftTweaker changes,
    // being significantly easier for modpack authors.


    /*
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.player.world.isRemote) {
        }
    }

    @SubscribeEvent
    public static void onSave(WorldEvent.Save event) {

    }

    @SubscribeEvent
    public static void onUnload(WorldEvent.Unload event) {

    }
    */
}
