package gregtechfoodoption.network;

import gregtech.api.network.IClientExecutor;
import gregtech.api.network.IPacket;
import gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.PacketBuffer;

import java.util.UUID;

public class PacketAppleCoreFoodDivisorUpdate implements IPacket, IClientExecutor {
    public UUID uuid;
    public float divisor;

    public PacketAppleCoreFoodDivisorUpdate() {

    }
    
    public PacketAppleCoreFoodDivisorUpdate(UUID uuid, float divisor) {
        this.uuid = uuid;
        this.divisor = divisor;
        GTFOAppleCoreCompat.clientDivisorsMap.put(uuid, divisor);
    }

    @Override
    public void encode(PacketBuffer packetBuffer) {
        packetBuffer.writeUniqueId(uuid);
        packetBuffer.writeFloat(divisor);
    }

    @Override
    public void decode(PacketBuffer packetBuffer) {
        uuid = packetBuffer.readUniqueId();
        divisor = packetBuffer.readFloat();
    }

    @Override
    public void executeClient(NetHandlerPlayClient handler) {
        GTFOAppleCoreCompat.clientDivisorsMap.put(uuid, divisor);
    }
}
