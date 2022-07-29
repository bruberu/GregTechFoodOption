package gregtechfoodoption.block.tree;

import gregtech.api.block.VariantBlock;
import gregtechfoodoption.block.GTFOMetaBlocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public class GTFOBlockPlanks extends VariantBlock<GTFOBlockPlanks.BlockType> {
    public GTFOBlockPlanks(Material materialIn) {
        super(materialIn);
    }

    public GTFOBlockPlanks() {
        super(net.minecraft.block.material.Material.IRON);
        setTranslationKey("planks");
        setHardness(2.0F);
        setResistance(5.0F);
        setSoundType(SoundType.WOOD);
        setHarvestLevel("axe", 0);
        setDefaultState(getState(GTFOBlockPlanks.BlockType.BANANA_PLANK));
        GTFOMetaBlocks.GTFO_PLANKS.add(this);
    }

    public enum BlockType implements IStringSerializable {

        BANANA_PLANK("banana"),
        ORANGE_PLANK("orange"),
        MANGO_PLANK("mango"),
        APRICOT_PLANK("apricot"),
        LEMON_PLANK("lemon"),
        LIME_PLANK("lime"),
        OLIVE_PLANK("olive");

        private final String name;

        BlockType(String name) {
            this.name = name;
        }

        @Nonnull
        @Override
        public String getName() {
            return this.name;
        }
    }

}
