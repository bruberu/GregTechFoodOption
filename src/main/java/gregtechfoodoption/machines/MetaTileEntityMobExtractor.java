package gregtechfoodoption.machines;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.RecipeLogicEnergy;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.item.GTFOSimpleMachineMetaTileEntity;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import gregtechfoodoption.recipe.properties.CauseDamageProperty;
import gregtechfoodoption.recipe.properties.MobOnTopProperty;
import gregtechfoodoption.utils.GTFODamageSources;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class MetaTileEntityMobExtractor extends GTFOSimpleMachineMetaTileEntity {
    private AxisAlignedBB boundingBox;
    private Entity attackableTarget;
    private List<Entity> nearbyEntities;

    public MetaTileEntityMobExtractor(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer, int tier, boolean hasFrontFacing,
                                      Function<Integer, Integer> tankScalingFunction) {
        super(metaTileEntityId, recipeMap, renderer, tier, hasFrontFacing, tankScalingFunction);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity holder) {
        return new MetaTileEntityMobExtractor(this.metaTileEntityId, GTFORecipeMaps.MOB_EXTRACTOR_RECIPES,
                GTFOClientHandler.MOB_EXTRACTOR_OVERLAY, this.getTier(), this.hasFrontFacing(), this.getTankScalingFunction());
    }

    protected RecipeLogicEnergy createWorkable(RecipeMap<?> recipeMap) {
        return new MobExtractorRecipeLogic(this, recipeMap, () -> energyContainer);
    }

    protected boolean checkRecipe(@Nonnull Recipe recipe) {
        ResourceLocation entityRequired = recipe.getProperty(MobOnTopProperty.getInstance(), null);
        if (entityRequired == null)
            return true;

        this.nearbyEntities = getEntitiesInProximity();
        if (attackableTarget != null && this.nearbyEntities.contains(attackableTarget)) {
            return true;
        }
        this.attackableTarget = null;
        for (Entity entity : nearbyEntities) {
            if (EntityList.isMatchingName(entity, entityRequired)) {
                attackableTarget = entity;
            }
        }
        return attackableTarget != null;
    }

    protected List<Entity> getEntitiesInProximity() {
        if (boundingBox == null)
            boundingBox = new AxisAlignedBB(this.getPos().up());
        return this.getWorld().getEntitiesWithinAABB(Entity.class, boundingBox, EntitySelectors.IS_ALIVE);
    }

    protected void damageEntity(Recipe recipe) {
        if (attackableTarget != null && recipe.hasProperty(CauseDamageProperty.getInstance())) {
            float damage = recipe.getProperty(CauseDamageProperty.getInstance(), 0f);
            if (damage > 0) {
                attackableTarget.attackEntityFrom(GTFODamageSources.EXTRACTION, damage);
            }
        }
    }

    private static class MobExtractorRecipeLogic extends RecipeLogicEnergy {
        public MobExtractorRecipeLogic(MetaTileEntity metaTileEntity, RecipeMap<?> recipeMap, Supplier<IEnergyContainer> energyContainer) {
            super(metaTileEntity, recipeMap, energyContainer);
        }

        @Override
        protected boolean checkPreviousRecipe() {
            return super.checkPreviousRecipe() && this.checkRecipe(this.previousRecipe);
        }

        @Override
        public boolean checkRecipe(Recipe recipe) {
            return ((MetaTileEntityMobExtractor) metaTileEntity).checkRecipe(recipe);
        }

        @Override
        protected void setupRecipe(@Nonnull Recipe recipe) {
            ((MetaTileEntityMobExtractor) metaTileEntity).damageEntity(recipe);
            super.setupRecipe(recipe);
        }

    }

}
