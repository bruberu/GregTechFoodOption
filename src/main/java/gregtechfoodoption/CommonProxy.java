package gregtechfoodoption;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtechfoodoption.integration.GTFOAAMaterialHandler;
import gregtechfoodoption.integration.GTFOGAMaterialHandler;
import gregtechfoodoption.integration.GTFONCMaterialHandler;
import gregtechfoodoption.utils.GTFOLog;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.fluid.GTFOMetaFluids;
import gregtechfoodoption.item.GTFOMetaItems;
import gregtechfoodoption.potion.GTFOPotions;
import gregtechfoodoption.recipe.GTFOOreDictRegistration;
import gregtechfoodoption.recipe.GTFORecipeAddition;
import gregtechfoodoption.recipe.GTFORecipeHandler;
import gregtechfoodoption.recipe.GTFORecipeRemoval;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.blocks.VariantItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.function.Function;


@Mod.EventBusSubscriber(modid = GregTechFoodOption.MODID)
public class CommonProxy {

    public void preLoad() {
        GTFOPotions.initPotionInstances();
        GTFOMetaFluids.init();
        GTFOMetaItems.init();

        GTFORecipeHandler.register();
        try {
            addSlotsToMaps(RecipeMaps.FERMENTING_RECIPES, "maxInputs", 1);
            addSlotsToMaps(RecipeMaps.FERMENTING_RECIPES, "maxOutputs", 1);
        } catch (Exception e) {

        }
    }

    public void onLoad() {
    }

    public void onPostLoad() {

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
        registry.register(GTFOMetaBlocks.GTFO_METAL_CASING);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        GTFOLog.logger.info("Registering Items...");
        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(createItemBlock(GTFOMetaBlocks.GTFO_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTFOMetaBlocks.GTFO_METAL_CASING, VariantItemBlock::new));

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
        GTFOOreDictRegistration.init();
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

    public static void addSlotsToMaps(
            final RecipeMap<?> map,
            final String slotType,
            final int value)
            throws Exception {

        // set public
        Field field = RecipeMap.class.getDeclaredField(slotType);
        field.setAccessible(true);

        // set non-final
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        // set the value of the parameter
        field.setInt(map, value);
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
