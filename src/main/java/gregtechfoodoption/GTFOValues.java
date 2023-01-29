package gregtechfoodoption;

import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.util.BaseCreativeTab;
import gregtechfoodoption.item.GTFOMetaItem;

import java.util.Random;

public class GTFOValues {
    public static final String MODID = "gregtechfoodoption",
            MODID_GCYS = "gcys",
            MODID_AA = "actuallyadditions",
            MODID_NC = "nuclearcraft",
            MODID_GF = "gregification",
            MODID_TFC = "tfc",
            MODID_AC = "agricraft",
            MODID_AP = "applecore";

    public static final String craftingToolRollingPin = "craftingToolRollingPin";

    public static final MaterialIconSet Organic = new MaterialIconSet("organic");

    public static final int UPDATE_OPERATION_POS = 97800;
    public static final int UPDATE_SPRINKLER_DATA = 97801;
    public static final int UPDATE_FARMER_OUTPUT_FACING = 97802;

    public static final BaseCreativeTab TAB_GTFO = new BaseCreativeTab("gregtechfoodoption.main", () -> GTFOMetaItem.DOUGH.getStackForm(), true);
    public static final BaseCreativeTab TAB_GTFO_FOOD = new BaseCreativeTab("gregtechfoodoption.food", () -> GTFOMetaItem.BANANA.getStackForm(), true);

    public static final Random rand = new Random();
}
