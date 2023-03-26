package glowredman.resiever;

import java.util.List;

import org.spongepowered.asm.mixin.Unique;

import codechicken.nei.PositionedStack;

public interface IInputAccessor {

    @Unique
    List<PositionedStack> resiever$getInput();

}
