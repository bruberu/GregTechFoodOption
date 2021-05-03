package com.bruberu.gregtechfoodoption;

import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

import static com.bruberu.gregtechfoodoption.GTFOMaterialHandler.PopcornKernel;

public class GTFOSeedDropsEventHandler {

    private Random rand = new Random();

    @SubscribeEvent
    public void addSeedsToGrass(BlockEvent.HarvestDropsEvent event)
    {
        if(event.getState().getBlock() == Blocks.TALLGRASS && rand.nextInt(16) == 0) {
            event.getDrops().add(PopcornKernel.getItemStack());
        }
    }
}
