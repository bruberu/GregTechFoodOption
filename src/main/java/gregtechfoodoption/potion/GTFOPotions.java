package gregtechfoodoption.potion;

import gregtechfoodoption.GregTechFoodOption;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = GregTechFoodOption.MODID)
public class GTFOPotions {
    public static final List<GTFOPotion> POTIONS = new ArrayList<>();

    public static void initPotionInstances()
    {
        new CreativityPotion();
        //new AddictionPotion();
        //new WithdrawalPotion();
        new StepAssistPotion();
        new SnowGolemSpawnerPotion();
        new CyanidePoisoningPotion();
        new VentingPotion();
        new PotionAmplifierPotion();
        new PotionLengthenerPotion();
    }


    @SubscribeEvent
    public static void registerPotionEffects(RegistryEvent.Register<Potion> event)
    {
        for (GTFOPotion potion : POTIONS)
        {
            event.getRegistry().register(potion);
        }
    }
}
