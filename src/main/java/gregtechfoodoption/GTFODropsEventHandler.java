package gregtechfoodoption;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

import static gregtechfoodoption.item.GTFOMetaItem.*;

public class GTFODropsEventHandler {

    private final Random rand = new Random();

    @SubscribeEvent
    public void addSeeds(BlockEvent.HarvestDropsEvent event) {
        if (event.getState().getBlock() == Blocks.LEAVES && GTFOConfig.gtfoMiscConfig.dropLemonsAndLimes) {
            if (rand.nextInt(200 / (event.getFortuneLevel() + 1)) == 0)
                event.getDrops().add(LEMON.getStackForm());
            if (rand.nextInt(200 / (event.getFortuneLevel() + 1)) == 0)
                event.getDrops().add(LIME.getStackForm());
        }
    }

    @SubscribeEvent
    public void addDrops(LivingDropsEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntityPig ||
                entity instanceof EntityCow ||
                entity instanceof EntityChicken ||
                entity instanceof EntitySheep ||
                entity instanceof EntityRabbit) {
            if (rand.nextInt(3) == 0) {
                event.getDrops().add(
                        new EntityItem(entity.getEntityWorld(),
                                entity.getPosition().getX(),
                                entity.getPosition().getY() + 1,
                                entity.getPosition().getZ(),
                                SCRAP_MEAT.getStackForm(event.getLootingLevel() == 0 ? 1 : rand.nextInt(event.getLootingLevel()) + 1)));
            }
        }
        if(entity instanceof EntitySheep){
            if (rand.nextInt(2) == 0) {
                event.getDrops().add(
                        new EntityItem(entity.getEntityWorld(),
                                entity.getPosition().getX(),
                                entity.getPosition().getY() + 1,
                                entity.getPosition().getZ(),
                                GTFOMaterialHandler.Fat.getItemStack(event.getLootingLevel() == 0 ? 1 : rand.nextInt(event.getLootingLevel()) + 1)));
            }
        }
    }

}
