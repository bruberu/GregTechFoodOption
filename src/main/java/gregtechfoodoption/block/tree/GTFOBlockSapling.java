package gregtechfoodoption.block.tree;

import gregtech.api.block.VariantBlock;
import gregtechfoodoption.block.GTFOMetaBlocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public class GTFOBlockSapling extends VariantBlock<GTFOBlockSapling.BlockType> {
    public GTFOBlockSapling(Material materialIn) {
        super(materialIn);
    }

    public GTFOBlockSapling() {
        super(Material.LEAVES);
        this.setTickRandomly(true);
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setSoundType(SoundType.PLANT);
        GTFOMetaBlocks.GTFO_SAPLINGS.add(this);
    }

    public enum BlockType implements IStringSerializable {

        BANANA_SAPLING("banana"),
        ORANGE_SAPLING("orange"),
        MANGO_SAPLING("mango"),
        APRICOT_SAPLING("apricot"),
        LEMON_SAPLING("lemon"),
        LIME_SAPLING("lime"),
        OLIVE_SAPLING("olive");

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
