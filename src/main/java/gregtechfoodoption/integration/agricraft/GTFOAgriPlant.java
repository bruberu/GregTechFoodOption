package gregtechfoodoption.integration.agricraft;

import com.infinityraider.agricraft.api.v1.AgriApi;
import com.infinityraider.agricraft.api.v1.crop.IAgriCrop;
import com.infinityraider.agricraft.api.v1.plant.IAgriPlant;
import com.infinityraider.agricraft.api.v1.render.RenderMethod;
import com.infinityraider.agricraft.api.v1.requirement.IGrowthReqBuilder;
import com.infinityraider.agricraft.api.v1.requirement.IGrowthRequirement;
import com.infinityraider.agricraft.api.v1.stat.IAgriStat;
import com.infinityraider.agricraft.api.v1.util.FuzzyStack;
import com.infinityraider.agricraft.farming.growthrequirement.GrowthReqBuilder;
import com.infinityraider.agricraft.renderers.PlantRenderer;
import com.infinityraider.infinitylib.render.tessellation.ITessellator;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOCrop;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class GTFOAgriPlant implements IAgriPlant {
    private GTFOCrop wrap;
    private List<FuzzyStack> seeds;

    public GTFOAgriPlant(GTFOCrop wrap) {
        this.wrap = wrap;
        this.seeds = new ArrayList<>();
        this.seeds.add(new FuzzyStack(wrap.getSeedStack(), false, false, "agri_growth", "agri_gain", "agri_strength", "agri_analyzed"));
    }

    @Nonnull
    @Override
    public String getId() {
        return wrap.getName();
    }

    @Nonnull
    @Override
    public String getPlantName() {
        return wrap.getLocalizedName();
    }

    @Nonnull
    @Override
    public String getSeedName() {
        return wrap.getSeedStack().getDisplayName();
    }

    @Nonnull
    @Override
    public Collection<FuzzyStack> getSeedItems() {
        return this.seeds;
    }

    @Override
    public boolean isWeed() {
        return false;
    }

    @Override
    public boolean isFertilizable() {
        return true;
    }

    @Override
    public double getSpreadChance() {
        return 0.05;
    }

    @Override
    public double getSpawnChance() {
        return 0;
    }

    @Override
    public double getGrowthChanceBase() {
        return 0.9;
    }

    @Override
    public double getGrowthChanceBonus() {
        return 0.025;
    }

    @Override
    public double getSeedDropChanceBase() {
        return 0.10;
    }

    @Override
    public double getSeedDropChanceBonus() {
        return 0.20;
    }

    @Override
    public double getGrassDropChance() {
        return 0;
    }

    @Override
    public int getGrowthStages() {
        return 6;
    }

    @Override
    public int getTier() {
        return 0;
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public String getInformation() {
        return I18n.format("gregtechfoodoption.crop.agricraft.info");
    }

    @Nonnull
    @Override
    public ItemStack getSeed() {
        return wrap.getSeedStack();
    }

    @Nonnull
    @Override
    public IGrowthRequirement getGrowthRequirement() {
        IGrowthReqBuilder builder = new GrowthReqBuilder();
        builder.addSoil(AgriApi.getSoilRegistry().get(Blocks.FARMLAND.getDefaultState()).get()); // How could this go wrong??? :troll:
        return builder.build();
    }

    @Override
    public void getPossibleProducts(@Nonnull Consumer<ItemStack> consumer) {
        consumer.accept(wrap.getCropStack());
    }

    @Override
    public void getHarvestProducts(@Nonnull Consumer<ItemStack> consumer, @Nonnull IAgriCrop iAgriCrop, @Nonnull IAgriStat iAgriStat, @Nonnull Random random) {
        if (iAgriCrop.isMature()) {
            for (int i = 0; i < 3; ++i) {
                if (random.nextInt(2 * wrap.getMaxAge()) <= iAgriCrop.getGrowthStage()) {
                    consumer.accept(wrap.getCropStack().copy());
                }
            }
        }

    }

    @Nullable
    @Override
    public ResourceLocation getSeedTexture() {
        return null;
    }

    @Override
    public float getHeight(int i) {
        return 0.8125F;
    }

    @Nullable
    @Override
    public RenderMethod getRenderMethod() {
        return RenderMethod.CROSSED;
    }

    @Nullable
    @Override
    public ResourceLocation getPrimaryPlantTexture(int i) {
        int possibleAge = Math.min(i, wrap.getMaxAge()); // Required for clippings to render correctly (they assume all crops have 7 stages... the fools)
        return new ResourceLocation(GTFOValues.MODID, "crop/crop_" + this.wrap.getName() + "/stage" + possibleAge);
    }

    @Nullable
    @Override
    public ResourceLocation getSecondaryPlantTexture(int i) {
        return null;
    }

    @Nonnull
    @Override
    public List<BakedQuad> getPlantQuads(IExtendedBlockState iExtendedBlockState, int growthStage, EnumFacing enumFacing, Function<ResourceLocation, TextureAtlasSprite> textureToIcon) {
        if (textureToIcon instanceof ITessellator) {
            PlantRenderer.renderPlant((ITessellator)textureToIcon, this, growthStage);
        }

        return Collections.emptyList();
    }
}
