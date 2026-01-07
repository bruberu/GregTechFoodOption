package gregtechfoodoption.block;

import gregtech.api.block.VariantBlock;
import gregtech.api.unification.material.Material;
import gregtech.client.renderer.ICubeRenderer;
import gregtechfoodoption.GTFOValues;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import static gregtech.api.unification.material.Materials.BismuthBronze;

public class GTFOMetalCasing extends VariantBlock<GTFOMetalCasing.CasingType> {

    public GTFOMetalCasing() {
        super(net.minecraft.block.material.Material.IRON);
        setTranslationKey("gtfo_metal_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setCreativeTab(GTFOValues.TAB_GTFO_BLOCKS);
        setDefaultState(getState(CasingType.BISMUTH_BRONZE_CASING));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        BISMUTH_BRONZE_CASING("casing_bismuth_bronze", BismuthBronze);


        private final String name;
        private final Material material;

        CasingType(String name, Material material) {
            this.name = name;
            this.material = material;
        }
        @Override
        public String getName() {
            return this.name;
        }

        public Material getMaterial() {
            return this.material;
        }

        public ICubeRenderer getTexture() {
            switch (name) {
                default: {
                    return BISMUTH_BRONZE_CASING.getTexture();
                }
            }
        }
    }
}
