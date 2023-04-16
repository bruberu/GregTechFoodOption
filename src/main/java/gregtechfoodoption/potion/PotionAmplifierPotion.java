package gregtechfoodoption.potion;

public class PotionAmplifierPotion extends GTFOPotion {
    public static final String TAG_NAME = "gregtechfoodoption - amplifier";
    public static PotionAmplifierPotion INSTANCE = null;
    public PotionAmplifierPotion() {
        super("amplifier", false, 0x69FF56, 5);
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
