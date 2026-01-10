package gregtechfoodoption;

import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.items.toolitem.ToolHelper;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.BaseCreativeTab;
import gregtechfoodoption.item.GTFOMetaItem;

import java.util.Random;

import static gregtech.api.capability.GregtechDataCodes.assignId;

public class GTFOValues {
    public static final String MODID = "gregtechfoodoption",
            MODID_GCYS = "gcys",
            MODID_AA = "actuallyadditions",
            MODID_NC = "nuclearcraft",
            MODID_GF = "gregification",
            MODID_TFC = "tfc",
            MODID_AC = "agricraft",
            MODID_AP = "applecore",
            MODID_NU = "nutrition",
            MODID_NUGT = "nutrition_gtceu",
            MODID_SS = "sereneseasons",
            MODID_EIO = "enderio";

    public static final String craftingToolRollingPin = "craftingToolRollingPin";

    public static final MaterialIconSet Organic = new MaterialIconSet("organic");

    public static final int UPDATE_OPERATION_POS = assignId();
    public static final int UPDATE_SPRINKLER_DATA = assignId();
    public static final int UPDATE_SPRINKLER_EXISTENCE = assignId();
    public static final int UPDATE_FARMER_OUTPUT_FACING = assignId();
    public static final int UPDATE_KITCHEN_STATUS = assignId();
    public static final int UPDATE_KITCHEN_ORDER = assignId();

    public static final BaseCreativeTab TAB_GTFO = new BaseCreativeTab("gregtechfoodoption.main", () -> GTFOMetaItem.DOUGH.getStackForm(), true);
    public static final BaseCreativeTab TAB_GTFO_FOOD = new BaseCreativeTab("gregtechfoodoption.food", () -> GTFOMetaItem.BANANA.getStackForm(), true);
    public static final BaseCreativeTab TAB_GTFO_CROPS = new BaseCreativeTab("gregtechfoodoption.crops", () -> GTFOMetaItem.ONION_SEED.getStackForm(), true);
    public static final BaseCreativeTab TAB_GTFO_TOOLS = new BaseCreativeTab("gregtechfoodoption.tools", () -> ToolHelper.getAndSetToolData(GTFOMetaItem.ROLLING_PIN, Materials.Wood, 1, 1, 1F, 1F), true);
    public static final BaseCreativeTab TAB_GTFO_BLOCKS = new BaseCreativeTab("gregtechfoodoption.blocks", () -> GTFOMetaItem.ADOBE_BRICK.getStackForm(), true);
    public static final BaseCreativeTab TAB_GTFO_DRUGSALCOHOL = new BaseCreativeTab("gregtechfoodoption.funnystuff", () -> GTFOMetaItem.BEER.getStackForm(), true);
    public static final BaseCreativeTab TAB_GTFO_DRINKS = new BaseCreativeTab("gregtechfoodoption.drinks", () -> GTFOMetaItem.ORANGE_JUICE.getStackForm(), true);
    public static final BaseCreativeTab TAB_GTFO_FRUITSVEGETABLES = new BaseCreativeTab("gregtechfoodoption.fruitsvegetables", () -> GTFOMetaItem.MANGO.getStackForm(), true);

    public static final Random rand = new Random();
}
