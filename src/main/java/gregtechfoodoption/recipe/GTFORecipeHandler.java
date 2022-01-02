package gregtechfoodoption.recipe;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import gregtechfoodoption.item.GTFOMetaItem;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;

public class GTFORecipeHandler {

    public static void register() {
        OrePrefix.ingot.addProcessingHandler(PropertyKey.TOOL, GTFORecipeHandler::processIngot);
    }

    private static void processIngot(OrePrefix ingotPrefix, Material material, ToolProperty property) {
        if (property.getToolDurability() > 0) {
            ModHandler.addShapedRecipe(String.format("rolling_pin_%s", material.toString()),
                    GTFOMetaItem.ROLLING_PIN.getStackForm(material),
                    "  R", " I ", "R f",
                    'I', new UnificationEntry(ingotPrefix, material),
                    'R', new UnificationEntry(OrePrefix.stick, material));
        }

    }
}