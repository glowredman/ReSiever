package glowredman.resiever.mixins.late;

import net.minecraftforge.fluids.FluidStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import glowredman.resiever.INEIRecipeBase;

@Mixin(targets = "cofh.thermalexpansion.plugins.nei.handlers.RecipeHandlerBase$NEIRecipeBase")
abstract class MixinNEIRecipeBase implements INEIRecipeBase {

    @Shadow(remap = false)
    FluidStack fluid;

    @Shadow(remap = false)
    int energy;

    @Override
    public FluidStack getFluid() {
        return this.fluid;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

}
