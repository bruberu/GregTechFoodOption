package gregtechfoodoption.potion;

import gregtechfoodoption.GregTechFoodOption;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = GregTechFoodOption.MODID)
public class GTFOPotions {
    public static void initPotionInstances()
    {
        CreativityPotion CREATIVITY = new CreativityPotion();
        //AddictionPotion ADDICTION = new AddictionPotion();
        //WithdrawalPotion WITHDRAWAL = new WithdrawalPotion();
        StepAssistPotion STEPASSIST = new StepAssistPotion();
    }


    @SubscribeEvent
    public static void registerPotionEffects(RegistryEvent.Register<Potion> event)
    {
        event.getRegistry().registerAll(CreativityPotion.instance);
        //event.getRegistry().registerAll(AddictionPotion.instance);
        //event.getRegistry().registerAll(WithdrawalPotion.instance);
        event.getRegistry().registerAll(StepAssistPotion.instance);
    }
}
