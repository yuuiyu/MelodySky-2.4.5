//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix;

import xyz.Melody.Performance.FoamFix.shared.*;
import xyz.Melody.*;
import net.minecraft.launchwrapper.*;
import net.minecraftforge.fml.relauncher.*;
import com.google.common.cache.*;
import net.minecraftforge.fml.common.registry.*;
import com.google.common.collect.*;
import java.lang.reflect.*;
import java.util.*;

public class ProxyCommon
{
    private void optimizeLaunchWrapper() {
        if (FoamFixShared.config.lwWeakenResourceCache) {
            Client.instance.logger.info("Weakening LaunchWrapper resource cache...");
            try {
                final LaunchClassLoader loader = (LaunchClassLoader)this.getClass().getClassLoader();
                final Field resourceCacheField = ReflectionHelper.findField((Class)LaunchClassLoader.class, new String[] { "resourceCache" });
                final Map oldResourceCache = (Map)resourceCacheField.get(loader);
                final Map newResourceCache = CacheBuilder.newBuilder().weakValues().build().asMap();
                newResourceCache.putAll(oldResourceCache);
                resourceCacheField.set(loader, newResourceCache);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void optimizeForgeRegistries() {
        try {
            int optimizedRegs = 0;
            int optimizedSavings = 0;
            final Class persistentRegistryClass = Class.forName("net.minecraftforge.fml.common.registry.PersistentRegistryManager$PersistentRegistry");
            final Field biMapField = persistentRegistryClass.getDeclaredField("registries");
            final Field availMapField = FMLControlledNamespacedRegistry.class.getDeclaredField("availabilityMap");
            final Field sizeStickyField = BitSet.class.getDeclaredField("sizeIsSticky");
            final Method trimToSizeMethod = BitSet.class.getDeclaredMethod("trimToSize", (Class<?>[])new Class[0]);
            biMapField.setAccessible(true);
            availMapField.setAccessible(true);
            sizeStickyField.setAccessible(true);
            trimToSizeMethod.setAccessible(true);
            for (final Object registryHolder : persistentRegistryClass.getEnumConstants()) {
                final BiMap biMap = (BiMap)biMapField.get(registryHolder);
                for (final FMLControlledNamespacedRegistry registry : biMap.values()) {
                    final BitSet availMap = (BitSet)availMapField.get(registry);
                    final int size = availMap.size();
                    if (size > 65536) {
                        sizeStickyField.set(availMap, false);
                        trimToSizeMethod.invoke(availMap, new Object[0]);
                        ++optimizedRegs;
                        optimizedSavings += size - availMap.size() >> 3;
                    }
                }
            }
            FoamFixShared.ramSaved += optimizedSavings;
            Client.instance.logger.info("Optimized " + optimizedRegs + " FML registries, saving " + optimizedSavings + " bytes.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void preInit() {
    }
    
    public void init() {
    }
    
    public void postInit() {
        this.optimizeLaunchWrapper();
        if (FoamFixShared.config.geDynamicRegistrySizeScaling) {
            this.optimizeForgeRegistries();
        }
    }
}
