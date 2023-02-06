package gregtechfoodoption.potion;

public class SnowGolemSpawnerPotion extends GTFOPotion {
    public static final String TAG_NAME = "gregtechfoodoption - snowgolemspawnerpotion";
    public static SnowGolemSpawnerPotion INSTANCE = null;

    public SnowGolemSpawnerPotion() {
        super("snowgolemspawner", false, 0xcef0e8, 4);
        INSTANCE = this;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    protected boolean canRender() {
        return true;
    }
}
