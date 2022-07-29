package gregtechfoodoption.block.tree;

import gregtech.api.block.VariantBlock;
import gregtechfoodoption.block.GTFOMetaBlocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public class GTFOBlockLog extends VariantBlock<GTFOBlockLog.BlockType> {
    public GTFOBlockLog(Material materialIn) {
        super(materialIn);
    }

    public GTFOBlockLog() {
        super(Material.WOOD);
        setTranslationKey("log");
        setHardness(2.0F);
        setResistance(5.0F);
        setSoundType(SoundType.WOOD);
        setHarvestLevel("axe", 0);
        setDefaultState(getState(GTFOBlockLog.BlockType.BANANA_LOG));
        GTFOMetaBlocks.GTFO_LOGS.add(this);
    }

    public enum BlockType implements IStringSerializable {

        BANANA_LOG("banana"),
        ORANGE_LOG("orange"),
        MANGO_LOG("mango"),
        APRICOT_LOG("apricot"),
        LEMON_LOG("lemon"),
        LIME_LOG("lime"),
        OLIVE_LOG("olive");

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
