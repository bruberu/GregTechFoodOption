package gregtechfoodoption.recipe;

import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtechfoodoption.item.GTFOMetaItem;
import net.minecraft.item.ItemStack;

import static gregtech.common.items.MetaItems.POWER_UNIT_HV;

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
            ItemStack[] power_units = {POWER_UNIT_HV.getMaxChargeOverrideStack(1800000L), POWER_UNIT_HV.getMaxChargeOverrideStack(1600000L), POWER_UNIT_HV.getMaxChargeOverrideStack(1200000L), POWER_UNIT_HV.getMaxChargeOverrideStack(6400000L)};
            for (ItemStack power_unit : power_units) {
                ItemStack output = GTFOMetaItem.BUTCHERY_KNIFE_HV.getMaxChargeOverrideStack(material, power_unit.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null).getMaxCharge());
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(480).duration(250)
                        .input(prefix, material)
                        .input(OrePrefix.stick, material, 2)
                        .input(OrePrefix.plate, material)
                        .input(OrePrefix.circuit, MarkerMaterials.Tier.Extreme, 2)
                        .inputs(power_unit)
                        .input(OrePrefix.wireGtDouble, Materials.MercuryBariumCalciumCuprate, 4)
                        .fluidInputs(Materials.SolderingAlloy.getFluid(144 * 10))
                        .outputs(output)
                        .buildAndRegister();
            }

        }

    }

}