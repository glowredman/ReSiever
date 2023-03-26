package glowredman.resiever.mixins.late;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;
import exnihilo.compatibility.nei.RecipeHandlerSieve;
import exnihilo.compatibility.nei.RecipeHandlerSieve.CachedSieveRecipe;
import exnihilo.registries.SieveRegistry;
import exnihilo.registries.helpers.SiftingResult;
import glowredman.resiever.IInputAccessor;
import glowredman.resiever.Utils;

@Mixin(RecipeHandlerSieve.class)
public abstract class MixinRecipeHandlerSieve extends TemplateRecipeHandler {

    /**
     * @author glowredman
     * @reason Fix compat with the GTNH NEI fork
     */
    @Overwrite(remap = false)
    public List<String> handleItemTooltip(GuiRecipe<?> gui, ItemStack stack, List<String> currenttip, int recipe) {
        super.handleItemTooltip(gui, stack, currenttip, recipe);
        CachedSieveRecipe crecipe = (CachedSieveRecipe) this.arecipes.get(recipe);
        if (stack != null && Utils.contains(crecipe.getOtherStacks(), stack)) {
            currenttip.add("Drop Chance:");
            ItemStack sourceStack = ((IInputAccessor) crecipe).resiever$getInput()
                                                              .get(0).item;
            Block inBlock = Block.getBlockFromItem(sourceStack.getItem());
            int meta = sourceStack.getItemDamage();
            for (SiftingResult smash : SieveRegistry.getSiftingOutput(inBlock, meta)) {
                if (NEIServerUtils.areStacksSameTypeCrafting(stack, new ItemStack(smash.item, 1, smash.meta))) {
                    int chance = (int) Math.round(100.0 / smash.rarity);
                    currenttip.add("  * " + chance + "%");

                }

            }

        }
        return currenttip;
    }

}
