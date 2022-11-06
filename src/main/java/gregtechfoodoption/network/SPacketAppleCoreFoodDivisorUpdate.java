package gregtechfoodoption.network;

import gregtech.api.net.IPacket;
import gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.PacketBuffer;
import lombok.NoArgsConstructor;
import java.util.UUID;

@NoArgsConstructor
public class SPacketAppleCoreFoodDivisorUpdate implements IPacket {
    public UUID uuid;
    public float divisor;


    public SPacketAppleCoreFoodDivisorUpdate(UUID uuid, float divisor) {
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
