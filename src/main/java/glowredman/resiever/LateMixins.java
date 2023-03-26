package glowredman.resiever;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

@LateMixin
public class LateMixins implements ILateMixinLoader {

    public static final Logger LOGGER = LogManager.getLogger("ReSiever");

    @Override
    public String getMixinConfig() {
        return "mixins.resiever.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        final List<String> mixins = new ArrayList<>();
        final List<String> notLoading = new ArrayList<>();
        for (Mixins mixin : Mixins.values()) {
            if (mixin.shouldLoad(Collections.emptySet(), loadedMods)) {
                mixins.addAll(mixin.mixinClasses);
            } else {
                notLoading.addAll(mixin.mixinClasses);
            }
        }
        LOGGER.info("Not loading the following LATE mixins: {}", notLoading.toString());
        return mixins;
    }

}
