package gregtechfoodoption.block;

import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GTFOBlockCasing extends VariantBlock<GTFOBlockCasing.CasingType> {

    public GTFOBlockCasing() {
        super(Material.IRON);
        setTranslationKey("gtfo_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 2);
        setDefaultState(getState(CasingType.ADOBE_BRICKS));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        ADOBE_BRICKS("adobe_bricks");


        private final String name;

        CasingType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

    }

}
