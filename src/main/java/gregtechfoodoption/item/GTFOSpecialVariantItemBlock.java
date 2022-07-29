package gregtechfoodoption.item;

import gregtechfoodoption.block.IVariantNamed;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class GTFOSpecialVariantItemBlock<T extends Block & IVariantNamed> extends ItemBlock {

    private T block;

    public GTFOSpecialVariantItemBlock(T block) {
        super(block);
        this.block = block;
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @SuppressWarnings("deprecation")
    public IBlockState getBlockState(ItemStack stack) {
        return block.getStateFromMeta(stack.getItemDamage());
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return block.getVariantTranslationKey(getBlockState(stack));
    }
}
