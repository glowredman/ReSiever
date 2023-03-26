package glowredman.resiever.mixins.late;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;
import exnihilo.compatibility.nei.RecipeHandlerHammer;
import exnihilo.compatibility.nei.RecipeHandlerHammer.CachedHammerRecipe;
import exnihilo.registries.HammerRegistry;
import exnihilo.registries.helpers.Smashable;
import glowredman.resiever.IInputAccessor;
import glowredman.resiever.Utils;

@Mixin(RecipeHandlerHammer.class)
public abstract class MixinRecipeHandlerHammer extends TemplateRecipeHandler {

    /**
     * @author glowredman
     * @reason Fix compat with the GTNH NEI fork
     */
    @Overwrite(remap = false)
    public List<String> handleItemTooltip(GuiRecipe gui, ItemStack stack, List<String> currenttip, int recipe) {
        super.handleItemTooltip(gui, stack, currenttip, recipe);
        CachedHammerRecipe crecipe = (CachedHammerRecipe) this.arecipes.get(recipe);
        if (stack != null && Utils.contains(crecipe.getOtherStacks(), stack)) {
            currenttip.add("Drop Chance:");
            ItemStack sourceStack = ((IInputAccessor) crecipe).resiever$getInput().get(0).item;
            Block inBlock = Block.getBlockFromItem(sourceStack.getItem());
            int meta = sourceStack.getItemDamage();
            for (Smashable smash : HammerRegistry.getRewards(inBlock, meta)) {
                if (NEIServerUtils.areStacksSameTypeCrafting(stack, new ItemStack(smash.item, 1, smash.meta))) {
                    int chance = (int) (100 * smash.chance);
                    int fortune = (int) (100 * smash.luckMultiplier);
                    if (fortune > 0) currenttip.add(
                            "  * " + chance
                                    + "%"
                                    + EnumChatFormatting.BLUE
                                    + " (+"
                                    + fortune
                                    + "% luck bonus)"
                                    + EnumChatFormatting.RESET);
                    else currenttip.add("  * " + chance + "%");

                }

            }

        }
        return currenttip;
    }

}
