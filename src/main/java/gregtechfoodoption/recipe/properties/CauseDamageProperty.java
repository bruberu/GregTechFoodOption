package gregtechfoodoption.recipe.properties;

import gregtech.api.recipes.properties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagFloat;
import org.jetbrains.annotations.NotNull;

public class CauseDamageProperty extends RecipeProperty<Float> {

    public static final String KEY = "cause_damage";
    private static CauseDamageProperty INSTANCE;

    public static CauseDamageProperty getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CauseDamageProperty();
        return INSTANCE;
    }

    private CauseDamageProperty() {
        super(KEY, Float.class);
    }

    protected CauseDamageProperty(String key, Class<Float> type) {
        super(key, type);
    }

    @Override
    public @NotNull NBTBase serialize(@NotNull Object value) {
        return new NBTTagFloat(castValue(value));
    }

    @Override
    public @NotNull Object deserialize(@NotNull NBTBase nbtBase) {
        return ((NBTTagFloat) nbtBase).getFloat();
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("gregtechfoodoption.recipe.cause_damage",
                castValue(value)), x, y, color);
    }
}
