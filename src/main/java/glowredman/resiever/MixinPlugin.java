package glowredman.resiever;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.launchwrapper.Launch;
import ru.timeconqueror.spongemixins.MinecraftURLClassPath;

public class MixinPlugin implements IMixinConfigPlugin {
    
    public static final Logger LOGGER = LogManager.getLogger("ReSiever");

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        boolean dev = (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
        if((dev && MinecraftURLClassPath.findJarInClassPath("Ex-Nihilo")) || loadJar()) {
            return Arrays.asList("MixinCachedHammerRecipe", "MixinCachedSieveRecipe", "MixinRecipeHandlerHammer", "MixinRecipeHandlerSieve");
        }
        LOGGER.error("CRITICAL ERROR: Could not find required jar Ex-Nihilo");
        FMLCommonHandler.instance().exitJava(-1, true);
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
    
    private boolean loadJar() {
        try {
            File jar = MinecraftURLClassPath.getJarInModPath("Ex-Nihilo");
            if(jar == null) {
                LOGGER.info("Could not find Ex-Nihilo jar");
                return false;
            }
            LOGGER.info("Attempting to add " + jar + " to the URL Class Path");
            if(!jar.exists()) {
                throw new FileNotFoundException(jar.toString());
            }
            MinecraftURLClassPath.addJar(jar);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
