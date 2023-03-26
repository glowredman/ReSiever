package glowredman.resiever.mixins.late;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import codechicken.nei.PositionedStack;
import exnihilo.compatibility.nei.RecipeHandlerHammer.CachedHammerRecipe;
import glowredman.resiever.IInputAccessor;

@Mixin(CachedHammerRecipe.class)
public class MixinCachedHammerRecipe implements IInputAccessor {

    @Shadow(remap = false)
    private List<PositionedStack> input;

    @Override
    public List<PositionedStack> resiever$getInput() {
        return input;
    }

}
