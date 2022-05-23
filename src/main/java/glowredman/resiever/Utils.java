package glowredman.resiever;

import java.util.List;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import net.minecraft.item.ItemStack;

public class Utils {
    
    public static boolean contains(List<PositionedStack> list, ItemStack stack) {
        for(PositionedStack pStack : list) {
            if(NEIServerUtils.areStacksSameTypeCrafting(stack, pStack.item)) {
                return true;
            }
        }
        return false;
    }

}
