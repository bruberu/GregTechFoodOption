package gregtechfoodoption.potion;

public class SnowGolemSpawnerPotion extends GTFOPotion {
    public static final String TAG_NAME = "gregtechfoodoption - snowgolemspawnerpotion";
    public static SnowGolemSpawnerPotion instance = null;

    public SnowGolemSpawnerPotion() {
        super("snowgolemspawner", false, 0xcef0e8, 4);
        instance = this;
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
