package gregtechfoodoption.integration.top;

import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;

public class GTFOTOPCompatibility {
    public static void registerCompatibility() {
        ITheOneProbe oneProbe = TheOneProbe.theOneProbeImp;
        oneProbe.registerProvider(new GTFORootCropProvider());
    }
}
