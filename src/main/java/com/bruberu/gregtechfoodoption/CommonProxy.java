package com.bruberu.gregtechfoodoption;

import com.bruberu.gregtechfoodoption.GregTechFoodOption;
import com.bruberu.gregtechfoodoption.fluid.GTFOMetaFluids;
import com.bruberu.gregtechfoodoption.integration.GTFOAAMaterialHandler;
import com.bruberu.gregtechfoodoption.integration.GTFONCMaterialHandler;
import com.bruberu.gregtechfoodoption.item.GTFOMetaItems;
import com.bruberu.gregtechfoodoption.item.GTFOOredictItem;
import com.bruberu.gregtechfoodoption.potion.GTFOPotions;
import com.bruberu.gregtechfoodoption.recipe.GTFORecipeAddition;
import com.bruberu.gregtechfoodoption.recipe.GTFORecipeRemoval;
import com.bruberu.gregtechfoodoption.utils.GTFOLog;
import gregicadditions.GAMaterials;
import gregicadditions.Gregicality;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.blocks.VariantItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;


@Mod.EventBusSubscriber(modid = GregTechFoodOption.MODID)
public class CommonProxy {


    public void preLoad() {
        GTFOMaterialHandler gtfoMaterials = new GTFOMaterialHandler();
        GTFOPotions.initPotionInstances();
        GTFOMetaFluids.init();
        GTFOMetaItems.init();
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
        /*
        GTFOLog.logger.info("Registering blocks...");
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(GAMetaBlocks.MUTLIBLOCK_CASING);
         */
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        GTFOLog.logger.info("Registering Items...");
        IForgeRegistry<Item> registry = event.getRegistry();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        GTFOLog.logger.info("Registering recipe low...");
        GTFORecipeRemoval.init();
        GTFORecipeAddition.init();
        /*
        ConfigCircuitRecipeRemoval.init();
        GAMachineRecipeRemoval.init();
        GARecipeAddition.generatedRecipes();
        RecipeHandler.registerGreenHouseRecipes();
         */
    }



    @SubscribeEvent
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
