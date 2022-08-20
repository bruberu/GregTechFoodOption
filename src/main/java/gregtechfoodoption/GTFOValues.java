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
            MODID_AC = "agricraft";

    public static final String craftingToolRollingPin = "craftingToolRollingPin";

    public static final MaterialIconSet Organic = new MaterialIconSet("organic");

    public static final int UPDATE_LASER_POS = 97800;

    public static final BaseCreativeTab TAB_GTFO = new BaseCreativeTab("gregtechfoodoption.main", () -> GTFOMetaItem.BANANA.getStackForm(), true);

    public static final Random rand = new Random();
}
