package gregtechfoodoption.potion;

import gregtechfoodoption.GTFOConfig;

public class AddictionPotion extends GTFOPotion {
    public static AddictionPotion instance = null;
    public AddictionPotion() {
        super("addiction", true, 0xf768be, 1);
        instance = this;
    }

    @Override
    protected boolean canRender() {
        return GTFOConfig.gtfoPotionConfig.addictionWithdrawal;
    }
}
