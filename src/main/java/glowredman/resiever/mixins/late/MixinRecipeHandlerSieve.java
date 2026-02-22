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
import glowredman.resiever.ICachedSieveRecipe;
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

        if (!Utils.contains(crecipe.getOtherStacks(), stack) || !Utils.isMouseInsideRect(0, 166, 0, 130, gui, recipe)) {
            return currenttip;
        }

        currenttip.add("Drop Chance:");
        ItemStack sourceStack = ((ICachedSieveRecipe) crecipe).getInput()
            .get(0).item;
        Block inBlock = Block.getBlockFromItem(sourceStack.getItem());
        int meta = sourceStack.getItemDamage();
        for (SiftingResult siftingResult : SieveRegistry.getSiftingOutput(inBlock, meta)) {
            if (NEIServerUtils
                .areStacksSameTypeCrafting(stack, new ItemStack(siftingResult.item, 1, siftingResult.meta))) {
                int chance = (int) Math.round(100.0 / siftingResult.rarity);
                currenttip.add("  * " + chance + "%");
            }
        }

        return currenttip;
    }

}
