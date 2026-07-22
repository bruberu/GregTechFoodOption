package gregtechfoodoption.mixins;

import java.util.Arrays;
import java.util.List;

import zone.rong.mixinbooter.ILateMixinLoader;

public class GTFOLateMixinPlugin implements ILateMixinLoader {

    @Override
    public List<String> getMixinConfigs() {
        String[] configs = { "mixins.gregtechfoodoption.late.json" };
        return Arrays.asList(configs);
    }
}
