package gregtechfoodoption.recipe;

import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.item.GTFOMetaItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import static gregtech.common.items.MetaItems.POWER_UNIT_HV;
import static gregtech.loaders.recipe.handlers.ToolRecipeHandler.addToolRecipe;
import static gregtechfoodoption.item.GTFOMetaItem.BUTCHERY_KNIFE_HV;

public class GTFORecipeHandler {

    public static void register() {
        OrePrefix.ingot.addProcessingHandler(PropertyKey.TOOL, GTFORecipeHandler::processIngot);

        OrePrefix.plate.addProcessingHandler(PropertyKey.TOOL, GTFORecipeHandler::processPlate);

    }

    private static void processIngot(OrePrefix ingotPrefix, Material material, ToolProperty property) {
        if (property.getToolDurability() > 0) {
            addToolRecipe(material, GTFOMetaItem.ROLLING_PIN, true,
                    "  R", " I ", "R f",
                    'I', new UnificationEntry(ingotPrefix, material),
                    'R', new UnificationEntry(OrePrefix.stick, material));
        }

    }

    private static void processPlate(OrePrefix prefix, Material material, ToolProperty property) {
        if (property.getToolDurability() > 0) {
            ItemStack[] powerUnits = {POWER_UNIT_HV.getMaxChargeOverrideStack(1800000L), POWER_UNIT_HV.getMaxChargeOverrideStack(1600000L), POWER_UNIT_HV.getMaxChargeOverrideStack(1200000L), POWER_UNIT_HV.getMaxChargeOverrideStack(6400000L)};
            for (int i = 0; i < powerUnits.length; i++) {
                IElectricItem powerUnit = powerUnits[i].getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
                ItemStack toolItem = BUTCHERY_KNIFE_HV.get(material, 0, powerUnit.getMaxCharge());
                ModHandler.addShapedEnergyTransferRecipe(String.format("%s_%s_%s", "butchery_knife", material, i),
                        toolItem,
                        Ingredient.fromStacks(powerUnits[i]), true, true,
                        "WUd", "wMf", "H H",
                        'H', new UnificationEntry(prefix, material),
                        'U', powerUnits[i],
                        'M', MetaItems.ELECTRIC_MOTOR_HV,
                        'W', new UnificationEntry(OrePrefix.wireGtDouble, Materials.MercuryBariumCalciumCuprate));
            }

        }

    }

}