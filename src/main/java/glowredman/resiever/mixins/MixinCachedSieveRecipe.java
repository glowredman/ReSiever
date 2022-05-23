package glowredman.resiever.mixins;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import codechicken.nei.PositionedStack;
import exnihilo.compatibility.nei.RecipeHandlerSieve.CachedSieveRecipe;
import glowredman.resiever.IInputAccessor;

@Mixin(CachedSieveRecipe.class)
public class MixinCachedSieveRecipe implements IInputAccessor {
    
    @Shadow(remap = false)
    private List<PositionedStack> input;

    @Override
    public List<PositionedStack> resiever$getInput() {
        return input;
    }

}
