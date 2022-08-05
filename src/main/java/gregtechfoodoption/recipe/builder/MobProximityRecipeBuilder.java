package gregtechfoodoption.recipe.builder;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtechfoodoption.recipe.properties.CauseDamageProperty;
import gregtechfoodoption.recipe.properties.MobOnTopProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class MobProximityRecipeBuilder extends RecipeBuilder<MobProximityRecipeBuilder> {

    public MobProximityRecipeBuilder() {
    }

    public MobProximityRecipeBuilder(Recipe recipe, RecipeMap<MobProximityRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public MobProximityRecipeBuilder(RecipeBuilder<MobProximityRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public MobProximityRecipeBuilder copy() {
        return new MobProximityRecipeBuilder(this);
    }

    @Override
    public boolean applyProperty(String key, Object value) {
        if (key.equals(MobOnTopProperty.KEY)) {
            this.mob((ResourceLocation) value);
            return true;
        }
        if (key.equals(CauseDamageProperty.KEY)) {
            this.causeDamage(((Number) value).floatValue());
            return true;
        }
        return true;
    }

    public MobProximityRecipeBuilder mob(ResourceLocation entityID) {
        this.applyProperty(MobOnTopProperty.getInstance(), entityID);
        return this;
    }

    public MobProximityRecipeBuilder mob(Class<? extends Entity> clazz) {
        this.applyProperty(MobOnTopProperty.getInstance(), EntityList.getKey(clazz));
        return this;
    }

    public MobProximityRecipeBuilder causeDamage(float damage) {
        this.applyProperty(CauseDamageProperty.getInstance(), damage);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(MobOnTopProperty.getInstance().getKey(), getEntityID())
                .append(CauseDamageProperty.getInstance().getKey(), getDamage())
                .toString();
    }

    public ResourceLocation getEntityID() {
        return this.recipePropertyStorage == null ? new ResourceLocation("lightning_bolt") :
                this.recipePropertyStorage.getRecipePropertyValue(MobOnTopProperty.getInstance(), new ResourceLocation("lightning_bolt"));
    }

    public float getDamage() {
        return this.recipePropertyStorage == null ? 0 :
                this.recipePropertyStorage.getRecipePropertyValue(CauseDamageProperty.getInstance(), 0f);
    }
}
