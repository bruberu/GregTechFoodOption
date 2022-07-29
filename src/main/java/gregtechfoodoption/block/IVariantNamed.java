package gregtechfoodoption.block;

import net.minecraft.block.state.IBlockState;

public interface IVariantNamed {
    String getVariantTranslationKey(IBlockState state);
}
