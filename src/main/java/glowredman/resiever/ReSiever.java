package glowredman.resiever;

import java.util.List;
import java.util.Set;

import net.minecraft.nbt.NBTTagCompound;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;

@Mod(
    acceptableRemoteVersions = "*",
    acceptedMinecraftVersions = "[1.7.10]",
    dependencies = "required-after:gtnhmixins@[0.1.21,);required-after:NotEnoughItems@[2.3.59,)",
    modid = "resiever",
    name = "ReSiever",
    version = Tags.VERSION)
@LateMixin
public class ReSiever implements ILateMixinLoader {

    @EventHandler
    public static void init(FMLInitializationEvent event) {
        if (Loader.isModLoaded("ThermalExpansion")) {
            final String[][] handlers = { { "cofh.thermalexpansion.plugins.nei.handlers.RecipeHandlerCrucible", "4" },
                { "cofh.thermalexpansion.plugins.nei.handlers.RecipeHandlerInsolator", "11" },
                { "cofh.thermalexpansion.plugins.nei.handlers.RecipeHandlerTransposer", "5" } };

            for (final String[] handler : handlers) {
                final NBTTagCompound data = new NBTTagCompound();
                data.setString("handler", handler[0]);
                data.setString("modName", "Thermal Expansion");
                data.setString("itemName", "ThermalExpansion:Machine:" + handlers[1]);
                data.setString("modId", "ThermalExpansion");
                data.setBoolean("modRequired", true);
                data.setInteger("handlerHeight", 65);
                data.setInteger("handlerWidth", 166);
                data.setInteger("maxRecipesPerPage", 5);
                FMLInterModComms.sendMessage("NotEnoughItems", "registerHandlerInfo", data);
            }
        }

        if (Loader.isModLoaded("exnihilo")) {
            final NBTTagCompound data = new NBTTagCompound();
            data.setString("handler", "exnihilo.compatibility.nei.RecipeHandlerSieve");
            data.setString("modName", "Ex Nihilo");
            data.setString("itemName", "exnihilo:sifting_table");
            data.setString("modId", "exnihilo");
            data.setBoolean("modRequired", true);
            data.setInteger("handlerHeight", 130);
            data.setInteger("maxRecipesPerPage", 5);
            FMLInterModComms.sendMessage("NotEnoughItems", "registerHandlerInfo", data);
        }
    }

    @Override
    public String getMixinConfig() {
        return "mixins.resiever.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        return IMixins.getLateMixins(Mixins.class, loadedMods);
    }
}
