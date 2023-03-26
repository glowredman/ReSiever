package glowredman.resiever;

import java.awt.Point;
import java.util.List;

import net.minecraft.item.ItemStack;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.GuiRecipe;

public class Utils {

    public static boolean contains(List<PositionedStack> list, ItemStack stack) {
        for (PositionedStack pStack : list) {
            if (NEIServerUtils.areStacksSameTypeCrafting(stack, pStack.item)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMouseInsideRect(int minX, int maxX, int minY, int maxY, GuiRecipe<?> gui, int recipe) {
        Point root = gui.getRecipePosition(recipe);
        return isInsideRect(
                GuiDraw.getMousePosition(),
                minX + gui.guiLeft + root.x,
                maxX + gui.guiLeft + root.x,
                minY + gui.guiTop + root.y,
                maxY + gui.guiTop + root.y);
    }

    public static boolean isInsideRect(Point p, int minX, int maxX, int minY, int maxY) {
        return p.x >= minX && p.x <= maxX && p.y >= minY && p.y <= maxY;
    }

}
