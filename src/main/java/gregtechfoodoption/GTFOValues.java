package gregtechfoodoption;

import gregtech.api.unification.material.info.MaterialIconSet;
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
            MODID_SS = "sereneseasons";

    public static final String craftingToolRollingPin = "craftingToolRollingPin";

    public static final MaterialIconSet Organic = new MaterialIconSet("organic");

    public static final int UPDATE_OPERATION_POS = assignId();
    public static final int UPDATE_SPRINKLER_DATA = assignId();
    public static final int UPDATE_FARMER_OUTPUT_FACING = assignId();
    public static final int UPDATE_KITCHEN_STATUS = assignId();
    public static final int UPDATE_KITCHEN_ORDER = assignId();

    public static final BaseCreativeTab TAB_GTFO = new BaseCreativeTab("gregtechfoodoption.main", () -> GTFOMetaItem.DOUGH.getStackForm(), true);
    public static final BaseCreativeTab TAB_GTFO_FOOD = new BaseCreativeTab("gregtechfoodoption.food", () -> GTFOMetaItem.BANANA.getStackForm(), true);

    public static final Random rand = new Random();
}
