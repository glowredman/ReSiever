package glowredman.resiever.mixins.late;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import codechicken.nei.recipe.GuiRecipe;
import cofh.thermalexpansion.plugins.nei.handlers.RecipeHandlerBase;
import cofh.thermalexpansion.plugins.nei.handlers.RecipeHandlerCrucible;
import glowredman.resiever.INEIRecipeBase;
import glowredman.resiever.Utils;

@Mixin(RecipeHandlerCrucible.class)
public abstract class MixinRecipeHandlerCrucible extends RecipeHandlerBase {

    /**
     * @author glowredman
     * @reason Allow ThermalExpansion to work with the GTNH fork of NEI
     */
    @Overwrite(remap = false)
    public List<String> handleTooltip(GuiRecipe<?> gui, List<String> currenttip, int recipe) {
        if (Utils.isMouseInsideRect(148, 164, 3, 63, gui, recipe)) {
            currenttip.add(
                ((INEIRecipeBase) this.arecipes.get(recipe)).getFluid()
                    .getLocalizedName());
        }
        return super.handleTooltip(gui, currenttip, recipe);
    }
}
