package gregtechfoodoption.tools;

import gregtech.api.GTValues;
import gregtech.api.items.toolitem.ItemGTSword;
import gregtech.api.items.toolitem.ItemGTTool;
import gregtech.api.items.toolitem.ToolClasses;
import gregtech.api.items.toolitem.ToolHelper;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.ToolItems;
import gregtech.core.sound.GTSoundEvents;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.item.GTFOMetaItem;

public class GTFOToolItems {

    public static String ROLLING_PIN_CLASS = "rolling_pin";

    public static void init() {
        GTFOMetaItem.ROLLING_PIN = ToolItems.register(ItemGTTool.Builder.of(GTFOValues.MODID, "rolling_pin")
                .toolStats(b -> b.crafting())
                .oreDict("craftingToolRollingPin")
                .toolClasses(ROLLING_PIN_CLASS));
        GTFOMetaItem.BUTCHERY_KNIFE_HV = ToolItems.register(ItemGTSword.Builder.of(GTFOValues.MODID, "butchery_knife.hv")
                .toolStats(b -> b.crafting().attacking().brokenStack(ToolHelper.SUPPLY_POWER_UNIT_HV))
                .sound(GTSoundEvents.CUT)
                .toolClasses(ToolClasses.BUTCHERY_KNIFE)
                .electric(GTValues.HV));

    }

    public static void registerCustomRecipes() {
        Material[] rollingPinMaterials = new Material[]{
                Materials.Wood, Materials.Rubber, Materials.Polyethylene, Materials.Polytetrafluoroethylene
        };

        final UnificationEntry stick = new UnificationEntry(OrePrefix.stick, Materials.Iron);

        for (int i = 0; i < rollingPinMaterials.length; i++) {
            Material solidMaterial = rollingPinMaterials[i];
            if (ModHandler.isMaterialWood(solidMaterial))
                ModHandler.addMirroredShapedRecipe(String.format("soft_mallet_%s", solidMaterial),
                        ToolHelper.getAndSetToolData(GTFOMetaItem.ROLLING_PIN, solidMaterial, 128 * (1 << i), 1, 1F, 1F),
                        "  R", " P ", "R f",
                        'P', new UnificationEntry(OrePrefix.plank, solidMaterial),
                        'R', stick);
            else
                ModHandler.addMirroredShapedRecipe(String.format("soft_mallet_%s", solidMaterial),
                        ToolHelper.getAndSetToolData(GTFOMetaItem.ROLLING_PIN, solidMaterial, 48, 1, 1F, 1F),
                        "  R", " P ", "R f",
                        'P', new UnificationEntry(OrePrefix.plank, solidMaterial),
                        'R', stick);
        }
    }

}
