package com.bruberu.gregtechfoodoption.potion;

import com.bruberu.gregtechfoodoption.GTFOConfig;

public class WithdrawalPotion extends GTFOPotion{
    public static WithdrawalPotion instance = null;
    public WithdrawalPotion() {
        super("withdrawal", true, 0x542b35, 2);
        instance = this;
    }

    @Override
    protected boolean canRender() {
        return GTFOConfig.gtfoPotionConfig.addictionWithdrawal;
    }
}
