package gregtechfoodoption.potion;

public class AntiSchizoPotion extends GTFOPotion {
    public static AntiSchizoPotion INSTANCE = null;

    public AntiSchizoPotion() {
        super("antischizo", false, 0xf5f5f5, -1);
        INSTANCE = this;
    }

    @Override
    protected boolean canRender() {
        return false;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
