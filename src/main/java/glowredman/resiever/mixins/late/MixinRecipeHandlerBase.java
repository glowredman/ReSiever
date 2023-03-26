package glowredman.resiever.mixins.late;

import net.minecraftforge.fluids.FluidStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;
import cofh.thermalexpansion.plugins.nei.handlers.RecipeHandlerBase;
import glowredman.resiever.INEIRecipeBase;
import glowredman.resiever.Utils;

@Mixin(RecipeHandlerBase.class)
public abstract class MixinRecipeHandlerBase extends TemplateRecipeHandler {

    /**
     * @author glowredman
     * @reason Allow ThermalExpansion to work with the GTNH fork of NEI
     */
    @Overwrite(remap = false)
    protected boolean transferFluidTank(GuiRecipe gui, int recipe, boolean usage) {
        FluidStack fluid = ((INEIRecipeBase) this.arecipes.get(recipe)).getFluid();
        return Utils.isMouseInsideRect(148, 164, 3, 63, gui, recipe) && fluid != null
                && fluid.amount > 0
                && (usage ? GuiUsageRecipe.openRecipeGui("liquid", fluid)
                        : GuiCraftingRecipe.openRecipeGui("liquid", fluid));
    }

    /**
     * @author glowredman
     * @reason Allow ThermalExpansion to work with the GTNH fork of NEI
     */
    @Overwrite(remap = false)
    public void drawEnergy(int recipe) {
        GuiDraw.drawTexturedModalRect(4, 2, 0, 96, 16, this.scaleEnergy);
        int energy = ((INEIRecipeBase) this.arecipes.get(recipe)).getEnergy();
        int numReductions = Math.floorDiv(this.maxEnergy, energy);
        int reductionCount = Math.floorDiv(this.cycleticks, 20); // 1 sec between each reduction
        int scaledEnergy = getScaledEnergy(energy * (reductionCount % numReductions));
        GuiDraw.drawTexturedModalRect(4, 2 + scaledEnergy, 16, 96 + scaledEnergy, 16, this.scaleEnergy - scaledEnergy);
    }

    /**
     * @author glowredman
     * @reason Allow ThermalExpansion to work with the GTNH fork of NEI
     */
    @Overwrite(remap = false)
    public void drawFluid(int recipe, boolean increase) {
        GuiDraw.drawTexturedModalRect(147, 2, 32, 96, 18, this.scaleFluid + 2);
        FluidStack fluid = ((INEIRecipeBase) this.arecipes.get(recipe)).getFluid();
        if (fluid == null) {
            return;
        }
        int numReductions = Math.floorDiv(this.maxFluid, fluid.amount);
        int reductionCount = Math.floorDiv(this.cycleticks, 20); // 1 sec between each reduction
        int scaledFluidAmount = getScaledFluid(fluid.amount * (reductionCount % numReductions));
        if (increase) {
            drawFluidRect(148, 3 + this.scaleFluid - scaledFluidAmount, fluid, 16, scaledFluidAmount);
        } else {
            drawFluidRect(148, 3 + scaledFluidAmount, fluid, 16, this.scaleFluid - scaledFluidAmount);
        }
        GuiDraw.drawTexturedModalRect(148, 2, 80, 96, 18, this.scaleFluid + 2);
    }

    // SHADOWS

    @Shadow(remap = false)
    int maxEnergy;

    @Shadow(remap = false)
    int scaleEnergy;

    @Shadow(remap = false)
    int maxFluid;

    @Shadow(remap = false)
    int scaleFluid;

    @Shadow(remap = false)
    public int getScaledEnergy(int amount) {
        throw new IllegalStateException("ReSiever failed to shadow getScaledEnergy!");
    }

    @Shadow(remap = false)
    public int getScaledFluid(int amount) {
        throw new IllegalStateException("ReSiever failed to shadow getScaledFluid!");
    }

    @Shadow(remap = false)
    protected void drawFluidRect(int j, int k, FluidStack fluid, int width, int height) {
        throw new IllegalStateException("ReSiever failed to shadow drawFluidRect!");
    }

}
