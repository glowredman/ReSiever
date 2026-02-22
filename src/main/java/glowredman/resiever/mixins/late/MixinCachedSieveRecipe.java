package glowredman.resiever.mixins.late;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import codechicken.nei.PositionedStack;
import exnihilo.compatibility.nei.RecipeHandlerSieve.CachedSieveRecipe;
import glowredman.resiever.ICachedSieveRecipe;

@Mixin(CachedSieveRecipe.class)
public class MixinCachedSieveRecipe implements ICachedSieveRecipe {

    @Shadow(remap = false)
    private List<PositionedStack> input;

    @Override
    public List<PositionedStack> getInput() {
        return this.input;
    }
}
