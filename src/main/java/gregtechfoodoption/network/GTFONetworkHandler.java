package gregtechfoodoption.network;

import gregtech.api.net.PacketHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static gregtech.api.net.PacketHandler.registerClientExecutor;

public class GTFONetworkHandler {
    public static void init() {
        PacketHandler.registerPacket(SPacketAppleCoreFoodDivisorUpdate.class);

        if (FMLCommonHandler.instance().getSide().isClient()) {
            clientInit();
        }
    }

    @SideOnly(Side.CLIENT)
    public static void clientInit() {
        registerClientExecutor(SPacketAppleCoreFoodDivisorUpdate.class);
    }

}
