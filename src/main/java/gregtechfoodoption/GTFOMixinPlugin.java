package gregtechfoodoption;

import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Arrays;
import java.util.List;

public class GTFOMixinPlugin implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        String[] configs = {"mixins.gregtechfoodoption.json"};
        return Arrays.asList(configs);
    }
}
