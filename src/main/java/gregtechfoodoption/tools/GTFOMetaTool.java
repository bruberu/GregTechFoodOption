package gregtechfoodoption.tools;

import gregtech.api.items.metaitem.ElectricStats;
import gregtech.api.items.toolitem.ToolMetaItem;
import gregtech.api.recipes.ModHandler;
import gregtech.api.sound.GTSounds;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtechfoodoption.item.GTFOMetaItem;
import net.minecraft.item.ItemStack;

public class GTFOMetaTool extends ToolMetaItem<ToolMetaItem<?>.MetaToolValueItem> {

    @Override
    public void registerSubItems() {
        GTFOMetaItem.ROLLING_PIN = (MetaToolValueItem) addItem(0, "tool.rolling_pin").setToolStats(new ToolRollingPin())
                .setFullRepairCost(2)
                .addOreDict("craftingToolRollingPin");
        GTFOMetaItem.BUTCHERY_KNIFE_HV = (MetaToolValueItem) addItem(1, "tool.butchery_knife.hv").setToolStats(new ToolButcheryKnifeHV())
                .setFullRepairCost(4.0D)
                .addComponents(ElectricStats.createElectricItem(1600000L, 3L))
                .setSound(GTSounds.CUT) // TODO: add Knife sound
                .addOreDict("craftingToolKnife");
    }

    public void registerRecipes() {
        Material[] rollingPinMaterials = new Material[]{
                Materials.Wood, Materials.Rubber, Materials.Polyethylene, Materials.Polytetrafluoroethylene
        };
        for (int i = 0; i < rollingPinMaterials.length; i++) {
            Material solidMaterial = rollingPinMaterials[i];
            ItemStack itemStack = GTFOMetaItem.ROLLING_PIN.getStackForm();
            GTFOMetaItem.ROLLING_PIN.setToolData(itemStack, solidMaterial, 128 * (1 << i), 1, 1.0f, 1f);
            if(i != 0)
                ModHandler.addShapedRecipe(String.format("rolling_pin_%s", solidMaterial.toString()), itemStack,
                    "  R", " I ", "R f",
                    'I', new UnificationEntry(OrePrefix.ingot, solidMaterial),
                    'R', new UnificationEntry(OrePrefix.stick, Materials.Iron));
            else
                ModHandler.addShapedRecipe("rolling_pin_wood", itemStack,
                        "  R", " P ", "R f",
                        'P', new UnificationEntry(OrePrefix.plank, Materials.Wood),
                        'R', new UnificationEntry(OrePrefix.stick, Materials.Iron));
        }
    }

}
