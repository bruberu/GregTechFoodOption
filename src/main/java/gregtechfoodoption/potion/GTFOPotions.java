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
        new CreativityPotion();
        //new AddictionPotion();
        //new WithdrawalPotion();
        new StepAssistPotion();
        new SnowGolemSpawnerPotion();
        new CyanidePoisoningPotion();
    }


    @SubscribeEvent
    public static void registerPotionEffects(RegistryEvent.Register<Potion> event)
    {
        event.getRegistry().registerAll(CreativityPotion.INSTANCE);
        //event.getRegistry().registerAll(AddictionPotion.instance);
        //event.getRegistry().registerAll(WithdrawalPotion.instance);
        event.getRegistry().registerAll(StepAssistPotion.INSTANCE);
        event.getRegistry().registerAll(SnowGolemSpawnerPotion.INSTANCE);
        event.getRegistry().registerAll(CyanidePoisoningPotion.INSTANCE);
    }
}
