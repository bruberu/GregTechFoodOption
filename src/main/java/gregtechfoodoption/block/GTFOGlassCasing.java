package gregtechfoodoption.block;

import gregtech.api.block.VariantActiveBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class GTFOGlassCasing extends VariantActiveBlock<GTFOGlassCasing.CasingType> {

    public GTFOGlassCasing() {
        super(Material.GLASS);
        setTranslationKey("transparent_casing");
        setHardness(5.0F);
        setResistance(5.0F);
        setSoundType(SoundType.GLASS);
        setHarvestLevel("pickaxe", 1);
        setDefaultState(getState(CasingType.GREENHOUSE_GLASS));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    @Override
    @Nonnull
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        IBlockState sideState = world.getBlockState(pos.offset(side));

        return sideState.getBlock() == this ?
                getState(sideState) != getState(state) :
                super.shouldSideBeRendered(state, world, pos, side);
    }

    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
        return BlockRenderLayer.TRANSLUCENT == layer;
    }

    public enum CasingType implements IStringSerializable {

        GREENHOUSE_GLASS("greenhouse_glass");

        private final String name;

        CasingType(String name) {
            this.name = name;
        }

        @Override
        @Nonnull
        public String getName() {
            return this.name;
        }

    }
}
