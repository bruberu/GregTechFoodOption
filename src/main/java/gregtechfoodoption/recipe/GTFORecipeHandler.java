package gregtechfoodoption.recipe;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.item.GTFOMetaItem;

public class GTFORecipeHandler {

    public static void register() {
        OrePrefix.ingot.addProcessingHandler(PropertyKey.TOOL, GTFORecipeHandler::processIngot);
        OrePrefix.toolHeadSword.addProcessingHandler(PropertyKey.TOOL, GTFORecipeHandler::processSword);
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

    private static void processSword(OrePrefix prefix, Material material, ToolProperty property) {
        if (property.getToolDurability() > 0) {
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(480).duration(2500)
                    .input(prefix, material)
                    .input(OrePrefix.stick, material, 2)
                    .input(OrePrefix.plate, material)
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Extreme, 2)
                    .inputs(MetaItems.POWER_UNIT_HV.getStackForm())
                    .input(OrePrefix.wireGtDouble, Materials.MercuryBariumCalciumCuprate, 8)
                    .fluidInputs(Materials.SolderingAlloy.getFluid(144 * 20))
                    .outputs(GTFOMetaItem.BUTCHERY_KNIFE_HV.getStackForm(material))
                    .buildAndRegister();
        }

    }

}