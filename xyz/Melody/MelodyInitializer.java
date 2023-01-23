//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody;

import java.util.stream.*;
import java.lang.reflect.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.network.*;
import net.minecraftforge.fml.common.network.internal.*;
import java.util.*;

public class MelodyInitializer
{
    public static void initModList() {
        try {
            final Loader LJ = Loader.instance();
            final Class<?> testClass = LJ.getClass();
            final Field field = testClass.getDeclaredField("mods");
            field.setAccessible(true);
            final List<ModContainer> dick = (List<ModContainer>)LJ.getModList().stream().collect(Collectors.toList());
            dick.removeIf(c -> c.getName().toLowerCase().equals("melodysky"));
            field.set(LJ, dick);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void initModMap() {
        try {
            final Loader LJ = Loader.instance();
            final Class<?> testClass = LJ.getClass();
            final Field field = testClass.getDeclaredField("namedMods");
            field.setAccessible(true);
            final Map<String, ModContainer> dick = new HashMap<String, ModContainer>(LJ.getIndexedModList());
            if (dick.containsKey("melodysky")) {
                dick.remove("melodysky");
            }
            field.set(LJ, dick);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void initActivedMods() {
        try {
            final Loader loader = Loader.instance();
            final Class<?> clz = loader.getClass();
            final Field modController = clz.getDeclaredField("modController");
            modController.setAccessible(true);
            final LoadController loadController = (LoadController)modController.get(loader);
            final Class<?> testClass = loadController.getClass();
            final Field field = testClass.getDeclaredField("activeModList");
            field.setAccessible(true);
            final List<ModContainer> activeModList = (List<ModContainer>)Loader.instance().getActiveModList().stream().collect(Collectors.toList());
            activeModList.removeIf(c -> c.getName().toLowerCase().equals("melodysky"));
            field.set(loadController, activeModList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void initIServerMods() {
        try {
            final NetworkRegistry nr = NetworkRegistry.INSTANCE;
            final Class<?> testClass = nr.getClass();
            final Field field = testClass.getDeclaredField("registry");
            field.setAccessible(true);
            final Map<ModContainer, NetworkModHolder> registry = new HashMap<ModContainer, NetworkModHolder>(nr.registry());
            ModContainer melodyContainer = null;
            final Set<ModContainer> keySet = registry.keySet();
            for (final ModContainer mod : keySet) {
                if (mod.getName().toLowerCase().equals("melodysky")) {
                    melodyContainer = mod;
                }
            }
            registry.remove(melodyContainer);
            field.set(nr, registry);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
