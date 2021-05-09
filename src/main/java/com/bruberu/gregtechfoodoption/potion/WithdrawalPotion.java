package com.bruberu.gregtechfoodoption.potion;

public class WithdrawalPotion extends GTFOPotion{
    public static WithdrawalPotion instance = null;
    public WithdrawalPotion() {
        super("withdrawal", true, 0x542b35, 2);
        instance = this;
    }
}
