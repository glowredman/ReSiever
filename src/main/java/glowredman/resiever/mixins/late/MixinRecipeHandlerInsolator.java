package glowredman.resiever.mixins.late;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import codechicken.nei.recipe.GuiRecipe;
import cofh.lib.util.helpers.FluidHelper;
import cofh.thermalexpansion.plugins.nei.handlers.RecipeHandlerInsolator;
import glowredman.resiever.Utils;

@Mixin(RecipeHandlerInsolator.class)
public abstract class MixinRecipeHandlerInsolator extends MixinRecipeHandlerBase {

    /**
     * @author glowredman
     * @reason Allow ThermalExpansion to work with the GTNH fork of NEI
     */
    @Overwrite(remap = false)
    public List<String> handleTooltip(GuiRecipe<?> gui, List<String> currenttip, int recipe) {
        if (Utils.isMouseInsideRect(148, 164, 3, 63, gui, recipe)) {
            currenttip.add(FluidHelper.WATER.getLocalizedName());
        }
        return super.handleTooltip(gui, currenttip, recipe);
    }

}
