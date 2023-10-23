package gregtechfoodoption.mixins;

import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Arrays;
import java.util.List;

public class GTFOLateMixinPlugin implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        String[] configs = {"mixins.gregtechfoodoption.late.json"};
        return Arrays.asList(configs);
    }
}
