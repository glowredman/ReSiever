package glowredman.resiever;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import com.gtnewhorizon.gtnhmixins.builders.TargetModBuilder;

public enum TargetedMod implements ITargetMod {

    THERMAL_EXPANSION("ThermalExpansion"),
    EX_NIHILO("exnihilo");

    private final TargetModBuilder builder;

    TargetedMod(TargetModBuilder builder) {
        this.builder = builder;
    }

    TargetedMod(String modId) {
        this(null, modId, null);
    }

    TargetedMod(String coreModClass, String modId) {
        this(coreModClass, modId, null);
    }

    TargetedMod(String coreModClass, String modId, String targetClass) {
        this.builder = new TargetModBuilder().setCoreModClass(coreModClass)
            .setModId(modId)
            .setTargetClass(targetClass);
    }

    @Override
    @Nonnull
    public TargetModBuilder getBuilder() {
        return this.builder;
    }
}
