package gregtechfoodoption.potion;

public class PotionLengthenerPotion extends GTFOPotion {
    public static final String TAG_NAME = "gregtechfoodoption - lengthener";
    public static PotionLengthenerPotion INSTANCE = null;
    public PotionLengthenerPotion() {
        super("lengthener", false, 0x69FF56, 6);
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
