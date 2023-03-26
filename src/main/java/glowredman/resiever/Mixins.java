package glowredman.resiever;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public enum Mixins {

    THERMAL_EXPANSION_NEI_FIXES(new Builder("Fix Thermal compat w/ GTNH NEI").addMixinClasses(
            "MixinNEIRecipeBase",
            "MixinRecipeHandlerBase",
            "MixinRecipeHandlerInsolator",
            "MixinRecipeHandlerTransposer").addTargetedMod(TargetedMod.THERMAL_EXPANSION)),
    EX_NIHILO_NEI_FIXES(new Builder("Ex-Nihilio GTNH NEI Compat").addMixinClasses(
            "MixinCachedHammerRecipe",
            "MixinCachedSieveRecipe",
            "MixinRecipeHandlerHammer",
            "MixinRecipeHandlerSieve").addTargetedMod(TargetedMod.EX_NIHILO));

    private static class Builder {

        private final String name;
        private final List<String> mixinClasses = new ArrayList<>();

        private final List<TargetedMod> targetedMods = new ArrayList<>();

        public Builder(String name) {
            this.name = name;
        }

        public Builder addMixinClasses(String... mixinClasses) {
            this.mixinClasses.addAll(Arrays.asList(mixinClasses));
            return this;
        }

        public Builder addTargetedMod(TargetedMod mod) {
            this.targetedMods.add(mod);
            return this;
        }
    }

    public final String name;
    public final List<String> mixinClasses;
    public final List<TargetedMod> targetedMods;

    Mixins(Builder builder) {
        this.name = builder.name;
        this.mixinClasses = builder.mixinClasses;
        this.targetedMods = builder.targetedMods;
        if (this.targetedMods.isEmpty()) {
            throw new RuntimeException("No targeted mods specified for " + this.name);
        }
    }

    private boolean allModsLoaded(List<TargetedMod> targetedMods, Set<String> loadedCoreMods, Set<String> loadedMods) {
        if (targetedMods.isEmpty()) return false;

        for (TargetedMod target : targetedMods) {
            if (target == TargetedMod.VANILLA) continue;

            // Check coremod first
            if (!loadedCoreMods.isEmpty() && target.coreModClass != null
                    && !loadedCoreMods.contains(target.coreModClass))
                return false;
            else if (!loadedMods.isEmpty() && target.modId != null && !loadedMods.contains(target.modId)) return false;
        }

        return true;
    }

    public boolean shouldLoad(Set<String> loadedCoreMods, Set<String> loadedMods) {
        return (allModsLoaded(targetedMods, loadedCoreMods, loadedMods));
    }

}
