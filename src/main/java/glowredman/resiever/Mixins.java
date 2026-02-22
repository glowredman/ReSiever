package glowredman.resiever;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;

public enum Mixins implements IMixins {

    THERMAL_EXPANSION_NEI_FIXES(new MixinBuilder()
        .addCommonMixins(
            "MixinNEIRecipeBase",
            "MixinRecipeHandlerBase",
            "MixinRecipeHandlerCrucible",
            "MixinRecipeHandlerInsolator",
            "MixinRecipeHandlerTransposer")
        .addRequiredMod(TargetedMod.THERMAL_EXPANSION)
        .setPhase(Phase.LATE)),
    EX_NIHILO_NEI_FIXES(new MixinBuilder().addCommonMixins("MixinCachedSieveRecipe", "MixinRecipeHandlerSieve")
        .addRequiredMod(TargetedMod.EX_NIHILO)
        .setPhase(Phase.LATE));

    private final MixinBuilder builder;

    private Mixins(MixinBuilder builder) {
        this.builder = builder;
    }

    @Override
    public MixinBuilder getBuilder() {
        return this.builder;
    }
}
