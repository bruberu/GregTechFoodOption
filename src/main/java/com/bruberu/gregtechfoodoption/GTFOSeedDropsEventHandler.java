package com.bruberu.gregtechfoodoption;

import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

import static com.bruberu.gregtechfoodoption.GTFOMaterialHandler.PopcornKernel;
import static com.bruberu.gregtechfoodoption.item.GTFOMetaItem.LEMON;
import static com.bruberu.gregtechfoodoption.item.GTFOMetaItem.LIME;

public class GTFOSeedDropsEventHandler {

    private Random rand = new Random();

    @SubscribeEvent
    public void addSeeds(BlockEvent.HarvestDropsEvent event)
    {
        if(event.getState().getBlock() == Blocks.TALLGRASS && rand.nextInt(30) == 0) {
            event.getDrops().add(PopcornKernel.getItemStack());
        }
        if(event.getState().getBlock() == Blocks.LEAVES) {
            if(rand.nextInt(30) == 0)
                event.getDrops().add(LEMON.getStackForm());
            if(rand.nextInt(30) == 0)
                event.getDrops().add(LIME.getStackForm());
        }
    }

}
