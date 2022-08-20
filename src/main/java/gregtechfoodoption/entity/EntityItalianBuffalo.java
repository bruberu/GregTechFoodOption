package gregtechfoodoption.entity;

import gregtechfoodoption.GTFOMaterialHandler;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

import java.util.HashSet;
import java.util.Set;

public class EntityItalianBuffalo extends EntityCow {

    private static final Biome[] NEARBY_BIOME_SPAWNS = {Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.RIVER, Biomes.SWAMPLAND};
    public static final Biome[] POSSIBLE_BIOME_SPAWNS = {Biomes.SWAMPLAND, Biomes.BEACH, Biomes.COLD_BEACH, Biomes.FOREST, Biomes.PLAINS, Biomes.ROOFED_FOREST, Biomes.EXTREME_HILLS};

    public EntityItalianBuffalo(World worldIn) {
        super(worldIn);
    }

    @Override
    public boolean getCanSpawnHere() {
        out:
        for(byte biomeByte : world.getChunk(this.chunkCoordX, this.chunkCoordZ).getBiomeArray()) {
            for (Biome currentBiome : POSSIBLE_BIOME_SPAWNS) {
                if (currentBiome.equals(Biome.getBiome(biomeByte)))
                    break out;
            }
        }
        Set<Biome> nearbyBiomes = new HashSet<>();
        for(int i = -1; i < 2; i++) { // We loop in a 3x3 around the spawn position.
            for (int j = -1; j < 2; j++) {
                for(byte biomeByte : world.getChunk(this.chunkCoordX + i, this.chunkCoordZ + j).getBiomeArray()) {
                    nearbyBiomes.add(Biome.getBiome(biomeByte));
                }
            }
        }
        for(Biome biome : NEARBY_BIOME_SPAWNS) {
            if(nearbyBiomes.contains(biome)) {
                return super.getCanSpawnHere();
            }
        }
        return false;
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (itemstack.getItem() == Items.BUCKET && this.getGrowingAge() >= 0 && !player.capabilities.isCreativeMode) {
            itemstack.shrink(1);
            ItemStack bucket = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, GTFOMaterialHandler.ItalianBuffaloMilk.getFluid());
            if (itemstack.isEmpty()) {
                player.setHeldItem(hand, bucket);
            } else if (!player.inventory.addItemStackToInventory(bucket)) {
                player.dropItem(bucket, false);
            }

            return true;
        }
        return super.processInteract(player, hand);
    }

    @Override
    public EntityItalianBuffalo createChild(EntityAgeable ageable) {
        return new EntityItalianBuffalo(this.world);
    }

}
