package bruberu.gregtechfoodoption.recipe;

import bruberu.gregtechfoodoption.item.GTFOMetaItem;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;

public class GTFORecipeHandler {

    public static void register() {
        OrePrefix.ingot.addProcessingHandler(IngotMaterial.class, GTFORecipeHandler::processIngot);

    }

    private static void processIngot(OrePrefix ingotPrefix, IngotMaterial material) {
        if (material.toolDurability > 0) {
            ModHandler.addShapedRecipe(String.format("rolling_pin_%s", material.toString()),
                    GTFOMetaItem.ROLLING_PIN.getStackForm(material),
                    "  R", " I ", "R f",
                    'I', new UnificationEntry(ingotPrefix, material),
                    'R', new UnificationEntry(OrePrefix.stick, material));
        }

    }
}